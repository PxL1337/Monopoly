package fr.eni.monopoly.actions;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.monopoly.Monopoly;

/**
 * The type Deplacement relatif action.
 */
public class DeplacementRelatifAction extends Action {
    private int decalage;

    public DeplacementRelatifAction(String libelle, int decalage) {
        super(libelle);
        if (decalage < 0)
            decalage = Monopoly.getPlateau().getNbElements() + decalage;
        this.decalage = decalage;
    }

    @Override
    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.realiser(j);
        j.deplacer(this.decalage);
    }
}
