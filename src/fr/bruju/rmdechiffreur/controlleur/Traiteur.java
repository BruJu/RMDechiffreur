package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.rmdechiffreur.ExecuteurInstructions;

/**
 * Un traiteur est une interface donnant le moyen d'exécuter une méthode selon des paramètres et une chaîne. Si la
 * méthode de traitement renvoie faux, cela signifie que le bloc souhaite 
 * 
 * @author Bruju
 *
 */
interface Traiteur {	
	public int executer(ExecuteurInstructions executeur, int[] parametres, String chaine);
	
	public RelayeurDInstructions relayer(int resultatExecution, RelayeurDInstructions relayeurActuel);
}
