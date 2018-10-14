package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.lcfreader.rmobjets.RMInstruction;
import fr.bruju.rmdechiffreur.Utilitaire;

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

	/**
	 * Commence l'ignorement d'une instruction
	 * @param codeDebut Le code de l'instruction qui commence l'ignorement
	 * @param codeFin Le code qui permet la fin
	 */
	public RelayeurCondition(RelayeurDInstructions relayeurPere, int mode, int codeFin) {
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
			relayeurPere.traiter(instruction);
		}
		
		majNiveau(code);
		
		if (niveau == 0) {
			if (code == CODE_SINON) {
				estDansLaBrancheSi = false;
				niveau++;
				return this;
			} else if (code == CODE_FINSI) {
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
