package fr.bruju.rmdechiffreur;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import fr.bruju.lcfreader.rmobjets.RMEvenement;
import fr.bruju.lcfreader.rmobjets.RMEvenementCommun;
import fr.bruju.lcfreader.rmobjets.RMInstruction;
import fr.bruju.lcfreader.rmobjets.RMMap;
import fr.bruju.lcfreader.rmobjets.RMPage;
import fr.bruju.lcfreader.services.LecteurDeLCF;
import fr.bruju.rmdechiffreur.Utilitaire.TriConsumer;
import fr.bruju.rmdechiffreur.projet.Dictionnaire;
import fr.bruju.rmdechiffreur.projet.DictionnaireNonSymbolique;
import fr.bruju.rmdechiffreur.projet.Explorateur;
import fr.bruju.rmdechiffreur.projet.ExplorateurDInstructions;
import fr.bruju.rmdechiffreur.reference.ReferenceEC;
import fr.bruju.rmdechiffreur.reference.ReferenceMap;


/**
 * Cette classe centralise tous les services proposés. Elle permet d'explorer un projet en lisant les évènements
 * qu'elle contient au travers de diverses méthodes, ainsi que d'extraire certains noms de la base de données
 * (comme le nom d'un objet dont l'identifiant est connu, le nom d'un héros ...).
 * 
 * @author Bruju
 *
 */
public class Projet implements ExplorateurDInstructions {
	private final LecteurDeLCF lecteur;
	private final ExplorateurDInstructions explorateur;
	private final Dictionnaire[] dictionnaires;
	
	/**
	 * Crée un projet qui utilise les fichiers du chemin spécifié
	 * @param chemin Le chemin
	 */
	public Projet(String chemin) {
		lecteur = new LecteurDeLCF(chemin);
		explorateur = new Explorateur(lecteur);
		dictionnaires = new Dictionnaire[Dictionnaires.values().length];
	}
	
	/* ==========================
	 * Explorateur d'instructions
	 * ========================== */

	@Override
	public void executer(ExecuteurInstructions executeur, List<RMInstruction> instructions) {
        explorateur.executer(executeur, instructions);
    }

	@Override
	public void lireEvenement(ExecuteurInstructions executeur, int idMap, int idEvenement, int idPage) {
        explorateur.lireEvenement(executeur, idMap, idEvenement, idPage);
    }

	@Override
	public void lireEvenementCommun(ExecuteurInstructions executeur, int idEvenement) {
        explorateur.lireEvenementCommun(executeur, idEvenement);
    }

	@Override
	public void explorerEvenementsCommuns(Consumer<RMEvenementCommun> actionSurLesEvenementCommuns) {
        explorateur.explorerEvenementsCommuns(actionSurLesEvenementCommuns);
    }

	@Override
	public void explorerEvenements(TriConsumer<RMMap, RMEvenement, RMPage> actionSurLesPages) {
        explorateur.explorerEvenements(actionSurLesPages);
    }

	@Override
	public void explorerCarte(int idCarte, BiConsumer<RMEvenement, RMPage> actionSurLesPages) {
        explorateur.explorerCarte(idCarte, actionSurLesPages);
    }

	@Override
	public void referencerEvenementsCommuns(Function<ReferenceEC, ExecuteurInstructions> generateur) {
        explorateur.referencerEvenementsCommuns(generateur);
    }

	@Override
	public void referencerCartes(Function<ReferenceMap, ExecuteurInstructions> generateur) {
        explorateur.referencerCartes(generateur);
    }

	@Override
	public ReferenceEC getReference(int idEvenementCommun) {
		return explorateur.getReference(idEvenementCommun);
	}

	/* =============
	 * Dictionnaires
	 * ============= */

	/**
	 * Liste des dictionnaires connus
	 */
	public enum Dictionnaires {
		PERSONNAGE("actors", Dictionnaire::new),
		INTERRUPTEUR("switches", Dictionnaire::new),
		VARIABLE("variables", Dictionnaire::new),
		OBJET("items", DictionnaireNonSymbolique::new);
		
		private String nom;
		private Function<List<String>, Dictionnaire> instanciation;

		/**
		 * Crée le dictionnaire
		 * @param chaine Nom du champ dans le fichier RPG_RT.db
		 * @param instanciation Fonction d'instanciation du dictionnaire voulu (enlevant ou non les symboles)
		 */
		Dictionnaires(String chaine, Function<List<String>, Dictionnaire> instanciation) {
			this.nom = chaine;
			this.instanciation = instanciation;
		}
	}
	
	/**
	 * Donne le nom d'un héros
	 * @param index ID du héros
	 * @return Le nom du héros
	 */
	public String extraireHeros(int index) {
		return extraire(Dictionnaires.PERSONNAGE, index);
	}
	
	/**
	 * Donne le nom d'un interrupteur
	 * @param index Le numéro de l'interrupteur
	 * @return Le nom de l'interrupteur
	 */
	public String extraireInterrupteur(int index) {
		return extraire(Dictionnaires.INTERRUPTEUR, index);
	}
	
	/**
	 * Donne le nom de la variable
	 * @param index Le numéro de la variable
	 * @return Le nom de la variable
	 */
	public String extraireVariable(int index) {
		return extraire(Dictionnaires.VARIABLE, index);
	}
	
	/**
	 * Donne le nom de l'objet
	 * @param index Le numéro de l'objet
	 * @return Le nom de l'objet
	 */
	public String extraireObjet(int index) {
		return extraire(Dictionnaires.OBJET, index);
	}
	
	/**
	 * Extrait une donnée d'un dictionnaire
	 * @param dictionnaire Le dictionnaire
	 * @param index Le numéro de la donnée
	 * @return Le nom de la donnée
	 */
	public String extraire(Dictionnaires dictionnaire, int index) {
		assurerExistanceDictionnaire(dictionnaire);
		return dictionnaires[dictionnaire.ordinal()].extraire(index);
	}
	
	/**
	 * Ecrit le contenu de tous les dictionnaires dans le dossier
	 * @param dossier Le chemin vers le dossier
	 */
	public void ecrireRessource(String dossier) {
		for (Dictionnaires nom : Dictionnaires.values()) {
			assurerExistanceDictionnaire(nom);
			dictionnaires[nom.ordinal()].ecrireFichier(dossier + "db_" + nom.nom + ".txt");
		}
	}
	
	/**
	 * Crée le dictionnaire si il n'est pas déjà crée (implémentation paresseuse)
	 * @param dictionnaire Le dictionnaire
	 */
	private void assurerExistanceDictionnaire(Dictionnaires dictionnaire) {
		if (dictionnaires[dictionnaire.ordinal()] == null) {
			dictionnaires[dictionnaire.ordinal()] =
					dictionnaire.instanciation.apply(lecteur.getListeDeNoms(dictionnaire.nom));
		}
	}
}
