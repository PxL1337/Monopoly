package fr.eni.monopoly.actions;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;

/**
 * The type Argent action.
 */
public class ArgentAction extends Action{
    private int somme;

    /**
     * Instantiates a new Argent action.
     *
     * @param libelle the libelle
     * @param somme   the somme
     */
    public ArgentAction(String libelle, int somme) {
        super(String.format("%s %d€", libelle, Math.abs(somme)));
        this.somme = somme;
    }

    @Override
    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.realiser(j);
        if (this.somme < 0) {
            j.debiter(this.somme);
        } else {
            j.crediter(this.somme);
        }
    }
}
