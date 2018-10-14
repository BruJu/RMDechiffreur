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
	public default int Flot_si(Condition condition) {
		return condition.accept(this);
	}
	
	/**
	 * Booléen retourné par défaut pour les conditions non traitées
	 * @return Le booléen retourné par défaut pour les conditions non traitées
	 */
	public default int conditionRetourDeBase() {
		return getBooleenParDefaut() ? 0 : 3;
	}

	public default int herosStatut(CondHerosAStatut condHerosAStatut) {
		return conditionRetourDeBase();
	}

	public default int herosObjet(CondHerosPossedeObjet condHerosPossedeObjet) {
		return conditionRetourDeBase();
	}

	public default int herosSort(CondHerosPossedeSort condHerosPossedeSort) {
		return conditionRetourDeBase();
	}

	public default int herosVivant(CondHerosAAuMoinsHp condHerosAAuMoinsHp) {
		return conditionRetourDeBase();
	}

	public default int herosNiveau(CondHerosNiveauMin condHerosNiveauMin) {
		return conditionRetourDeBase();
	}

	public default int herosNomme(CondHerosAPourNom condHerosAPourNom) {
		return conditionRetourDeBase();
	}

	public default int herosPresent(CondHerosDansLEquipe condHerosDansLEquipe) {
		return conditionRetourDeBase();
	}

	public default int musiqueABoucle(CondMusiqueJoueePlusDUneFois condMusiqueJoueePlusDUneFois) {
		return conditionRetourDeBase();
	}

	public default int eventDemarreParAppui(CondEventDemarreParAppui condEventDemarreParAppui) {
		return conditionRetourDeBase();
	}

	public default int vehicule(CondVehiculeUtilise condVehiculeUtilise) {
		return conditionRetourDeBase();
	}

	public default int direction(CondDirection condDirection) {
		return conditionRetourDeBase();
	}

	public default int objet(CondObjet condObjet) {
		return conditionRetourDeBase();
	}

	public default int argent(CondArgent condArgent) {
		return conditionRetourDeBase();
	}

	public default int chrono(CondChrono condChrono) {
		return conditionRetourDeBase();
	}

	public default int interrupteur(CondInterrupteur condInterrupteur) {
		return conditionRetourDeBase();
	}

	public default int variableVariable(int variable, Comparateur comparateur, Variable droite) {
		return conditionRetourDeBase();
	}

	public default int variableFixe(int variable, Comparateur comparateur, ValeurFixe droite) {
		return conditionRetourDeBase();
	}
}
