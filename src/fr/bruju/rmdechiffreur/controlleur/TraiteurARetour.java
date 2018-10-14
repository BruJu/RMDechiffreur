package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.rmdechiffreur.ExecuteurInstructions;

public interface TraiteurARetour extends Traiteur {
	boolean traiter(ExecuteurInstructions executeur, int[] parametres, String chaine);
	RelayeurBloquantSimple creerIgnorance();
	
	@Override
	default int executer(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		return traiter(executeur, parametres, chaine) ? 0 : 1;
	}
	
	@Override
	default RelayeurDInstructions relayer(int resultatExecution, RelayeurDInstructions relayeurActuel) {
		return resultatExecution == 0 ? relayeurActuel : creerIgnorance();
	}
}
