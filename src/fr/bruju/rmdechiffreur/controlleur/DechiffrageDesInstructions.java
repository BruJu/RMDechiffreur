package fr.bruju.rmdechiffreur.controlleur;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import fr.bruju.rmdechiffreur.ExecuteurInstructions;
import fr.bruju.rmdechiffreur.Utilitaire;
import fr.bruju.rmdechiffreur.modele.ArrierePlanCombat;
import fr.bruju.rmdechiffreur.modele.Comparateur;
import fr.bruju.rmdechiffreur.modele.Condition;
import fr.bruju.rmdechiffreur.modele.Couleur;
import fr.bruju.rmdechiffreur.modele.Deplacement;
import fr.bruju.rmdechiffreur.modele.EvenementDeplacable;
import fr.bruju.rmdechiffreur.modele.ExecEnum;
import fr.bruju.rmdechiffreur.modele.FixeVariable;
import fr.bruju.rmdechiffreur.modele.NombreObjet;
import fr.bruju.rmdechiffreur.modele.OpMathematique;
import fr.bruju.rmdechiffreur.modele.Pointeur;
import fr.bruju.rmdechiffreur.modele.SonParam;
import fr.bruju.rmdechiffreur.modele.Tous;
import fr.bruju.rmdechiffreur.modele.ValeurAleatoire;
import fr.bruju.rmdechiffreur.modele.ValeurDeplacable;
import fr.bruju.rmdechiffreur.modele.ValeurDivers;
import fr.bruju.rmdechiffreur.modele.ValeurDroiteVariable;
import fr.bruju.rmdechiffreur.modele.ValeurFixe;
import fr.bruju.rmdechiffreur.modele.ValeurGauche;
import fr.bruju.rmdechiffreur.modele.ValeurMembre;
import fr.bruju.rmdechiffreur.modele.Variable;
import fr.bruju.rmdechiffreur.modele.VariableHeros;
import fr.bruju.rmdechiffreur.modele.VariablePlage;
import fr.bruju.rmdechiffreur.modele.ExecEnum.SujetTransition;
import fr.bruju.rmdechiffreur.modele.ExecEnum.Vehicule;

/**
 * Cette classe regroupe l'ensemble des déchiffrages des instructions pour appeler des fonctions ayant des noms et des
 * arguments plus explicites afin de faciliter la programmation.
 * 
 * @author Bruju
 *
 */
public class DechiffrageDesInstructions {
	/** Permet d'adapter la Map<Integer, Traiteur> pour utiliser put des TraiteurSansRetour exprimés en lambda */
	private static interface RedirectionDeMapSans {
		public void put(int code, TraiteurSansRetour traiteur);
	}
	
	/** Permet d'adapter la Map<Integer, Traiteur> pour utiliser put des TraiteurARetour exprimés en lambda */
	private static interface RedirectionDeMapRetour {
		public void put(int code, TraiteurARetour traiteur);
	}

