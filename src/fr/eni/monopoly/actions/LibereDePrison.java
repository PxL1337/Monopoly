package fr.eni.monopoly.actions;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.Detenable;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;

/**
 * The type Libere de prison.
 */
public class LibereDePrison extends Action implements Detenable {
    private Joueur proprio;

    public LibereDePrison() {
        super("Vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée ou vendue.");
    }

    @Override
    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.realiser(j);
        this.setProprio(j);
    }
    @Override
    public Joueur getProprio() {
        return this.proprio;
    }

    @Override
    public void setProprio(Joueur j) {
        this.proprio = j;
    }

    @Override
    public int getValeur() {
        return 50;
    }
}
