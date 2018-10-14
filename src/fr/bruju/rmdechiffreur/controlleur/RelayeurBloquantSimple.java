package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.lcfreader.rmobjets.RMInstruction;

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

	/**
	 * Commence l'ignorement d'une instruction
	 * @param codeDebut Le code de l'instruction qui commence l'ignorement
	 * @param codeFin Le code qui permet la fin
	 */
	public RelayeurBloquantSimple(int codeDebut, int codeFin) {
		this.codeDebut = codeDebut;
		this.codeFin = codeFin;
		this.niveau = 1;
	}
	
	/**
	 * Applique le code au bloc d'ignorement.
	 * @param code Le code reçu
	 * @return this si les instructions suivantes doivent être ignorées. null si on met fin à l'ignorement des
	 * instructions
	 */
	public RelayeurBloquantSimple appliquerCode(int code) {
		if (codeDebut == code) {
			this.niveau ++;
		} else if (codeFin == code) {
			this.niveau --;
		}
		
		return (niveau == 0) ? null : this;
	}

	@Override
	public RelayeurDInstructions traiter(RMInstruction instruction) {
		return appliquerCode(instruction.code());
	}
}