	/**
	 * Donne la correspondance entre code et fonction de déchiffrage à exécuter
	 * 
	 * @return La correspondance entre code et fonction de déchiffrage à exécuter
	 */
	public Map<Integer, Traiteur> getTraiteurs() {
		Map<Integer, Traiteur> decodages = new HashMap<>();
		RedirectionDeMapSans classe1 = decodages::put;
		RedirectionDeMapRetour classe2 = decodages::put;

		// Instructions implémentées
		classe1.put(10, (e, t, c) -> {});
		classe1.put(10110, (e, t, c) -> e.Messages_afficherMessage(c));
		classe1.put(10120, this::$10120_changeMessageOptions);
		classe1.put(10130, this::$10130_afficherPortrait);
		classe2.put(10140, new $10140_initierQCM());
		classe1.put(10150, (e, t, c) -> e.SaisieMessages_saisieNombre(t[1], t[0]));
		classe1.put(10210, this::$10210_changerSwitch);
		classe1.put(10220, this::$10220_changerVariable);
		classe1.put(10230, this::$10230_modifierChrono);
		classe1.put(10310, this::$10310_modifierArgent);
		classe1.put(10320, this::$10320_modifierObjets);
		classe1.put(10330, this::$10330_modifierEquipe);
		classe1.put(10410, (e, p, c) -> $10410_modifierExperience(e, p, VariableHeros.Caracteristique.EXPERIENCE));
		classe1.put(10420, (e, p, c) -> $10410_modifierExperience(e, p, VariableHeros.Caracteristique.NIVEAU));
		classe1.put(10430, this::$10430_modifierStatistiqueStable);
		classe1.put(10440, this::$10440_modifierCompetence);
		classe1.put(10450, this::$10450_modifierEquipement);
		classe1.put(10460, this::$10460_modifierHP);
		classe1.put(10470, this::$10470_modifierMP);
		classe1.put(10480, this::$10480_modifierStatut);
		classe1.put(10490, this::$10490_soignerCompletement);
		classe1.put(10500, this::$10500_SimulerAttaque);
		classe1.put(10610, (e, p, s) -> e.Systeme_modifierNom(p[0], s));
		classe1.put(10620, (e, p, s) -> e.Systeme_modifierGrade(p[0], s));
		classe1.put(10630, this::$10630_modifierCharsetHeros);
		classe1.put(10640, (e, p, s) -> e.Systeme_modifierFaceset(p[0], s, p[1]));
		classe1.put(10650, (e, p, s) -> e.Systeme_modifierApparenceVehicule(dechiffreVehicule(p[0]), s, p[1]));
		classe1.put(10660, this::$10660_modifierMusique);
		classe1.put(10670, this::$10670_modifierEffetSonore);
		classe1.put(10680, this::$10680_modifierApparence);
		classe1.put(10690, this::$10690_modifierTransition);
		classe2.put(10710, new $10710_lancerCombat());
		classe1.put(10720, this::$10720_Magasin);
		classe1.put(10730, this::$10730_Auberge);
		classe1.put(10740, this::$10740_saisieDeNom);
		classe1.put(10810, this::$10810_teleporter);
		classe1.put(10820, this::$10820_memoriserPosition);
		classe1.put(10830, (e, p, s) -> e.Jeu_revenirPosition(p[0], p[1], p[2]));
		classe1.put(10840, (e, p, s) -> e.Jeu_entrerVehicule());
		classe1.put(10850, this::$10850_deplacerVehicule);
		classe1.put(10860, this::$10860_deplacerEvenement);
		classe1.put(10870, this::$10870_inverserEvenements);
		classe1.put(10910, this::$10910_stockerIdTerrain);
		classe1.put(10920, this::$10920_stockerIdEvenement);
		classe1.put(11010, this::$11010_effacerEcran);
		classe1.put(11020, this::$11020_afficherEcran);
		classe1.put(11030, this::$11030_modifierTonEcran);
		classe1.put(11040, this::$11040_flashEcran);
		classe1.put(11050, this::$11050_tremblementEcran);
		classe1.put(11060, this::$11060_defilement);
		classe1.put(11070, this::$11070_modifierMeteo);
		classe1.put(11110, this::$11110_AfficherImage);
		classe1.put(11120, this::$11120_DeplacerImage);
		classe1.put(11130, (e, p, s) -> e.Image_effacer(p[0]));
		classe1.put(11210, this::$12210_afficherAnimation);
		classe1.put(11310, (e, p, s) -> e.Jeu_transparenceHeros(p[0] == 0));
		classe1.put(11320, this::$11320_flasherEvenement);
		classe1.put(11330, this::$11330_deplacement);
		classe1.put(11340, (e, p, s) -> e.Jeu_toutDeplacer());
		classe1.put(11350, (e, p, s) -> e.Jeu_toutStopper());
		classe1.put(11410, this::$11410_attente);
		classe1.put(11510, this::$11510_JouerMusique);
		classe1.put(11520, (e, p, s) -> e.Media_arreterMusique(p[0]));
		classe1.put(11530, (e, p, s) -> e.Media_memoriserMusique());
		classe1.put(11540, (e, p, s) -> e.Media_jouerMusiqueMemorisee());
		classe1.put(11550, this::$11550_JouerSon);
		classe1.put(11560, this::$11560_JouerFilm);
		classe1.put(11610, this::$11610_appuiTouche);
		classe1.put(11710, (e, p, s) -> e.Jeu_modifierChipset(p[0]));
		classe1.put(11720, this::$11720_panorama);
		classe1.put(11740, (e, p, s) -> e.Jeu_modifierFrequenceRencontres(p[0]));
		classe1.put(11750, this::$11750_modifierCarreau);
		classe1.put(11810, this::$11810_modifierTeleporteur);
		classe1.put(11820, (e, p, s) -> e.Systeme_peutSeTeleporter(p[0] == 0));
		classe1.put(11830, this::$11830_pointDeFuite);
		classe1.put(11840, (e, p, s) -> e.Systeme_peutFuir(p[0] == 0));
		classe1.put(11910, (e, p, s) -> e.Jeu_ouvrirMenuSauvegarde());
		classe1.put(11930, (e, p, s) -> e.Systeme_peutSauvegarder(p[0] == 0));
		classe1.put(11950, (e, p, s) -> e.Jeu_ouvrirMenu());
		classe1.put(11960, (e, p, s) -> e.Systeme_peutOuvrirLeMenu(p[0] == 0));
		decodages.put(12010, new $12010_condition());
		classe1.put(12110, (e, p, s) -> e.Flot_etiquette(p[0]));
		classe1.put(12120, (e, p, s) -> e.Flot_sautEtiquette(p[0]));
		classe1.put(12210, (e, p, s) -> e.Flot_boucleDebut());
		classe1.put(12220, (e, p, s) -> e.Flot_boucleSortir());
		classe1.put(12310, (e, p, s) -> e.Flot_stopperCetEvenement());
		classe1.put(12320, (e, p, s) -> e.Flot_effacerCetEvenement());
		classe1.put(12330, this::$12330_Evenement);
		classe1.put(12410, (e, p, s) -> e.Flot_commentaire(s));
		classe1.put(12420, (e, p, s) -> e.Jeu_finDuJeu());
		classe1.put(12510, (e, p, s) -> e.Jeu_ecranTitre());
		classe1.put(20110, (e, t, c) -> e.Messages_afficherSuiteMessage(c));
		classe1.put(20140, (e, p, c) -> e.SaisieMessages_choixQCM(c, ExecEnum.ChoixQCM.values()[p[0] + 1]));
		classe1.put(20141, this::$20141_finQCM);
		classe1.put(20710, (e, p, s) -> e.Combat_brancheVictoire());
		classe1.put(20711, (e, p, s) -> e.Combat_brancheFuite());
		classe1.put(20712, (e, p, s) -> e.Combat_brancheDefaite());
		classe1.put(20713, (e, p, s) -> e.Combat_finBranche());
		classe1.put(20720, (e, p, s) -> e.Magasin_magasinBrancheNonVente());
		classe1.put(20720, (e, p, s) -> e.Magasin_magasinBrancheVente());
		classe1.put(20720, (e, p, s) -> e.Magasin_magasinFinBranche());
		classe1.put(20730, (e, p, s) -> e.Magasin_aubergeRepos());
		classe1.put(20731, (e, p, s) -> e.Magasin_aubergeNonRepos());
		classe1.put(20732, (e, p, s) -> e.Magasin_aubergeFinBranche());
		classe1.put(22010, (e, p, s) -> e.Flot_siNon());
		classe1.put(22011, (e, p, s) -> e.Flot_siFin());
		classe1.put(22210, (e, p, s) -> e.Flot_boucleFin());
		// RPG Maker 2003
		classe1.put(1008, this::$X1008_modifierClasse);
		classe1.put(1009, this::$X1009_modifierCommandes);

		return decodages;
	}

	// Les déchiffrages sont classés par code.
	// Aucune documentation n'est produite car elle serait redondante avec le code, et ces instrucions se contentent
	// de déchiffrer les instructions via un procédé découvert via des observations empiriques.

