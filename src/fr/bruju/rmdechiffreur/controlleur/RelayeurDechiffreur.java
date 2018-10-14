package fr.bruju.rmdechiffreur.controlleur;

import java.util.Arrays;
import java.util.Map;

import fr.bruju.lcfreader.rmobjets.RMInstruction;
import fr.bruju.rmdechiffreur.ExecuteurInstructions;

public class RelayeurDechiffreur implements RelayeurDInstructions {
	
	public static RelayeurDechiffreur instance;
	
	/* =============
	 * BASE GENERALE
	 * ============= */
	
	/** Instructions dont le décryptage est connu */
	private static Map<Integer, Traiteur> instructionsConnues;

	/**
	 * Rempli la liste des instructions connues. Fonction static pour ne pas recréer tous les traiteurs à chaque
	 * nouveau déchiffrage
	 */
	private static void remplirInstructions() {
		if (instructionsConnues == null) {
			instructionsConnues = new DechiffrageDesInstructions().getTraiteurs();
		}
	}

	/* ==========================
	 * RELAYEUR POUR UN EXECUTEUR
	 * ========================== */
	
	
	private ExecuteurInstructions executeur;
	
	public RelayeurDechiffreur(ExecuteurInstructions executeur) {
		remplirInstructions();
		this.executeur = executeur;
		instance = this;
	}

	@Override
	public void traiter(RMInstruction instruction, DechiffreurInstructions dechiffreurInstructions) {
		Traiteur traiteur = instructionsConnues.get(instruction.code());
		
		if (traiteur != null) {
			int resultat = traiteur.executer(executeur, instruction.parametres(), instruction.argument());
			if (resultat != 0) {
				dechiffreurInstructions.relayeurActuel = traiteur.relayer(resultat, dechiffreurInstructions.relayeurActuel);
			}
		} else {
			instructionInconnue(instruction);
		}
	}

	

	/**
	 * Affiche un message expliquant que l'instruction est inconnue, avec ses paramètres
	 * @param instruction L'instruction
	 */
	private void instructionInconnue(RMInstruction instruction) {
		DechiffreurInstructions.afficherErreur(" --> Instruction [" + instruction.code() + "] non déchiffrable "
				+ "<" + instruction.argument() + "> "
				+ Arrays.toString(instruction.parametres()) + "\n");
	}
}
