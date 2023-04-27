package fr.eni.monopoly;

/**
 * The type Aller en prison exception.
 */
public class AllerEnPrisonException extends Exception {
    private static final long serialVersionUID = 1L;
    private Joueur joueur;

    /**
     * Instantiates a new Aller en prison exception.
     *
     * @param message the message
     * @param j       the j
     */
    public AllerEnPrisonException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Aller en prison exception.
     */
    public AllerEnPrisonException() {

    }
}
