package fr.bruju.rmeventreader.rmdechiffreur.modele;

import java.util.function.Function;

public interface ValeurMembre {
	public <T> T appliquerMembre(Function<Tous, T> fonctionTous,
			Function<ValeurFixe, T> fonctionFixe,
			Function<Variable, T> fonctionVariable);
}
