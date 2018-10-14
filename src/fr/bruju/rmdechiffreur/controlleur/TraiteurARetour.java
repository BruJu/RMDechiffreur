package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.rmdechiffreur.ExecuteurInstructions;

public interface TraiteurARetour extends Traiteur {
	boolean traiter(ExecuteurInstructions executeur, int[] parametres, String chaine);
	
	@Override
	default int executer(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		return traiter(executeur, parametres, chaine) ? 0 : 1;
	}
}
