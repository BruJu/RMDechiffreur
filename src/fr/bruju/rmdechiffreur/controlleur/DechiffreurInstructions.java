package fr.bruju.rmdechiffreur.controlleur;

import java.util.List;

import fr.bruju.lcfreader.rmobjets.RMInstruction;
import fr.bruju.rmdechiffreur.ExecuteurInstructions;

/**
 * Un déchiffreur d'instructions qui permet d'appeler des fonctions ayant des noms plus explicites qu'un code et une
 * liste d'arguments.
 * 
 * @author Bruju
 *
 */
public class DechiffreurInstructions {
	/* =======================================
	 * Liste des instructions connues (static)
	 * ======================================= */
	
	/** Booléen qui permet de savoir si le programme doit afficher les instructions avec erreur ou non */
	public static boolean AFFICHER_ERREURS = false;
	
	/**
	 * Affiche l'erreur dans la console si AFFICHE_ERREUR est vrai
	 * @param chaine La chaîne à afficher
	 */
	static void afficherErreur(String chaine) {
		if (AFFICHER_ERREURS) {
			System.out.print(chaine);
		}
	}
	
	/* =========
	 * Interface
	 * ========= */
	
	RelayeurDInstructions relayeurActuel;

	
	/**
	 * Crée un déchiffreur qui utilise l'exécuteur donné
	 * @param executeur L'exécuteur d'instructions
	 */
	public DechiffreurInstructions(ExecuteurInstructions executeur) {
		relayeurActuel = new RelayeurDechiffreur(executeur);
	}
	
	/**
	 * Appelle la méthode de l'exécuteur associé à l'instruction donnée
	 * @param instruction L'instruction à exécuteur
	 */
	public void executer(RMInstruction instruction) {
		relayeurActuel.traiter(instruction, this);
	}
	
	/**
	 * Exécute les instructions données les unes aprés les autres
	 * @param instructions La liste des instructions
	 */
	public void executer(List<RMInstruction> instructions) {
		instructions.forEach(this::executer);
	}
}
