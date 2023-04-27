package fr.eni.monopoly.cases;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;

/**
 * The type Depart.
 */
public class Depart extends Case{

    /**
     * The constant SALAIRE.
     */
    public static final int SALAIRE = 200;

    /**
     * Instantiates a new Depart.
     */
    public Depart() {
        super("Départ");
    }

    @Override
    public void joueurPasse(Joueur j) {
        payerSalaire(j);
    }

    @Override
    public void joueurArrive(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.joueurArrive(j);
        payerSalaire(j);
    }

    private void payerSalaire(Joueur j) {
        System.out.printf("%s touche %d€%n", j, SALAIRE);
        j.crediter(SALAIRE);
    }
}
