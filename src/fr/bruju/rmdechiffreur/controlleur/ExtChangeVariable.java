package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.rmdechiffreur.ExecuteurInstructions;
import fr.bruju.rmdechiffreur.modele.NombreObjet;
import fr.bruju.rmdechiffreur.modele.OpMathematique;
import fr.bruju.rmdechiffreur.modele.Pointeur;
import fr.bruju.rmdechiffreur.modele.ValeurAleatoire;
import fr.bruju.rmdechiffreur.modele.ValeurDeplacable;
import fr.bruju.rmdechiffreur.modele.ValeurDivers;
import fr.bruju.rmdechiffreur.modele.ValeurDroiteVariable;
import fr.bruju.rmdechiffreur.modele.ValeurFixe;
import fr.bruju.rmdechiffreur.modele.ValeurGauche;
import fr.bruju.rmdechiffreur.modele.Variable;
import fr.bruju.rmdechiffreur.modele.VariableHeros;
import fr.bruju.rmdechiffreur.modele.VariablePlage;

/**
 * Fourni une interface explicitant toutes les combinaisons possibles pour la modification de variables, permettant
 * d'implémenter aisément une implémentation partielle.
 * 
 * @author Bruju
 *
 */
public interface ExtChangeVariable extends ExecuteurInstructions {
	/**
	 * Transforme les affectations en changement de variables.
	 * 
	 * @author Bruju
	 */
	public interface SansAffectation extends ExtChangeVariable {
		@Override
		default void affecterVariable(Pointeur valeurGauche, ValeurDivers valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Variable valeurGauche, ValeurDivers valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Pointeur valeurGauche, ValeurDeplacable valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Variable valeurGauche, ValeurDeplacable valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Pointeur valeurGauche, VariableHeros valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Variable valeurGauche, VariableHeros valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Pointeur valeurGauche, NombreObjet valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Variable valeurGauche, NombreObjet valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Pointeur valeurGauche, ValeurAleatoire valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Variable valeurGauche, ValeurAleatoire valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Pointeur valeurGauche, Pointeur valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Variable valeurGauche, Pointeur valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Pointeur valeurGauche, Variable valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Variable valeurGauche, Variable valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Variable valeurGauche, ValeurFixe valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}

		@Override
		default void affecterVariable(Pointeur valeurGauche, ValeurFixe valeurDroite) {
			changerVariable(valeurGauche, OpMathematique.AFFECTATION, valeurDroite);
		}
	}

	/*
	 * Changement d'interrupteur
	 */
	@Override
	public default void Variables_changerSwitch(ValeurGauche valeurGauche, Boolean nouvelleValeur) {
		if (nouvelleValeur == null) {
			if (valeurGauche instanceof Variable) {
				inverseSwitch((Variable) valeurGauche);
			} else if (valeurGauche instanceof VariablePlage) {
				((VariablePlage) valeurGauche).getList().forEach(this::inverseSwitch);
			} else {
				inverseSwitch((Pointeur) valeurGauche);
			}
		} else {
			if (valeurGauche instanceof Variable) {
				changeSwitch((Variable) valeurGauche, nouvelleValeur);
			} else if (valeurGauche instanceof VariablePlage) {
				((VariablePlage) valeurGauche).getList().forEach(variable -> changeSwitch(variable, nouvelleValeur));
			} else {
				changeSwitch((Pointeur) valeurGauche, nouvelleValeur);
			}
		}
	}

	public default void inverseSwitch(Variable interrupteur) {
	}

	public default void changeSwitch(Variable interrupteur, boolean nouvelleValeur) {
	}

	public default void inverseSwitch(Pointeur interrupteur) {
	}

	public default void changeSwitch(Pointeur interrupteur, boolean nouvelleValeur) {
	}

	/*
	 * Changement de variable
	 */