	private static class $10140_initierQCM implements TraiteurARetour {
		@Override
		public boolean traiter(ExecuteurInstructions executeur, int[] parametres, String chaine) {
			return executeur.SaisieMessages_initierQCM(ExecEnum.ChoixQCM.values()[parametres[0]]);
		}

		@Override
		public RelayeurDInstructions relayer(int resultatExecution, RelayeurDInstructions relayeurActuel) {
			return new RelayeurBloquantSimple(relayeurActuel, 10140, 20141);
		}
	}

	private static class $10710_lancerCombat implements TraiteurARetour {
		private static final int[] codeVictoire = new int[] {20720};
		
		@Override
		public RelayeurDInstructions relayer(int resultatExecution, RelayeurDInstructions relayeurActuel) {
			return new RelayeurBloquantSimple(relayeurActuel, 10710, 20713, codeVictoire);
		}

		@Override
		public boolean traiter(ExecuteurInstructions executeur, int[] parametres, String chaine) {
			FixeVariable idCombat = dechiffrerFixeVariable(parametres[0], parametres[1]);
			ExecEnum.ConditionsDeCombat conditions = ExecEnum.ConditionsDeCombat.values()[parametres[6]];
			ArrierePlanCombat arrierePlan = arrierePlan(parametres[2], parametres[7], parametres[8], chaine);
			boolean avantage = parametres[5] == 1;

			boolean defaiteAutorisee = parametres[4] == 1;
			ExecEnum.CombatComportementFuite fuite = ExecEnum.CombatComportementFuite.values()[parametres[3]];

			return executeur.Combat_lancerCombat(idCombat, conditions, arrierePlan, fuite, defaiteAutorisee, avantage);
		}
	}

	private static class $12010_condition implements Traiteur {
		@Override
		public int executer(ExecuteurInstructions executeur, int[] parametres, String chaine) {
			return executeur.Flot_si(dechiffrerCondition(parametres, chaine));
		}

		@Override
		public RelayeurDInstructions relayer(int resultatExecution, RelayeurDInstructions relayeurActuel) {
			return new RelayeurCondition(RelayeurDechiffreur.instance, resultatExecution);
		}
	}

	private void $10120_changeMessageOptions(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		executeur.Messages_modifierOptions(parametres[0] == 1, extrairePosition(parametres[1]), parametres[2] == 1,
				parametres[3] == 0);
	}

	private void $10130_afficherPortrait(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		String nomFaceset = chaine;

		if (nomFaceset.equals("")) {
			executeur.Messages_effacerPortrait();
		} else {
			int positionPortrait = parametres[0];
			boolean affichageAGauche = parametres[1] == 0;
			boolean portraitInverse = parametres[1] == 1;

			executeur.Messages_afficherPortrait(nomFaceset, positionPortrait, affichageAGauche, portraitInverse);
		}
	}


	private void $10210_changerSwitch(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		try {
			ValeurGauche valeurGauche = dechiffrerValeurGauche(parametres[0], parametres[1], parametres[2]);
			Boolean nouvelleValeur = dechiffrerBooleen(parametres[3]);
			executeur.Variables_changerSwitch(valeurGauche, nouvelleValeur);
		} catch (ArgumentInconnuException e) {
			// Aë : instruction sans effet et connue 
			if (parametres[0] == 29968776) {
				return;
			}

			DechiffreurInstructions.afficherErreur("ChangeVariable - ");
			afficher(parametres, new int[0], chaine);
		}
	}

	private void $10220_changerVariable(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		ValeurGauche valeurGauche = dechiffrerValeurGauche(parametres[0], parametres[1], parametres[2]);
		OpMathematique operateur = extraireOperateurMaths(parametres[3]);
		ValeurDroiteVariable valeurDroite = dechiffrerValeurDroiteVariable(parametres[4], parametres[5],
				parametres[6]);

		if (operateur == null) {
			executeur.Variables_affecterVariable(valeurGauche, valeurDroite);
		} else {
			executeur.Variables_changerVariable(valeurGauche, operateur, valeurDroite);
		}
	}

	private void $10230_modifierChrono(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		int numeroChrono = parametres[5] + 1;

		switch (parametres[0]) {
		case 0:
			executeur.Chrono_modifier(numeroChrono, dechiffrerFixeVariable(parametres[1], parametres[2]));
			return;
		case 1:
			boolean afficherChrono = parametres[3] == 1;
			boolean continuerPendantCombat = parametres[4] == 1;
			executeur.Chrono_lancer(numeroChrono, afficherChrono, continuerPendantCombat);
			return;
		case 2:
			executeur.Chrono_arreter(numeroChrono);
			return;
		default:
			throw new ArgumentInconnuException("ModifierChrono Type " + parametres[0]);
		}
	}

	private void $10310_modifierArgent(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		FixeVariable quantite = dechiffrerFixeVariable(parametres[1], parametres[2]);
		boolean ajouter = parametres[0] == 0;

		executeur.Variables_modifierArgent(ajouter, quantite);
	}

	private void $10320_modifierObjets(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		boolean ajouter = parametres[0] == 0;
		FixeVariable objet = dechiffrerFixeVariable(parametres[1], parametres[2]);
		FixeVariable quantite = dechiffrerFixeVariable(parametres[3], parametres[4]);

		executeur.Variables_modifierObjets(ajouter, objet, quantite);
	}

	private void $10330_modifierEquipe(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		boolean ajouter = parametres[0] == 0;
		FixeVariable personnage = dechiffrerFixeVariable(parametres[1], parametres[2]);

		executeur.Equipe_modifierEquipe(ajouter, personnage);
	}

	private void $10410_modifierExperience(ExecuteurInstructions executeur, int[] parametres,
			VariableHeros.Caracteristique stat) {
		boolean ajouter = parametres[2] == 0;
		ValeurMembre cible = dechiffrerMembreCible(parametres[0], parametres[1]);
		FixeVariable quantite = dechiffrerFixeVariable(parametres[3], parametres[4]);
		boolean verbose = parametres[5] == 1;

		executeur.Equipe_modifierExperience(cible, stat, ajouter, quantite, verbose);
	}

