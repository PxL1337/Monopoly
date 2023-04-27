package fr.eni.monopoly.cases;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;

/**
 * The type Allez en prison.
 */
public class AllezEnPrison extends Case {

    /**
     * Instantiates a new Allez en prison.
     *
     */
    public AllezEnPrison() {
        super("Allez en prison");

    }

    @Override
    public void joueurArrive(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.joueurArrive(j);
        throw new AllerEnPrisonException();
    }
}