	@Override
	public default void Variables_affecterVariable(ValeurGauche valeurGauche, ValeurDroiteVariable valeurDroite) {
		if (valeurGauche instanceof VariablePlage) {
			((VariablePlage) valeurGauche).getList()
				.forEach(variable -> Variables_affecterVariable(variable, valeurDroite));
			return;
		}

		if (valeurDroite instanceof ValeurFixe) {
			if (valeurGauche instanceof Variable) {
				affecterVariable((Variable) valeurGauche, (ValeurFixe) valeurDroite);
			} else {
				affecterVariable((Pointeur) valeurGauche, (ValeurFixe) valeurDroite);
			}
		} else if (valeurDroite instanceof Variable) {
			if (valeurGauche instanceof Variable) {
				affecterVariable((Variable) valeurGauche, (Variable) valeurDroite);
			} else {
				affecterVariable((Pointeur) valeurGauche, (Variable) valeurDroite);
			}
		} else if (valeurDroite instanceof Pointeur) {
			if (valeurGauche instanceof Variable) {
				affecterVariable((Variable) valeurGauche, (Pointeur) valeurDroite);
			} else {
				affecterVariable((Pointeur) valeurGauche, (Pointeur) valeurDroite);
			}
		} else if (valeurDroite instanceof ValeurAleatoire) {
			if (valeurGauche instanceof Variable) {
				affecterVariable((Variable) valeurGauche, (ValeurAleatoire) valeurDroite);
			} else {
				affecterVariable((Pointeur) valeurGauche, (ValeurAleatoire) valeurDroite);
			}
		} else if (valeurDroite instanceof NombreObjet) {
			if (valeurGauche instanceof Variable) {
				affecterVariable((Variable) valeurGauche, (NombreObjet) valeurDroite);
			} else {
				affecterVariable((Pointeur) valeurGauche, (NombreObjet) valeurDroite);
			}
		} else if (valeurDroite instanceof VariableHeros) {
			if (valeurGauche instanceof Variable) {
				affecterVariable((Variable) valeurGauche, (VariableHeros) valeurDroite);
			} else {
				affecterVariable((Pointeur) valeurGauche, (VariableHeros) valeurDroite);
			}
		} else if (valeurDroite instanceof ValeurDeplacable) {
			if (valeurGauche instanceof Variable) {
				affecterVariable((Variable) valeurGauche, (ValeurDeplacable) valeurDroite);
			} else {
				affecterVariable((Pointeur) valeurGauche, (ValeurDeplacable) valeurDroite);
			}
		} else if (valeurDroite instanceof ValeurDivers) {
			if (valeurGauche instanceof Variable) {
				affecterVariable((Variable) valeurGauche, (ValeurDivers) valeurDroite);
			} else {
				affecterVariable((Pointeur) valeurGauche, (ValeurDivers) valeurDroite);
			}
		}
	}

	public default void affecterVariable(Pointeur valeurGauche, ValeurDivers valeurDroite) {
	}

	public default void affecterVariable(Variable valeurGauche, ValeurDivers valeurDroite) {
	}

	public default void affecterVariable(Pointeur valeurGauche, ValeurDeplacable valeurDroite) {
	}

	public default void affecterVariable(Variable valeurGauche, ValeurDeplacable valeurDroite) {
	}

	public default void affecterVariable(Pointeur valeurGauche, VariableHeros valeurDroite) {
	}

	public default void affecterVariable(Variable valeurGauche, VariableHeros valeurDroite) {
	}

	public default void affecterVariable(Pointeur valeurGauche, NombreObjet valeurDroite) {
	}

	public default void affecterVariable(Variable valeurGauche, NombreObjet valeurDroite) {
	}

	public default void affecterVariable(Pointeur valeurGauche, ValeurAleatoire valeurDroite) {
	}

	public default void affecterVariable(Variable valeurGauche, ValeurAleatoire valeurDroite) {
	}

	public default void affecterVariable(Pointeur valeurGauche, Pointeur valeurDroite) {
	}

	public default void affecterVariable(Variable valeurGauche, Pointeur valeurDroite) {
	}

	public default void affecterVariable(Pointeur valeurGauche, Variable valeurDroite) {
	}

	public default void affecterVariable(Variable valeurGauche, Variable valeurDroite) {
	}

	public default void affecterVariable(Variable valeurGauche, ValeurFixe valeurDroite) {
	}

	public default void affecterVariable(Pointeur valeurGauche, ValeurFixe valeurDroite) {
	}

	/*
	 * Modification de variable
	 */