	private void $10430_modifierStatistiqueStable(ExecuteurInstructions executeur, int[] parametres, String s) {
		boolean ajouter = parametres[2] == 0;
		ValeurMembre cible = dechiffrerMembreCible(parametres[0], parametres[1]);
		VariableHeros.Caracteristique stat = caracAugmentable(parametres[3]);
		FixeVariable quantite = dechiffrerFixeVariable(parametres[4], parametres[5]);

		executeur.Equipe_modifierStatistique(cible, stat, ajouter, quantite);
	}

	private void $10440_modifierCompetence(ExecuteurInstructions executeur, int[] parametres, String s) {
		ValeurMembre cible = dechiffrerMembreCible(parametres[0], parametres[1]);
		boolean ajouter = parametres[2] == 0;
		FixeVariable sort = dechiffrerFixeVariable(parametres[3], parametres[4]);

		executeur.Equipe_modifierCompetence(cible, ajouter, sort);
	}

	private void $10450_modifierEquipement(ExecuteurInstructions executeur, int[] parametres, String s) {
		ValeurMembre cible = dechiffrerMembreCible(parametres[0], parametres[1]);
		if (parametres[2] == 0) {
			FixeVariable objet = dechiffrerFixeVariable(parametres[3], parametres[4]);
			executeur.Equipe_equiper(cible, objet);
		} else {
			if (parametres[3] == 0) {
				executeur.Equipe_desequiper(cible);
			} else {
				executeur.Equipe_desequiper(cible, VariableHeros.Caracteristique
						.values()[VariableHeros.Caracteristique.ARME.ordinal() + parametres[3]]);
			}
		}
	}

	private void $10460_modifierHP(ExecuteurInstructions executeur, int[] parametres, String s) {
		ValeurMembre cible = dechiffrerMembreCible(parametres[0], parametres[1]);
		boolean ajouter = parametres[2] == 0;
		FixeVariable quantite = dechiffrerFixeVariable(parametres[3], parametres[4]);
		boolean peutTuer = parametres[5] == 1;

		executeur.Equipe_modifierHP(cible, ajouter, quantite, peutTuer);
	}

	private void $10470_modifierMP(ExecuteurInstructions executeur, int[] parametres, String s) {
		ValeurMembre cible = dechiffrerMembreCible(parametres[0], parametres[1]);
		boolean ajouter = parametres[2] == 0;
		FixeVariable quantite = dechiffrerFixeVariable(parametres[3], parametres[4]);

		executeur.Equipe_modifierStatistique(cible, VariableHeros.Caracteristique.MPACTUEL, ajouter, quantite);
	}

	private void $10480_modifierStatut(ExecuteurInstructions executeur, int[] parametres, String s) {
		ValeurMembre cible = dechiffrerMembreCible(parametres[0], parametres[1]);
		boolean infliger = parametres[2] == 0;
		int numeroStatut = parametres[3];

		executeur.Equipe_modifierStatut(cible, infliger, numeroStatut);
	}

	private void $10490_soignerCompletement(ExecuteurInstructions executeur, int[] parametres, String s) {
		ValeurMembre cible = dechiffrerMembreCible(parametres[0], parametres[1]);
		executeur.Equipe_soignerCompletement(cible);
	}

	private void $10500_SimulerAttaque(ExecuteurInstructions executeur, int[] parametres, String s) {
		ValeurMembre cible = dechiffrerMembreCible(parametres[0], parametres[1]);
		int puissance = parametres[2];
		int effetDefense = parametres[3];
		int effetIntel = parametres[4];
		int variance = parametres[5];
		int degatsEnregistresDansVariable = parametres[6] == 0 ? 0 : parametres[7];
		executeur.Combat_simulerAttaque(cible, puissance, effetDefense, effetIntel, variance,
				degatsEnregistresDansVariable);
	}

	private void $10630_modifierCharsetHeros(ExecuteurInstructions executeur, int[] parametres, String s) {
		boolean transparent = parametres[2] == 1;
		executeur.Systeme_modifierApparenceHeros(parametres[0], s, parametres[1], transparent);
	}

	private void $10660_modifierMusique(ExecuteurInstructions executeur, int[] parametres, String s) {
		ExecEnum.Musique musique = ExecEnum.Musique.values()[parametres[0]];
		int tempsFondu = parametres[1];
		SonParam sonParam = new SonParam(parametres[2], parametres[3], parametres[4]);

		executeur.Systeme_modifierMusique(musique, s, tempsFondu, sonParam);
	}

	private void $10670_modifierEffetSonore(ExecuteurInstructions executeur, int[] parametres, String s) {
		ExecEnum.EffetSonore son = ExecEnum.EffetSonore.values()[parametres[0]];
		SonParam sonParam = new SonParam(parametres[1], parametres[2], parametres[3]);

		executeur.Systeme_modifierEffetSonore(son, s, sonParam);
	}

	private void $10680_modifierApparence(ExecuteurInstructions executeur, int[] parametres, String s) {
		executeur.Systeme_modifierApparence(parametres[0] == 0, parametres[1] == 0, s);
	}

	private void $10690_modifierTransition(ExecuteurInstructions executeur, int[] parametres, String s) {
		ExecEnum.SujetTransition sujetTransition = sujetTransition(parametres[0]);
		boolean entrant = parametres[0] % 2 == 0;
		ExecEnum.Transition transition = ExecEnum.Transition.values()[parametres[1]];

		executeur.Systeme_modifierTransition(sujetTransition, entrant, transition);
	}

	private void $10720_Magasin(ExecuteurInstructions executeur, int[] parametres, String s) {
		boolean ventePossible = parametres[0] != 1;
		int dialogue = parametres[1];

		int[] objetsAchetables;

		if (parametres[0] == 2)
			objetsAchetables = null;
		else
			objetsAchetables = Arrays.copyOfRange(parametres, 4, parametres.length);

		executeur.Magasin_magasin(dialogue, objetsAchetables, ventePossible);
	}

