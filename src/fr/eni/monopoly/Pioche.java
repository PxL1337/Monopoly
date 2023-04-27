package fr.eni.monopoly;

import fr.eni.monopoly.actions.Action;
import fr.eni.monopoly.actions.LibereDePrison;
import fr.eni.util.Anneau;
import fr.eni.util.Maillon;

/**
 * The type Pioche.
 */
public class Pioche {
    private Anneau<Action> cartes = new Anneau<>();
    private Maillon<Action> position;

    public void ajouter(Action a) {
        this.cartes.ajouter(a);
        if (this.position == null) {
            this.position = this.cartes.getPtEntree();
        }
    }

    public Action tirer() {
        Maillon<Action> carte = this.position.suivant();
        if (carte.get() instanceof LibereDePrison) {
            cartes.retirer(carte.get());
        } else {
            this.position = carte;
        }
        return carte.get();
    }
}
