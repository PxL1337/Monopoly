package fr.eni.monopoly;


/**
 * The type Faillite exception.
 */
public class FailliteException extends Exception {
    private static final long serialVersionUID = 1L;
    private Joueur joueur;

    /**
     * Instantiates a new Faillite exception.
     *
     * @param message the message
     * @param j       the j
     */
    public FailliteException(String message, Joueur j) {
        super(message);
        this.joueur = j;
    }

    /**
     * Gets joueur.
     *
     * @return the joueur
     */
    public Joueur getJoueur() {
        return this.joueur;
    }
}