	private void $10730_Auberge(ExecuteurInstructions executeur, int[] parametres, String s) {
		boolean type1 = parametres[0] == 0;
		int prix = parametres[1];

		executeur.Magasin_auberge(type1, prix);
	}

	private void $10740_saisieDeNom(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		int idHeros = parametres[0];
		boolean lettres = parametres[1] == 0;
		boolean afficherNomParDefaut = parametres[2] == 1;
		executeur.SaisieMessages_SaisieNom(idHeros, lettres, afficherNomParDefaut);
	}

	private void $10810_teleporter(ExecuteurInstructions executeur, int[] parametres, String s) {
		executeur.Jeu_teleporter(parametres[0], parametres[1], parametres[2],
				parametres.length <= 3 ? ExecEnum.Direction.INCHANGEE : ExecEnum.Direction.values()[parametres[3]]);
	}

	private void $10820_memoriserPosition(ExecuteurInstructions executeur, int[] p, String s) {
		executeur.Jeu_memoriserPosition(p[0], p[1], p[2]);
	}

	private void $10850_deplacerVehicule(ExecuteurInstructions executeur, int[] parametres, String s) {
		ExecEnum.Vehicule vehicule = ExecEnum.Vehicule.values()[parametres[0]];
		FixeVariable z = dechiffrerFixeVariable(parametres[1], parametres[2]);
		FixeVariable x = dechiffrerFixeVariable(parametres[1], parametres[3]);
		FixeVariable y = dechiffrerFixeVariable(parametres[1], parametres[4]);
		executeur.Jeu_deplacerVehicule(vehicule, z, x, y);
	}

	private void $10860_deplacerEvenement(ExecuteurInstructions executeur, int[] parametres, String s) {
		EvenementDeplacable deplacable = new EvenementDeplacable(parametres[0]);

		FixeVariable x = dechiffrerFixeVariable(parametres[1], parametres[2]);
		FixeVariable y = dechiffrerFixeVariable(parametres[1], parametres[3]);

		executeur.Jeu_deplacerEvenement(deplacable, x, y);
	}

	private void $10870_inverserEvenements(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		EvenementDeplacable evenement1 = new EvenementDeplacable(parametres[0]);
		EvenementDeplacable evenement2 = new EvenementDeplacable(parametres[1]);
		executeur.Jeu_inverserEvenements(evenement1, evenement2);
	}

	private void $10910_stockerIdTerrain(ExecuteurInstructions executeur, int[] parametres, String s) {
		FixeVariable x = dechiffrerFixeVariable(parametres[0], parametres[1]);
		FixeVariable y = dechiffrerFixeVariable(parametres[0], parametres[2]);

		executeur.Jeu_stockerIdTerrain(x, y, parametres[3]);
	}

	private void $10920_stockerIdEvenement(ExecuteurInstructions executeur, int[] parametres, String s) {
		FixeVariable x = dechiffrerFixeVariable(parametres[0], parametres[1]);
		FixeVariable y = dechiffrerFixeVariable(parametres[0], parametres[2]);

		executeur.Jeu_stockerIdEvenement(x, y, parametres[3]);
	}

	private void $11010_effacerEcran(ExecuteurInstructions executeur, int[] parametres, String s) {
		if (parametres[0] == -1) {
			executeur.Jeu_cacherEcran(ExecEnum.Transition.DEFAUT);
		} else {
			executeur.Jeu_cacherEcran(ExecEnum.Transition.values()[parametres[0]]);
		}
	}

	private void $11020_afficherEcran(ExecuteurInstructions executeur, int[] parametres, String s) {
		if (parametres[0] == -1) {
			executeur.Jeu_afficherEcran(ExecEnum.Transition.DEFAUT);
		} else {
			executeur.Jeu_afficherEcran(ExecEnum.Transition.values()[parametres[0]]);
		}
	}

	private void $11030_modifierTonEcran(ExecuteurInstructions executeur, int[] parametres, String s) {
		Couleur couleur = new Couleur(parametres[0], parametres[1], parametres[2]);
		int saturation = parametres[3];
		int tempsMs = parametres[4];
		boolean pause = parametres[5] == 1;

		executeur.Jeu_tonEcran(couleur, saturation, tempsMs, pause);
	}

	private void $11040_flashEcran(ExecuteurInstructions executeur, int[] parametres, String s) {
		int statut = parametres.length >= 7 ? parametres[6] : 0;

		if (statut == 2) {
			executeur.Jeu_flashStop();
			return;
		}

		Couleur couleur = new Couleur(parametres[0], parametres[1], parametres[2]);
		int intensite = parametres[3];
		int tempsMs = parametres[4];
		boolean pause = parametres[5] == 1;
		boolean flashUnique = statut == 1;

		executeur.Jeu_flashLancer(couleur, intensite, tempsMs, pause, flashUnique);
	}

	private void $11050_tremblementEcran(ExecuteurInstructions executeur, int[] parametres, String s) {
		int force = parametres[0];
		int intensite = parametres[1];
		int temps = parametres[2];
		boolean bloquant = parametres[3] == 1;

		if (parametres.length <= 4 || parametres[4] == 0) {
			executeur.Jeu_tremblementPonctuel(force, intensite, temps, bloquant);
			return;
		}

		if (parametres[4] == 1) {
			executeur.Jeu_tremblementCommencer(force, intensite);
			return;
		}

		if (parametres[4] == 2) {
			executeur.Jeu_tremblementStop();
			return;
		}

	}

	private void $11060_defilement(ExecuteurInstructions executeur, int[] parametres, String s) {
		if (parametres[0] == 0) {
			executeur.Jeu_defilementBloquer();
			return;
		} else if (parametres[0] == 1) {
			executeur.Jeu_defilementReprendre();
			return;
		}

		int vitesse = parametres[3];
		boolean pause = parametres[4] == 1;

		if (parametres[0] == 3) {
			executeur.Jeu_defilementRetour(vitesse, pause);
		}

		ExecEnum.Direction direction = ExecEnum.Direction.values()[parametres[1]];
		int nombreDeCases = parametres[2];

		executeur.Jeu_defilementPonctuel(vitesse, pause, direction, nombreDeCases);
	}

