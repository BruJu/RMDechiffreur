package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.rmdechiffreur.ExecuteurInstructions;

/**
 * Interface fonctionnelle redéfinissant les traiteurs afin de ne renvoyer aucune valeur. Ces instructions ne bloquent
 * en effet jamais la lecture d'instructions futures.
 *  
 * @author Bruju
 *
 */
interface TraiteurSansRetour extends Traiteur {
	/**
	 * Exécute l'instruction
	 * @param executeur L'exécuteur
	 * @param parametres Les paramètres de l'instruction
	 * @param chaine La chaîne donnée en argument
	 */
	void executionVoid(ExecuteurInstructions executeur, int[] parametres, String chaine);

	@Override
	default int executer(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		executionVoid(executeur, parametres, chaine);
		return 0;
	}

	@Override
	default RelayeurDInstructions relayer(int resultatExecution, RelayeurDInstructions relayeurActuel) {
		return relayeurActuel;
	}
}
