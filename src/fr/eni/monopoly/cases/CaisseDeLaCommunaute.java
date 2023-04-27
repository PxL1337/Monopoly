package fr.eni.monopoly.cases;

import fr.eni.monopoly.Joueur;

/**
 * The type Caisse de la communaute.
 */
public class CaisseDeLaCommunaute extends PiocherCase {
    private Pioche carte;

    /**
     * Instantiates a new Caisse de la communaute.
     *
     * @param nom the nom
     */
    public CaisseDeLaCommunaute(String nom) {
        super(nom);
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
}
