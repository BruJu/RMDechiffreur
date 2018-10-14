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
	private final RelayeurDInstructions relayeurPere;
	private int[] temporisation; // Liste des codes qui annulent le bloquage lors de l'instruction suivant

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
		this.codeDebut = codeDebut;
		this.codeFin = codeFin;
		this.niveau = 1;
		this.relayeurPere = relayeurPere;
		this.temporisation = temporisation;
	}



	/**
	 * Applique le code au bloc d'ignorement.
	 * @param code Le code reçu
	 * @return this si les instructions suivantes doivent être ignorées. null si on met fin à l'ignorement des
	 * instructions
	 */
	public RelayeurDInstructions traiter(RMInstruction instruction) {
		int code = instruction.code();
		
		if (temporisation != null) {
			if (Utilitaire.getPosition(code, temporisation) == -1) {
				relayeurPere.traiter(instruction);
				return relayeurPere;
			}
		}
		
		if (codeDebut == code) {
			this.niveau ++;
		} else if (codeFin == code) {
			this.niveau --;
		}
		
		return (niveau == 0) ? relayeurPere : this;
	}
}
