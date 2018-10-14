package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.lcfreader.rmobjets.RMInstruction;

public interface RelayeurDInstructions {
	public void traiter(RMInstruction instruction, DechiffreurInstructions dechiffreurInstructions);
}
