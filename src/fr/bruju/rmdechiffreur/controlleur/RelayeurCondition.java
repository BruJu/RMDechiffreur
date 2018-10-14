package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.lcfreader.rmobjets.RMInstruction;

/**
 * Classe permettant de gérer le fait d'ignorer certaines instructions
 * 
 * @author Bruju
 *
 */
public class RelayeurCondition implements RelayeurDInstructions {
	private static final int CODE_SI = 12010;
	private static final int CODE_SINON = 22010;
	private static final int CODE_FINSI = 22011;
	
	
	private final RelayeurDInstructions relayeurPere;
	private int niveau;
	private boolean estDansLaBrancheSi;
	
	private final boolean ignorerBrancheSi;
	private final boolean ignorerBrancheSinon;

	
	public RelayeurCondition(RelayeurDInstructions relayeurPere, int mode) {
		this.relayeurPere = relayeurPere;
		this.niveau = 1;
		this.estDansLaBrancheSi = true;
		ignorerBrancheSi = (mode & 2) == 2;
		ignorerBrancheSinon = (mode & 1) == 1;
	}


	private boolean estRelayable(int code) {
		// Instruction transmise dnas tous les cas si on envoie pas sinon et si on est pas au niveau 1
		if (niveau > 1 || code != CODE_SINON) {
			return true;
		}
		
		// Sinon transmis au niveau 1 que si on prend les deux branches
		return !(ignorerBrancheSi || ignorerBrancheSinon);
	}
	
	@Override
	public void traiter(RMInstruction instruction, DechiffreurInstructions dechiffreurInstructions) {
		int code = instruction.code();
		
		if (!brancheActuelleIgnoree()) {
			if (estRelayable(code)) {
				relayeurPere.traiter(instruction, dechiffreurInstructions);
			}
		}
		
		majNiveau(code);
		
		if (niveau == 0) {
			if (code == CODE_SINON) {
				estDansLaBrancheSi = false;
				niveau++;
			} else if (code == CODE_FINSI) {
				if (brancheActuelleIgnoree() && (!ignorerBrancheSi || !ignorerBrancheSinon)) {
					relayeurPere.traiter(instruction, dechiffreurInstructions); // Transmettre la fin
				}
				
				dechiffreurInstructions.relayeurActuel = relayeurPere;
			} else {
				throw new RuntimeException("code = " + code);
			}
		} else {
			if (code == CODE_SINON) {
				niveau++;
			}
		}
	}
	

	private boolean brancheActuelleIgnoree() {
		return (estDansLaBrancheSi && ignorerBrancheSi) || (!estDansLaBrancheSi && ignorerBrancheSinon);
	}
	
	private void majNiveau(int code) {
		if (code == CODE_SI) {
			niveau++;
		} else if (code == CODE_FINSI || code == CODE_SINON) {
			niveau--;
		}
	}


}
