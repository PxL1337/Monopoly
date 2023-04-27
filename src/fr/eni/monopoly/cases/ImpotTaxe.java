package fr.eni.monopoly.cases;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;

/**
 * The type Impot taxe.
 */
public class ImpotTaxe extends Case {
    private int prix;

    /**
     * Instantiates a new Impot taxe.
     *
     * @param nom  the nom
     * @param prix the prix
     */
    public ImpotTaxe(String nom, int prix) {
        super(nom);
        this.prix = prix;
    }

    @Override
    public void joueurArrive(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.joueurArrive(j);
        System.out.printf("%s paye %d€ à la banque%n", j, this.prix);
        j.debiter(this.prix);
    }
}
