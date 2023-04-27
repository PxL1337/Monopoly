package fr.eni.monopoly.cases;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.monopoly.Pioche;
import fr.eni.monopoly.actions.Action;

/**
 * The type Chance.
 */
public class Chance extends PiocherCase {
    protected static Pioche carte = new Pioche();

    public static Pioche getCartes() {
        return Chance.carte;
    }


    /**
     * Instantiates a new Chance.
     */
    public Chance() {
        super("Chance");
    }

    /**
     * Instantiates a new Remettre en jeu.
     */
    public static void remettreEnJeu(Action carte) {
        Chance.carte.ajouter(carte);
    }


    /**
     * Instantiates a new Tirer carte.
     *
     * @param j the j
     */
    protected void tirerCarte(Joueur j) throws FailliteException, AllerEnPrisonException {
        Action a = Chance.carte.tirer();
    }
}
