# RMDechiffreur

## Objectif

Ce projet a pour but d'offrir une interface agréable pour l'interpréation d'instructions RPG Maker 2003.

Un jeu RPG Maker est constitué de fichiers qui décrivent, entre autres, des suites d'instructions. Toutes les
instructions sont codées avec le code de l'instruction, une chaîne et un tableau de nombres. Ce format force à
connaître la signification de chaque nombre et de chaque code.

L'objectif est ici de proposer une interface qui permet de transformer des instructions avec des paramètres abstraits :

Par exemple
* [10110, "Chaton", pas de nombre] deviendrait afficherMessage("Chaton");
* [11550, "miaou.wav", {70, 100, 50}] deviendrait jouerEffetSonore(fichier : "miaou.wav", volume: 70%, tempo: 100%, balance: 50 ) en documentant que la balance signifie l'endroit d'où on entend le son (50 = milieu, 0 = seulement à gauche, 100 = seulement à droite).

## Utilisation

La classe Projet regroupe toutes les méthodes permettant d'interpréter les fichiers de manière générique. Par exemple
lire tous les objets de la base.

Certaines méthodes exigent d'implémenter un objet de type "ExecuterInstrutions". Cette interface englobe la totalité
des instructions pouvant être appelées hors d'un combat. L'objectif est d'implémenter seulement les instructions
interessantes.