	private void $11070_modifierMeteo(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		ExecEnum.Meteo climat = ExecEnum.Meteo.values()[parametres[0]];
		ExecEnum.Intensite intensite = ExecEnum.Intensite.values()[parametres[1]];
		executeur.Jeu_modifierMeteo(climat, intensite);
	}

	private void $11110_AfficherImage(ExecuteurInstructions executeur, int[] parametres, String s) {
		int numeroImage = parametres[0];
		FixeVariable xImage = dechiffrerFixeVariable(parametres[1], parametres[2]);
		FixeVariable yImage = dechiffrerFixeVariable(parametres[1], parametres[3]);
		int transparenceHaute = parametres[6];
		int transparenceBasse = parametres.length > 14 ? parametres[14] : parametres[6];
		int agrandissement = parametres[5];

		Couleur couleur = new Couleur(parametres[8], parametres[9], parametres[10]);
		int saturation = parametres[11];

		ExecEnum.TypeEffet typeEffet = ExecEnum.TypeEffet.values()[parametres[12]];
		int intensiteEffet = parametres[13];
		boolean transparence = parametres[7] == 1;
		boolean defilementAvecCarte = parametres[4] == 1;

		executeur.Image_afficher(numeroImage, s, xImage, yImage, transparenceHaute, transparenceBasse, agrandissement,
				couleur, saturation, typeEffet, intensiteEffet, transparence, defilementAvecCarte);
	}

	private void $11120_DeplacerImage(ExecuteurInstructions executeur, int[] parametres, String s) {
		int numeroImage = parametres[0];
		FixeVariable xImage = dechiffrerFixeVariable(parametres[1], parametres[2]);
		FixeVariable yImage = dechiffrerFixeVariable(parametres[1], parametres[3]);
		int transparenceHaute = parametres[6];
		int transparenceBasse = parametres.length > 16 ? parametres[16] : parametres[6];
		int agrandissement = parametres[5];

		Couleur couleur = new Couleur(parametres[8], parametres[9], parametres[10]);
		int saturation = parametres[11];

		ExecEnum.TypeEffet typeEffet = ExecEnum.TypeEffet.values()[parametres[12]];
		int intensiteEffet = parametres[13];

		int temps = parametres[14];
		boolean pause = parametres[15] == 1;

		executeur.Image_deplacer(numeroImage, xImage, yImage, transparenceHaute, transparenceBasse, agrandissement,
				couleur, saturation, typeEffet, intensiteEffet, temps, pause);
	}

	private void $11320_flasherEvenement(ExecuteurInstructions executeur, int[] parametres, String s) {
		Couleur couleur = new Couleur(parametres[1], parametres[2], parametres[3]);
		int tempsDixiemeDeSec = parametres[5];
		int intensite = parametres[4];
		EvenementDeplacable deplacable = new EvenementDeplacable(parametres[0]);
		boolean pause = parametres[6] == 0;

		executeur.Jeu_flasherEvenement(deplacable, couleur, intensite, pause, tempsDixiemeDeSec);
	}

	private void $11330_deplacement(ExecuteurInstructions executeur, int[] parametres, String s) {
		EvenementDeplacable deplacable = new EvenementDeplacable(parametres[0]);
		int vitesse = parametres[1];
		boolean repeter = parametres[2] == 1;
		boolean ignorerBloquage = parametres[3] == 1;

		executeur.Jeu_deplacer(deplacable, vitesse, repeter, ignorerBloquage, new Deplacement());
	}

	private void $11410_attente(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		if (parametres.length == 1 || parametres[1] == 0) {
			executeur.Jeu_attendre(parametres[0]);
		} else {
			executeur.Jeu_attendreAppuiTouche();
		}
	}

	private void $11510_JouerMusique(ExecuteurInstructions executeur, int[] parametres, String nomMusique) {
		int tempsFondu = parametres[0];
		SonParam parametresMusicaux = new SonParam(parametres[1], parametres[2], parametres[3]);

		executeur.Media_jouerMusique(nomMusique, tempsFondu, parametresMusicaux);
	}

	private void $11550_JouerSon(ExecuteurInstructions executeur, int[] parametres, String nomSon) {
		SonParam parametresSonore = new SonParam(parametres[0], parametres[1], parametres[2]);
		executeur.Media_jouerSon(nomSon, parametresSonore);
	}

	private void $11560_JouerFilm(ExecuteurInstructions executeur, int[] parametres, String nomFilm) {
		FixeVariable x = dechiffrerFixeVariable(parametres[0], parametres[1]);
		FixeVariable y = dechiffrerFixeVariable(parametres[0], parametres[2]);

		int longueur = parametres[3];
		int largeur = parametres[4];

		executeur.Media_jouerFilm(nomFilm, x, y, longueur, largeur);
	}

	private void $11610_appuiTouche(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		int numeroVariable = parametres[0];
		boolean bloquant = parametres[1] == 1;
		int enregistrementTempsMis = parametres.length >= 9 ? (parametres[8] == 1 && bloquant ? -1 : parametres[7])
				: -1;

		boolean entree = parametres[3] == 1;
		boolean annuler = parametres[4] == 1;
		if (parametres.length <= 5) {
			executeur.Messages_appuiTouche(numeroVariable, bloquant, enregistrementTempsMis, false, false, false, false,
					entree, annuler, false, false, false);
			return;
		}

		boolean maj = parametres[5] == 1;
		boolean chiffres = parametres[6] == 1;
		boolean symboles = parametres[9] == 1;
		boolean bas = parametres[10] == 1;
		boolean gauche = parametres[11] == 1;
		boolean droite = parametres[12] == 1;
		boolean haut = parametres[13] == 1;

		executeur.Messages_appuiTouche(numeroVariable, bloquant, enregistrementTempsMis, haut, droite, bas, gauche,
				entree, annuler, maj, chiffres, symboles);
	}