	@Override
	default void Variables_changerVariable(ValeurGauche valeurGauche, OpMathematique operateur,
			ValeurDroiteVariable valeurDroite) {
		if (valeurGauche instanceof VariablePlage) {
			((VariablePlage) valeurGauche).getList()
				.forEach(variable -> Variables_changerVariable(variable, operateur, valeurDroite));
			return;
		}

		if (valeurDroite instanceof ValeurFixe) {
			if (valeurGauche instanceof Variable) {
				changerVariable((Variable) valeurGauche, operateur, (ValeurFixe) valeurDroite);
			} else {
				changerVariable((Pointeur) valeurGauche, operateur, (ValeurFixe) valeurDroite);
			}
		} else if (valeurDroite instanceof Variable) {
			if (valeurGauche instanceof Variable) {
				changerVariable((Variable) valeurGauche, operateur, (Variable) valeurDroite);
			} else {
				changerVariable((Pointeur) valeurGauche, operateur, (Variable) valeurDroite);
			}
		} else if (valeurDroite instanceof Pointeur) {
			if (valeurGauche instanceof Variable) {
				changerVariable((Variable) valeurGauche, operateur, (Pointeur) valeurDroite);
			} else {
				changerVariable((Pointeur) valeurGauche, operateur, (Pointeur) valeurDroite);
			}
		} else if (valeurDroite instanceof ValeurAleatoire) {
			if (valeurGauche instanceof Variable) {
				changerVariable((Variable) valeurGauche, operateur, (ValeurAleatoire) valeurDroite);
			} else {
				changerVariable((Pointeur) valeurGauche, operateur, (ValeurAleatoire) valeurDroite);
			}
		} else if (valeurDroite instanceof NombreObjet) {
			if (valeurGauche instanceof Variable) {
				changerVariable((Variable) valeurGauche, operateur, (NombreObjet) valeurDroite);
			} else {
				changerVariable((Pointeur) valeurGauche, operateur, (NombreObjet) valeurDroite);
			}
		} else if (valeurDroite instanceof VariableHeros) {
			if (valeurGauche instanceof Variable) {
				changerVariable((Variable) valeurGauche, operateur, (VariableHeros) valeurDroite);
			} else {
				changerVariable((Pointeur) valeurGauche, operateur, (VariableHeros) valeurDroite);
			}
		} else if (valeurDroite instanceof ValeurDeplacable) {
			if (valeurGauche instanceof Variable) {
				changerVariable((Variable) valeurGauche, operateur, (ValeurDeplacable) valeurDroite);
			} else {
				changerVariable((Pointeur) valeurGauche, operateur, (ValeurDeplacable) valeurDroite);
			}
		} else if (valeurDroite instanceof ValeurDivers) {
			if (valeurGauche instanceof Variable) {
				changerVariable((Variable) valeurGauche, operateur, (ValeurDivers) valeurDroite);
			} else {
				changerVariable((Pointeur) valeurGauche, operateur, (ValeurDivers) valeurDroite);
			}
		}
	}

	public default void changerVariable(Pointeur valeurGauche, OpMathematique operateur, ValeurDivers valeurDroite) {
	}

	public default void changerVariable(Variable valeurGauche, OpMathematique operateur, ValeurDivers valeurDroite) {
	}

	public default void changerVariable(Pointeur valeurGauche, OpMathematique operateur,
			ValeurDeplacable valeurDroite) {
	}

	public default void changerVariable(Variable valeurGauche, OpMathematique operateur,
			ValeurDeplacable valeurDroite) {
	}

	public default void changerVariable(Pointeur valeurGauche, OpMathematique operateur, VariableHeros valeurDroite) {
	}

	public default void changerVariable(Variable valeurGauche, OpMathematique operateur, VariableHeros valeurDroite) {
	}

	public default void changerVariable(Pointeur valeurGauche, OpMathematique operateur, NombreObjet valeurDroite) {
	}

	public default void changerVariable(Variable valeurGauche, OpMathematique operateur, NombreObjet valeurDroite) {
	}

	public default void changerVariable(Pointeur valeurGauche, OpMathematique operateur, ValeurAleatoire valeurDroite) {
	}

	public default void changerVariable(Variable valeurGauche, OpMathematique operateur, ValeurAleatoire valeurDroite) {
	}

	public default void changerVariable(Pointeur valeurGauche, OpMathematique operateur, Pointeur valeurDroite) {
	}

	public default void changerVariable(Variable valeurGauche, OpMathematique operateur, Pointeur valeurDroite) {
	}

	public default void changerVariable(Pointeur valeurGauche, OpMathematique operateur, Variable valeurDroite) {
	}

	public default void changerVariable(Variable valeurGauche, OpMathematique operateur, Variable valeurDroite) {
	}

	public default void changerVariable(Variable valeurGauche, OpMathematique operateur, ValeurFixe valeurDroite) {
	}

	public default void changerVariable(Pointeur valeurGauche, OpMathematique operateur, ValeurFixe valeurDroite) {
	}
}
