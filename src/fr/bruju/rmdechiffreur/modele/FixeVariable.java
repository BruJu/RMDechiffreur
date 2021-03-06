package fr.bruju.rmdechiffreur.modele;
import java.util.function.Function;

public interface FixeVariable {
	public <T> T appliquerFV(Function<ValeurFixe, T> fonctionFixe, Function<Variable, T> fonctionVariable);
}
