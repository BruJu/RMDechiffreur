package fr.bruju.rmdechiffreur.controlleur;

import fr.bruju.rmdechiffreur.ExecuteurInstructions;
import fr.bruju.rmdechiffreur.modele.Comparateur;
import fr.bruju.rmdechiffreur.modele.Condition;
import fr.bruju.rmdechiffreur.modele.ValeurFixe;
import fr.bruju.rmdechiffreur.modele.Variable;
import fr.bruju.rmdechiffreur.modele.Condition.CondArgent;
import fr.bruju.rmdechiffreur.modele.Condition.CondChrono;
import fr.bruju.rmdechiffreur.modele.Condition.CondDirection;
import fr.bruju.rmdechiffreur.modele.Condition.CondEventDemarreParAppui;
import fr.bruju.rmdechiffreur.modele.Condition.CondHerosAAuMoinsHp;
import fr.bruju.rmdechiffreur.modele.Condition.CondHerosAPourNom;
import fr.bruju.rmdechiffreur.modele.Condition.CondHerosAStatut;
import fr.bruju.rmdechiffreur.modele.Condition.CondHerosDansLEquipe;
import fr.bruju.rmdechiffreur.modele.Condition.CondHerosNiveauMin;
import fr.bruju.rmdechiffreur.modele.Condition.CondHerosPossedeObjet;
import fr.bruju.rmdechiffreur.modele.Condition.CondHerosPossedeSort;
import fr.bruju.rmdechiffreur.modele.Condition.CondInterrupteur;
import fr.bruju.rmdechiffreur.modele.Condition.CondMusiqueJoueePlusDUneFois;
import fr.bruju.rmdechiffreur.modele.Condition.CondObjet;
import fr.bruju.rmdechiffreur.modele.Condition.CondVehiculeUtilise;

/**
 * Classe implémentant un visiteur par défaut des conditions. Le but étant de permettre d'implémenter facilement les
 * conditions que l'on souhaite.
 * 
 * @author Bruju
 *
 */
public interface ExtCondition extends ExecuteurInstructions {
	@Override
	public default boolean Flot_si(Condition condition) {
		return condition.accept(this);
	}
	
	/**
	 * Booléen retourné par défaut pour les conditions non traitées
	 * @return Le booléen retourné par défaut pour les conditions non traitées
	 */
	public default boolean conditionRetourDeBase() {
		return getBooleenParDefaut();
	}

	public default boolean herosStatut(CondHerosAStatut condHerosAStatut) {
		return conditionRetourDeBase();
	}

	public default boolean herosObjet(CondHerosPossedeObjet condHerosPossedeObjet) {
		return conditionRetourDeBase();
	}

	public default boolean herosSort(CondHerosPossedeSort condHerosPossedeSort) {
		return conditionRetourDeBase();
	}

	public default boolean herosVivant(CondHerosAAuMoinsHp condHerosAAuMoinsHp) {
		return conditionRetourDeBase();
	}

	public default boolean herosNiveau(CondHerosNiveauMin condHerosNiveauMin) {
		return conditionRetourDeBase();
	}

	public default boolean herosNomme(CondHerosAPourNom condHerosAPourNom) {
		return conditionRetourDeBase();
	}

	public default boolean herosPresent(CondHerosDansLEquipe condHerosDansLEquipe) {
		return conditionRetourDeBase();
	}

	public default boolean musiqueABoucle(CondMusiqueJoueePlusDUneFois condMusiqueJoueePlusDUneFois) {
		return conditionRetourDeBase();
	}

	public default boolean eventDemarreParAppui(CondEventDemarreParAppui condEventDemarreParAppui) {
		return conditionRetourDeBase();
	}

	public default boolean vehicule(CondVehiculeUtilise condVehiculeUtilise) {
		return conditionRetourDeBase();
	}

	public default boolean direction(CondDirection condDirection) {
		return conditionRetourDeBase();
	}

	public default boolean objet(CondObjet condObjet) {
		return conditionRetourDeBase();
	}

	public default boolean argent(CondArgent condArgent) {
		return conditionRetourDeBase();
	}

	public default boolean chrono(CondChrono condChrono) {
		return conditionRetourDeBase();
	}

	public default boolean interrupteur(CondInterrupteur condInterrupteur) {
		return conditionRetourDeBase();
	}

	public default boolean variableVariable(int variable, Comparateur comparateur, Variable droite) {
		return conditionRetourDeBase();
	}

	public default boolean variableFixe(int variable, Comparateur comparateur, ValeurFixe droite) {
		return conditionRetourDeBase();
	}
}