	private void $11720_panorama(ExecuteurInstructions executeur, int[] parametres, String nomPanorama) {
		int defilementHorizontal = $11720_panoramaDefilement(parametres[0], parametres[1], parametres[2]);
		int defilementVertical = $11720_panoramaDefilement(parametres[3], parametres[4], parametres[5]);

		executeur.Jeu_changerPanorama(nomPanorama, defilementHorizontal, defilementVertical);
	}

	/**
	 * Algorithme : Si i = 0, renvoie -1. Si j = 0, renvoie 0. Sinon renvoie k. <br>
	 * But : Si -1 il n'y a pas de défilement. Sinon on indique la vitesse du défilement.
	 */
	private int $11720_panoramaDefilement(int i, int j, int k) {
		if (i == 0)
			return -1;

		if (j == 0)
			return 0;

		return k;
	}

	private void $11750_modifierCarreau(ExecuteurInstructions executeur, int[] parametres, String s) {
		boolean coucheHaute = parametres[0] == 1;
		int remplace = parametres[1];
		int remplacant = parametres[2];

		executeur.Jeu_modifierCarreau(coucheHaute, remplace, remplacant);
	}

	private void $11810_modifierTeleporteur(ExecuteurInstructions executeur, int[] parametres, String s) {
		boolean ajouter = parametres[0] == 0;

		int map = parametres[1];

		if (ajouter) {
			int x = parametres[2];
			int y = parametres[3];

			int switchActive = parametres[4] == 0 ? 0 : parametres[5];
			executeur.Jeu_ajouterTeleporteur(map, x, y, switchActive);
		} else {
			executeur.Jeu_retirerTeleporteur(map);
		}
	}

	private void $11830_pointDeFuite(ExecuteurInstructions executeur, int[] parametres, String s) {
		int map = parametres[0];
		int x = parametres[1];
		int y = parametres[2];

		int switchActive = parametres[3] == 0 ? 0 : parametres[4];

		executeur.Jeu_definirPointDeFuite(map, x, y, switchActive);
	}

	private void $12210_afficherAnimation(ExecuteurInstructions executeur, int[] parametres, String s) {
		EvenementDeplacable deplacable = new EvenementDeplacable(parametres[1]);
		int numeroAnimation = parametres[0];
		boolean pause = parametres[2] == 1;
		boolean pleinEcran = parametres[3] == 1;

		executeur.Jeu_afficherAnimation(deplacable, numeroAnimation, pause, pleinEcran);
	}

	private void $12330_Evenement(ExecuteurInstructions executeur, int[] parametres, String s) {
		switch (parametres[0]) {
		case 0:
			executeur.Flot_appelEvenementCommun(parametres[1]);
			return;
		case 1:
			executeur.Flot_appelEvenementCarte(new ValeurFixe(parametres[1]), new ValeurFixe(parametres[2]));
			return;
		case 2:
			executeur.Flot_appelEvenementCarte(new Variable(parametres[1]), new Variable(parametres[2]));
			return;
		}
	}

	private void $20141_finQCM(ExecuteurInstructions executeur, int[] parametres, String chaine) {
		executeur.SaisieMessages_finQCM();
	}

	private void $X1008_modifierClasse(ExecuteurInstructions executeur, int[] p, String s) {
		int idHeros = p[1];
		int idClasse = p[2];
		boolean revenirAuNiveau1 = p[3] == 1;
		ExecEnum.ClasseComp competences = ExecEnum.ClasseComp.values()[p[4]];
		ExecEnum.ClasseCarac caracBase = ExecEnum.ClasseCarac.values()[p[5]];
		boolean montrerCompetencesApprises = p[6] == 1;

		executeur.Systeme_changerClasse(idHeros, idClasse, revenirAuNiveau1, competences, caracBase,
				montrerCompetencesApprises);
	}

	private void $X1009_modifierCommandes(ExecuteurInstructions executeur, int[] parametres, String s) {
		int idHeros = parametres[1];
		boolean ajout = parametres[3] == 0;
		int numeroCommande = parametres[2];
		executeur.Systeme_modifierCommandes(idHeros, ajout, numeroCommande);
	}
	
	
	// ================================================================================================================
	// ================================================================================================================
	// ================================================================================================================
	
	
	private void afficher(int[] parametres, int[] is, String s) {
		DechiffreurInstructions.afficherErreur("=====\n");
		for (int i = 0; i != parametres.length; i++) {
			if (Utilitaire.getPosition(i, is) == -1) {
				DechiffreurInstructions.afficherErreur("[" + i + "] = " + parametres[i] + " ; ");
			}
		}
		DechiffreurInstructions.afficherErreur(s + "\n");
	}
	

	private ExecEnum.Position extrairePosition(int position) {
		switch (position) {
		case 0:
			return ExecEnum.Position.HAUT;
		case 1:
			return ExecEnum.Position.MILIEU;
		case 2:
			return ExecEnum.Position.BAS;
		default:
			return null;
		}
	}
	
	private VariableHeros.Caracteristique caracAugmentable(int valeur) {
		switch (valeur) {
		case 0:
			return VariableHeros.Caracteristique.HPMAX;
		case 1:
			return VariableHeros.Caracteristique.MPMAX;
		case 2:
			return VariableHeros.Caracteristique.ATTAQUE;
		case 3:
			return VariableHeros.Caracteristique.DEFENSE;
		case 4:
			return VariableHeros.Caracteristique.INTELLIGENCE;
		case 5:
			return VariableHeros.Caracteristique.AGILITE;
		default:
			throw new ArgumentInconnuException("Carac augmentable " + valeur);
		}
	}

	private ValeurMembre dechiffrerMembreCible(int type, int valeur) {
		switch (type) {
		case 0:
			return Tous.getInstance();
		case 1:
			return new ValeurFixe(valeur);
		case 2:
			return new Variable(valeur);
		default:
			throw new ArgumentInconnuException("Membre cible Type " + type);
		}
	}

