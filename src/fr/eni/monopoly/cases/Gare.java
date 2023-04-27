package fr.eni.monopoly.cases;

import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;

/**
 * The type Gare.
 */
public class Gare extends Propriete {
    /**
     * Instantiates a new Gare.
     *
     * @param nom the nom
     */
    public Gare(String nom) {
        super("Gare " + nom, 200, Groupe.GARE);
    }

    @Override
    protected void payerLoyer(Joueur passager, Joueur proprietaire) throws FailliteException {
        int loyer = this.txComplGroupe;
        if (loyer == 75)
            loyer = 100;
        else if (loyer == 100)
            loyer = 200;
        System.out.printf("%s possède %d gare%s%n", proprietaire, this.txComplGroupe * 4 / 100, loyer > 25 ? "s" : "");
        passager.payeA(proprietaire, loyer);
    }
}
