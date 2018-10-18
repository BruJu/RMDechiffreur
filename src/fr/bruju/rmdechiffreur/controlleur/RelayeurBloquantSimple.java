package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.lcfreader.rmobjets.RMInstruction;
import fr.bruju.rmdechiffreur.Utilitaire;

/**
 * Classe permettant de gérer le fait d'ignorer certaines instructions
 * 
 * @author Bruju
 *
 */
public class RelayeurBloquantSimple implements RelayeurDInstructions {
	private final int codeDebut;
	private final int codeFin;
	private int niveau;
	private int[] temporisation; // Liste des codes qui annulent le bloquage lors de l'instruction suivant
	private final RelayeurDInstructions relayeurPere;

	/**
	 * Commence l'ignorement d'une instruction
	 * @param codeDebut Le code de l'instruction qui commence l'ignorement
	 * @param codeFin Le code qui permet la fin
	 */
	public RelayeurBloquantSimple(RelayeurDInstructions relayeurPere, int codeDebut, int codeFin) {
		this.relayeurPere = relayeurPere;
		this.codeDebut = codeDebut;
		this.codeFin = codeFin;
		this.niveau = 1;
		this.temporisation = null;
	}
	
	
	
	public RelayeurBloquantSimple(RelayeurDInstructions relayeurPere, int codeDebut, int codeFin, int[] temporisation) {
		this.relayeurPere = relayeurPere;
		this.codeDebut = codeDebut;
		this.codeFin = codeFin;
		this.niveau = 1;
		this.temporisation = temporisation;
	}



	/**
	 * Applique le code au bloc.
	 */
	public void traiter(RMInstruction instruction, DechiffreurInstructions dechiffreurInstructions) {
		int code = instruction.code();
		
		if (temporisation != null) {
			if (Utilitaire.getPosition(code, temporisation) == -1) {
				dechiffreurInstructions.relayeurActuel = relayeurPere;
				relayeurPere.traiter(instruction, dechiffreurInstructions);
				return;
			}
		}
		
		if (codeDebut == code) {
			this.niveau ++;
		} else if (codeFin == code) {
			this.niveau --;
		}
		
		if (niveau == 0) {
			dechiffreurInstructions.relayeurActuel = relayeurPere;
		}
	}
}