	private static FixeVariable dechiffrerFixeVariable(int type, int nombre) {
		if (type == 0) {
			return new ValeurFixe(nombre);
		} else if (type == 1) {
			return new Variable(nombre);
		} else {
			throw new ArgumentInconnuException("FixeVariable Type  " + type);
		}
	}

	private ValeurDroiteVariable dechiffrerValeurDroiteVariable(int type, int valeur1, int valeur2) {
		switch (type) {
		case 0:
			return new ValeurFixe(valeur1);
		case 1:
			return new Variable(valeur1);
		case 2:
			return new Pointeur(valeur1);
		case 3:
			return new ValeurAleatoire(valeur1, valeur2);
		case 4:
			return new NombreObjet(valeur1, valeur2 == 1);
		case 5:
			return new VariableHeros(valeur2, VariableHeros.Caracteristique.values()[valeur1]);
		case 6:
			return new ValeurDeplacable(new EvenementDeplacable(valeur1),
					ValeurDeplacable.Caracteristique.values()[valeur2]);
		case 7:
			return ValeurDivers.values()[valeur1];
		default:
			throw new ArgumentInconnuException("VDVariable Type  " + type);
		}
	}

	private Boolean dechiffrerBooleen(int action) {
		switch (action) {
		case 0:
			return Boolean.TRUE;
		case 1:
			return Boolean.FALSE;
		case 2:
			return null;
		default:
			throw new ArgumentInconnuException("ChangerSwitch Action " + action);
		}
	}

	private ValeurGauche dechiffrerValeurGauche(int type, int valeur1, int valeur2) {
		switch (type) {
		case 0:
			return new Variable(valeur1);
		case 1:
			return new VariablePlage(valeur1, valeur2);
		case 2:
			return new Pointeur(valeur1);
		default:
			throw new ArgumentInconnuException("ChangerSwitch Variable type " + type);
		}
	}

	private OpMathematique extraireOperateurMaths(int numero) {
		switch (numero) {
		case 0:
			return null;
		case 1:
			return OpMathematique.PLUS;
		case 2:
			return OpMathematique.MOINS;
		case 3:
			return OpMathematique.FOIS;
		case 4:
			return OpMathematique.DIVISE;
		case 5:
			return OpMathematique.MODULO;
		default:
			throw new ArgumentInconnuException("Extraire Operateur Mathematique " + numero);
		}
	}

	private Vehicule dechiffreVehicule(int numero) {
		switch (numero) {
		case 0:
			return Vehicule.RADEAU;
		case 1:
			return Vehicule.BATEAU;
		case 2:
			return Vehicule.VAISSEAU;
		default:
			throw new ArgumentInconnuException("DechiffreVehicule " + numero);
		}
	}

	private SujetTransition sujetTransition(int id) {
		if (id < 2)
			return SujetTransition.TELEPORTATION;

		if (id < 4)
			return SujetTransition.COMBAT;

		if (id < 6)
			return SujetTransition.FINCOMBAT;

		throw new ArgumentInconnuException("sujetTransition " + id);
	}

	private static ArrierePlanCombat arrierePlan(int type, int equipiers, int typeTerrain, String nomPanorama) {
		switch (type) {
		case 0:
			return new ArrierePlanCombat.Aucun();
		case 1:
			return new ArrierePlanCombat.Specifique(nomPanorama, equipiers == 1);
		case 2:
			return new ArrierePlanCombat.AssocieAuTerrain(typeTerrain);
		default:
			throw new ArgumentInconnuException("ArrierePlanCombat " + type);
		}
	}

	private static Condition dechiffrerCondition(int[] parametres, String s) {
		switch (parametres[0]) {
		case 0:
			return new Condition.CondInterrupteur(parametres[1], parametres[2] == 0);
		case 1:
		{
			int idVariable = parametres[1];
			FixeVariable valeurDroite = dechiffrerFixeVariable(parametres[2], parametres[3]);
			Comparateur comp;
			switch (parametres[4]) {
			case 0:
				comp = Comparateur.IDENTIQUE;
				break;
			case 1:
				comp = Comparateur.SUPEGAL;
				break;
			case 2:
				comp = Comparateur.INFEGAL;
				break;
			case 3:
				comp = Comparateur.SUP;
				break;
			case 4:
				comp = Comparateur.INF;
				break;
			case 5:
				comp = Comparateur.DIFFERENT;
				break;
			default:
				comp = null;
			}
			return new Condition.CondVariable(idVariable, comp, valeurDroite);
		}
		case 2:
		case 10:
			return new Condition.CondChrono(parametres[0] == 2,
					parametres[2] == 0 ? Comparateur.SUPEGAL : Comparateur.INFEGAL , parametres[1]);
		case 3:
			return new Condition.CondArgent(parametres[2] == 0 ? Comparateur.SUPEGAL : Comparateur.INFEGAL , parametres[1]);
		case 9:
			return new Condition.CondMusiqueJoueePlusDUneFois();
		case 8:
			return new Condition.CondEventDemarreParAppui();
		case 7:
			return new Condition.CondVehiculeUtilise(ExecEnum.Vehicule.values()[parametres[1]]);
		case 6:
			return new Condition.CondDirection(new EvenementDeplacable(parametres[1]), ExecEnum.Direction.values()[parametres[2]]);
		case 4:
			return new Condition.CondObjet(parametres[1], parametres[2] == 0);
		case 5:
			int idHeros = parametres[1];
			switch (parametres[2]) {
			case 0:
				return new Condition.CondHerosDansLEquipe(idHeros);
			case 1:
				return new Condition.CondHerosAPourNom(idHeros, s);
			case 2:
				return new Condition.CondHerosNiveauMin(idHeros, parametres[3]);
			case 3:
				return new Condition.CondHerosAAuMoinsHp(idHeros, parametres[3]);
			case 4:
				return new Condition.CondHerosAStatut(idHeros, parametres[3]);
			case 5:
				return new Condition.CondHerosPossedeObjet(idHeros, parametres[3]);
			case 6:
				return new Condition.CondHerosPossedeSort(idHeros, parametres[3]);
			default:
				return null;
			}
		default:
			return null;
		}
	}
}
