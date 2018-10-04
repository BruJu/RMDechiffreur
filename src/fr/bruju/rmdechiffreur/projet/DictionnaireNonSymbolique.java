package fr.bruju.rmdechiffreur.projet;

import java.util.List;

/**
 * Un dictionnaire qui prend soin d'enlever les symboles (chaînes commencant par $) 
 * 
 * @author Bruju
 *
 */
public class DictionnaireNonSymbolique extends Dictionnaire {
	/**
	 * Crée un dictionnaire enlevant les symboles
	 * @param listeDeNoms La liste de noms
	 */
	public DictionnaireNonSymbolique(List<String> listeDeNoms) {
		super(listeDeNoms);
	}

	/**
	 * Renvoie " " si l'index est 0
	 * <br>
	 * Sinon renvoie la chaîne à la valeur index - 1
	 * <br>
	 * Si il y a un symbole (chaîne commencant par $ et suivi d'un caractère), il est retiré
	 * @param index La ligne voulue du dictionnaire
	 * @return Une représentation de la donnée à la ligne index
	 */
	@Override
	public String extraire(int index) {
		if (index == 0)
			return " ";
		
		String valeur = super.extraire(index);
		
		// Retrait du symbole
		if (valeur.startsWith("$") && valeur.length() >= 2)
			valeur = valeur.substring(2);
		
		// Retrait de l'espace éventuel après le symbole
		if (valeur.startsWith(" ")) {
			valeur = valeur.substring(1);
		}
		
		return valeur;
	}
}
