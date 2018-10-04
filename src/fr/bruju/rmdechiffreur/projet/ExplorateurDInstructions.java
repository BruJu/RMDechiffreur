package fr.bruju.rmdechiffreur.projet;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import fr.bruju.lcfreader.rmobjets.RMEvenement;
import fr.bruju.lcfreader.rmobjets.RMEvenementCommun;
import fr.bruju.lcfreader.rmobjets.RMInstruction;
import fr.bruju.lcfreader.rmobjets.RMMap;
import fr.bruju.lcfreader.rmobjets.RMPage;
import fr.bruju.rmdechiffreur.ExecuteurInstructions;
import fr.bruju.rmdechiffreur.Utilitaire.TriConsumer;
import fr.bruju.rmdechiffreur.reference.ReferenceEC;
import fr.bruju.rmdechiffreur.reference.ReferenceMap;

public interface ExplorateurDInstructions {

	/**
	 * Exécute l'exécuteur sur toutes les instructions données
	 * @param executeur L'exécuteur
	 * @param instructions La liste d'instructions
	 */
	void executer(ExecuteurInstructions executeur, List<RMInstruction> instructions);

	/**
	 * Exécute l'exécuteur sur l'évènement à l'endroit donné
	 * @param executeur L'exécuteur
	 * @param idMap Numéro de la map
	 * @param idEvenement Numéro de l'évènement
	 * @param idPage Numéro de la page
	 */
	void lireEvenement(ExecuteurInstructions executeur, int idMap, int idEvenement, int idPage);

	/**
	 * Exécute l'exécuteur sur l'évènement commun donné
	 * @param executeur L'exécuteur
	 * @param idEvenement Le numéro de l'évènement commun
	 */
	void lireEvenementCommun(ExecuteurInstructions executeur, int idEvenement);

	/**
	 * Permet d'explorer tout un projet
	 * @param actionSurLesEvenementCommuns Action réalisée pour chaque évènement commun
	 */
	void explorerEvenementsCommuns(Consumer<RMEvenementCommun> actionSurLesEvenementCommuns);

	/**
	 * Permet d'explorer tout un projet
	 * @param actionSurLesPages Action réalisée pour chaque page d'évènement de chaque carte
	 */
	void explorerEvenements(TriConsumer<RMMap, RMEvenement, RMPage> actionSurLesPages);

	/**
	 * Permet d'explorer les évènements d'une carte
	 * @param actionSurLesPages Action réalisée pour chaque page d'évènement de la carte
	 */
	void explorerCarte(int idCarte, BiConsumer<RMEvenement, RMPage> actionSurLesPages);

	/**
	 * Permet d'explorer tous les évènements communs.
	 * @param generateur Une fonction qui à partir d'une référence indiquant l'identité de l'évènement commun crée
	 * l'exécuteur d'instructions associé
	 */
	void referencerEvenementsCommuns(Function<ReferenceEC, ExecuteurInstructions> generateur);

	/**
	 * Permet d'explorer toutes les cartes
	 * @param generateur Une fonction qui à partir d'une référence indiquant l'identité d'un évènement sur une carte,
	 * donne l'exécuteur d'instructions associé
	 */
	void referencerCartes(Function<ReferenceMap, ExecuteurInstructions> generateur);

}