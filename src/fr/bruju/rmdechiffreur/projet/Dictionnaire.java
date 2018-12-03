package fr.bruju.rmdechiffreur.projet;

import java.util.Collections;
import java.util.List;

import fr.bruju.rmdechiffreur.Utilitaire;

/**
 * Représente une liste de noms
 * 
 * @author Bruju
 *
 */
public class Dictionnaire {
	/** Les noms */
	private final List<String> donneesExtraites;

	/**
	 * Crée un dictionnaire avec la liste de noms donnée
	 * @param listeDeNoms La liste de noms
	 */
	public Dictionnaire(List<String> listeDeNoms) {
		donneesExtraites = listeDeNoms;
	}

	/**
	 * Extrait un nom de la liste
	 * @param index L'index
	 * @return Le nom associé
	 */
	public String extraire(int index) {
		return donneesExtraites.get(index - 1);
	}

	/**
	 * Renvoie la liste de toutes les entrées dans une liste non modifiable
	 * @return La liste de toutes les entrées
	 */
	public List<String> getListe() {
		return Collections.unmodifiableList(donneesExtraites);
	}

	/**
	 * Ecrit dans le fichier destination tous les noms dans la liste (un par ligne)
	 * @param fichierDestination Le fichier destination
	 */
	public void ecrireFichier(String fichierDestination) {
		StringBuilder sb = new StringBuilder();
		for (String valeur : donneesExtraites) {
			sb.append(valeur).append("\n");
		}
		
		Utilitaire.Fichier_Ecrire(fichierDestination, sb.toString());
	}
}