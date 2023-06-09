package fr.eni.monopoly.actions;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;

/**
 * The type Action.
 */
public abstract class Action {
    private String libelle;

    /**
     * Instantiates a new Action.
     *
     * @param libelle the libelle
     */
    public Action(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Realiser.
     *
     * @param j the j
     * @throws FailliteException      the faillite exception
     * @throws AllerEnPrisonException the aller en prison exception
     */
    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        System.out.printf("Carte Tirée %s%n", libelle);
    }

    @Override
    public String toString() {
        return "Carte" + libelle;
    }

}
