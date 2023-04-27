package fr.eni.monopoly.actions;

import fr.eni.monopoly.Detenable;
import fr.eni.monopoly.Joueur;

/**
 * The type Libere de prison.
 */
public class LibereDePrison extends Action implements Detenable {
    private Joueur proprio;
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
