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
import fr.bruju.lcfreader.services.LecteurDeLCF;
import fr.bruju.rmdechiffreur.ExecuteurInstructions;
import fr.bruju.rmdechiffreur.Utilitaire.TriConsumer;
import fr.bruju.rmdechiffreur.controlleur.DechiffreurInstructions;
import fr.bruju.rmdechiffreur.reference.ReferenceEC;
import fr.bruju.rmdechiffreur.reference.ReferenceMap;

/**
 * Cette classe permet d'avoir un point d'entrée simple et pratique pour faire la communication entre exécuteur
 * et fichiers.
 * 
 * @author Bruju
 *
 */
public class Explorateur implements ExplorateurDInstructions {
	/** Projet rattaché à l'explorateur */
	private final LecteurDeLCF lecteur;
	
	/**
	 * Crée un explorateur rattaché au projet donné
	 * @param lecteur Le projet de rattachement
	 */
	public Explorateur(LecteurDeLCF lecteur) {
		this.lecteur = lecteur;
	}
	
	/* ==============================================
	 * Niveau d'abstraction 1 : Lire des instructions
	 * ============================================== */
	
	@Override
	public void executer(ExecuteurInstructions executeur, List<RMInstruction> instructions) {
		DechiffreurInstructions dechiffreur = new DechiffreurInstructions(executeur);
		dechiffreur.executer(instructions);
	}

	/* ============================================
	 * Niveau d'abstraction 2 : Lire des évènements
	 * ============================================ */
	
	@Override
	public void lireEvenement(ExecuteurInstructions executeur, int idMap, int idEvenement, int idPage) {
		executer(executeur, lecteur.page(idMap, idEvenement, idPage).instructions());
	}

	@Override
	public void lireEvenementCommun(ExecuteurInstructions executeur, int idEvenement) {
		executer(executeur, lecteur.evenementCommun(idEvenement).instructions());
	}
	
	
	/* ================================================================
	 * Niveau d'abstraction 3 : Lire tous les évènements d'un même type
	 * ================================================================ */
	
	@Override
	public void explorerEvenementsCommuns(Consumer<RMEvenementCommun> actionSurLesEvenementCommuns) {
		lecteur.evenementsCommuns().values().forEach(actionSurLesEvenementCommuns);
	}

	@Override
	public void explorerEvenementsSurCarte(BiConsumer<RMMap, RMEvenement> actionSurEvenement) {
		for (RMMap rmMap : lecteur.maps().values()) {
			for (RMEvenement rmEvenement : rmMap.evenements().values()) {
				actionSurEvenement.accept(rmMap, rmEvenement);
			}
		}
	}

	@Override
	public void explorerEvenements(TriConsumer<RMMap, RMEvenement, RMPage> actionSurLesPages) {
		for (RMMap rmMap : lecteur.maps().values()) {
			for (RMEvenement rmEvenement : rmMap.evenements().values()) {
				for (RMPage rmPage : rmEvenement.pages()) {
					actionSurLesPages.consume(rmMap, rmEvenement, rmPage);
				}
			}
		}
	}

	@Override
	public void explorerCarte(int idCarte, BiConsumer<RMEvenement, RMPage> actionSurLesPages) {
		RMMap carte = lecteur.map(idCarte);
		
		for (RMEvenement evenement : carte.evenements().values()) {
			for (RMPage page : evenement.pages()) {
				actionSurLesPages.accept(evenement, page);
			}
		}
	}

	/* =======================================================================================
	 * Niveau d'abstraction 4 : Lire tous les évènements d'un même type en retenant l'identité
	 * ======================================================================================= */
	
	@Override
	public void referencerEvenementsCommuns(Function<ReferenceEC, ExecuteurInstructions> generateur) {
		for (RMEvenementCommun evenementCommun : lecteur.evenementsCommuns().values()) {
			executer(generateur.apply(new ReferenceEC(evenementCommun)), evenementCommun.instructions());
		}
	}
	
	@Override
	public void referencerCartes(Function<ReferenceMap, ExecuteurInstructions> generateur) {
		for (RMMap carte : lecteur.maps().values()) {
			for (RMEvenement evenement : carte.evenements().values()) {
				for (RMPage page : evenement.pages()) {
					executer(generateur.apply(new ReferenceMap(carte, evenement, page)), page.instructions());
				}
			}
		}
	}
	
	/* ====
	 * Misc
	 * ==== */

	@Override
	public ReferenceEC getReference(int idEvenementCommun) {
		return new ReferenceEC(lecteur.evenementCommun(idEvenementCommun));
	}
}
