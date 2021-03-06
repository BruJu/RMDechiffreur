package fr.bruju.rmdechiffreur.modele;

import java.util.function.Function;

public interface ValeurGauche {
	public <T> T appliquerG(Function<Variable, T> variable,
							Function<VariablePlage, T> plage,
							Function<Pointeur, T> pointeur);
}
