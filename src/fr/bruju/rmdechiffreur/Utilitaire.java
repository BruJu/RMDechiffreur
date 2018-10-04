package fr.bruju.rmdechiffreur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Ensemble de fonctions utilitaires
 * 
 * @author Bruju
 *
 */
public class Utilitaire {
	/**
	 * Effectue un traîtement en prenant trois arguments
	 * @author Bruju
	 *
	 * @param <A> Type du premier argument
	 * @param <B> Type du second argument
	 * @param <C> Type du troisième argument
	 */
	public static interface TriConsumer<A, B, C> {
		/**
		 * Applique la procédure sur les trois arguments
		 * @param a Premier argument
		 * @param b Second argument
		 * @param c Troisième argument
		 */
		public void consume(A a, B b, C c);
	}

	
	/**
	 * Ecrit dans le fichier dont le chemin est spécifié la chaîne à écrire
	 * @param chemin Le fichier
	 * @param chaineAEcrire La chaîne
	 */
	public static void Fichier_Ecrire(String chemin, String chaineAEcrire) {
		File f = new File(chemin);

		try {
			f.createNewFile();
			FileWriter ff = new FileWriter(f);
			ff.write(chaineAEcrire);
			ff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Donne la position de l'élément dans le tableau, ou -1 si il est absent
	 * @param element L'élément à chercher
	 * @param elements Le tableau de nombres
	 * @return La position du nombre
	 */
	public static int getPosition(int element, int[] elements) {
		for (int i = 0 ; i != elements.length ; i++) {
			if (elements[i] == element) {
				return i;
			}
		}
		
		return -1;
	}
}
