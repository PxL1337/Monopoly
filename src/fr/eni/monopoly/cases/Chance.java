package fr.eni.monopoly.cases;

import fr.eni.monopoly.Joueur;

/**
 * The type Chance.
 */
public class Chance extends PiocherCase {
    private Pioche carte;

    /**
     * Instantiates a new Chance.
     */
    public Chance() {
        super("Chance");
        carte = new Pioche();
    }

    /**
     * Instantiates a new Tirer carte.
     *
     * @param j the j
     */
    protected tirerCarte(Joueur j) {
        carte.tirerCarte(j);
    }

    /**
     * Instantiates a new Remettre en jeu.
     */
    public remettreEnJeu() {
        carte.remettreEnJeu();
    }

    /**
     * Gets cartes.
     *
     * @return the cartes
     */
    public static Object getCartes() {
    }
}
