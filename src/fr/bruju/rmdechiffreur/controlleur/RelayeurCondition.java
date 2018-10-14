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
	
	private boolean ignorerBrancheSi;
	private boolean ignorerBrancheSinon;

	
	public RelayeurCondition(RelayeurDInstructions relayeurPere, int mode) {
		this.relayeurPere = relayeurPere;
		this.niveau = 1;
		this.estDansLaBrancheSi = true;
		ignorerBrancheSi = (mode | 2) == 1;
		ignorerBrancheSinon = (mode | 1) == 1;
	}

	@Override
	public RelayeurDInstructions traiter(RMInstruction instruction) {
		int code = instruction.code();
		
		if (!brancheActuelleIgnoree()) {
			if (code != CODE_SINON || (!ignorerBrancheSi && ! ignorerBrancheSinon)) {
				// Sinon n'est transmis que si on exploite les deux branches
				relayeurPere.traiter(instruction);
			}
		}
		
		majNiveau(code);
		
		if (niveau == 0) {
			if (code == CODE_SINON) {
				estDansLaBrancheSi = false;
				niveau++;
				return this;
			} else if (code == CODE_FINSI) {
				if ((ignorerBrancheSinon && !estDansLaBrancheSi)
						|| (ignorerBrancheSi && estDansLaBrancheSi)
						) {
					relayeurPere.traiter(instruction); // Transmettre la fin
				}
				
				return relayeurPere;
			}
			
			throw new RuntimeException("code = " + code);
		} else {
			if (code == CODE_SINON) {
				niveau++;
			}
			
			return this;
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
