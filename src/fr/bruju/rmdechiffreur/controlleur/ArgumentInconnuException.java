package fr.bruju.rmdechiffreur.controlleur;

/**
 * Exception jetée si une instruction n'est pas reconnue et que cela pose un problème critique
 * 
 * @author Bruju
 *
 */
public class ArgumentInconnuException extends RuntimeException {
	private static final long serialVersionUID = -2821542240536547673L;

	public ArgumentInconnuException(String message) {
		super(message);
	}
}