Certains paramètres sont des interfaces que l'on peut soit visiter, soit exploiter à travers une méthode prenant un
comportement par type. Dans certains cas (les conditions et le changement de variables / d'interrupteurs), il est
possible d'implémenter les interface ExtChangeVariable et ExtCondition pour implémenter seulement les comportements
des possibilités qui nous interessent.

Des exemples d'implémentations sont disponibles dans ![RMEventReader](https://github.com/BruJu/RMEventMonsterReader).


### Exemple : Lama

On veut compter le nombre de fois où une ligne affichant "Lama" est présente sur les cartes de "c:\MonSuperJeu\".
Contrairement à JavaLCFReader, nous n'avons pas besoin de connaître le numéro des instructions d'affichage de messages.

```
public class AfficheLama {
	public static void main(String[] args) {
		String dossierDuJeu = "c:\\MonSuperJeu\\";
		Projet projet = new Projet(dossierDuJeu);

		CompteLama compteurDeLamas = new CompteLama();
		projet.referencerCartes(reference -> compteurDeLamas);

		System.out.println("Il y a " + compteurDeLamas.getNombreDeLamas() + " lignes affichant Lama dans le projet.");
	}


	public static class CompteLama implements ExecuteurInstructions {
		private final String LAMA = "Lama";
		private int nombreDeLamas = 0;

		public int getNombreDeLamas() {
			return nombreDeLamas;
		}

		@Override
		public boolean getBooleenParDefaut() {
			// Méthode appelée pour les comportements par défauts (vides) d'instructions possédant des sous
			// branches (exemple : if / elseif / end -> faut il explorer ou non le contenu du si et du sinon ?)
			return true;
		}

		@Override
		public void Messages_afficherMessage(String chaine) {
			// Méthode appellée lorsque une instruction "afficher un nouveau message" est appelé
			if (LAMA.equals(chaine)) {
				nombreDeLamas++;
			}
		}

		@Override
		public void Messages_afficherSuiteMessage(String chaine) {
			// Méthode appellée lorsque une instruction "afficher la suite d'un message en cours" est appelé
			if (LAMA.equals(chaine)) {
				nombreDeLamas++;
			}
		}
	}
}
```

### Exemple : Modification de variables

On souhaite désormais savoir quels sont les évènements qui modifient chaque variable et les afficher.

```
	public static void main(String[] args) {
		String dossierDuJeu = "c:\\MonSuperJeu\\";
		Projet projet = new Projet(dossierDuJeu);
		projet.referencerCartes(ModificationDUneVariable::new);
	}


	public static class ModificationDUneVariable implements ExecuteurInstructions {
		private final Reference reference;

		public ModificationDUneVariable(Reference reference) {
			this.reference = reference;
		}

		@Override
		public boolean getBooleenParDefaut() {
			return true;
		}

		@Override
		public void Variables_affecterVariable(ValeurGauche valeurGauche, ValeurDroiteVariable valeurDroite) {
			// appliquerG : premier paramètre : fonction appelée si valeurGauche est une variable. Second paramètre :
			// fonction appelée si valeurGauche est une plage de variables. Troisième paramètre : Fonction appelée si
			// valeurGauche est un pointeur
			valeurGauche.appliquerG(this::afficherModification, null, null);
		}

		@Override
		public void Variables_changerVariable(ValeurGauche valeurGauche, OpMathematique operateur,
											  ValeurDroiteVariable valeurDroite) {
			valeurGauche.appliquerG(this::afficherModification, null, null);
		}

		private Void afficherModification(Variable variable) {
			System.out.println(reference.getString() + " modifie la variable " + variable.idVariable);
			return null;
		}
	}
```

### Exemple : Modfication de variables (2)

La construction précédente n'est pas très élégante. Il existe deux interfaces : ExtChangeVariable et ExtCondition qui
éclatent les instructions de modifications de variables et les conditions (elles permettent d'implémenter des fonctions
qui seront appellées pour chaque possibilité de combinaison de type de valeur gauche / de valeur droite / de type de
conditions). Les implémenter permet d'implémenter uniquement les cas qui nous interessent.

```
	public static void main(String[] args) {
		String dossierDuJeu = "c:\\MonSuperJeu\\";
		Projet projet = new Projet(dossierDuJeu);

		projet.referencerCartes(ModificationDUneVariable::new);
	}


	public static class ModificationDUneVariable implements ExecuteurInstructions, ExtChangeVariable.SansAffectation {
		private final Reference reference;

		public ModificationDUneVariable(Reference reference) {
			this.reference = reference;
		}

		@Override
		public boolean getBooleenParDefaut() {
			return true;
		}

		@Override
		public void changerVariable(Variable valeurGauche, OpMathematique operateur, ValeurFixe valeurDroite) {
			System.out.println(reference.getString() + " modifie la variable " + valeurGauche.idVariable
					+ " avec la valeur " + valeurDroite.valeur + " utilisant l'opérateur " + operateur);
		}

		@Override
		public void changerVariable(Variable valeurGauche, OpMathematique operateur, Variable valeurDroite) {
			System.out.println(reference.getString() + " modifie la variable " + valeurGauche.idVariable
					+ " avec la valeur de la variable " + valeurDroite.idVariable + " en utilisant l'opérateur"
					+ operateur);
		}
	}
```

Ici, on affiche un message si une variable est modifiée par rapport à une constante ou à une autre variable, et on
affiche le détail de l'opération.

L'éclatement est motivé par le fait que souvent, on aura des connaissance sur les instructions analysées : absence d'une
grande majorité des cas possibles. On ne souhaite en réalité souvent implémenter que quelques instructions.

En effet, le but de ce projet est globalement de pouvoir analyser plus facilement du code. Si on a 10000 enchaînement
de ligne ne comportant que des conditions la variable 1, et qu'on sait que dans le si de ces conditions, on aura des
affectations de valeurs constantes, on souhaite générer un tableau qui nous dira quels sont les valeurs prises par
les variables selon la variable 1. Dans ce contexte, on a besoin juste de regarder les conditions sur une variable et
les modifications des valeurs des variables par une valeur constante.

Devoir prévoir le cas où on modifie une variable selon un équipement porté par un héros ou si une ligne "retour à
l'écran titre" apparait est donc une contrainte pour l'utilisateur. Ainsi, le fait que les instructions aient des
implémentations vides par défaut facilite l'utilisation.



## Dépendances

Ce projet est dépendant de ![JavaLCFReader](https://github.com/BruJu/JavaLCFReader) pour la lecture des fichiers LCF.


## Crédits

* **Développement du projet par Julian Bruyat** : Ce projet a été réalisé lors du second semestre de l'année 2018.
