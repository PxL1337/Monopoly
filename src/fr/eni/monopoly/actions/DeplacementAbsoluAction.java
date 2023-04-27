package fr.eni.monopoly.actions;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.monopoly.cases.Case;
import fr.eni.util.Maillon;

/**
 * The type Deplacement absolu action.
 */
public class DeplacementAbsoluAction extends Action {
    private Maillon<Case> cible;
    private boolean direct;

    public DeplacementAbsoluAction(String libelle, Maillon<Case> cible, boolean direct) {
        super(libelle);
        this.cible = cible;
        this.direct = direct;
    }

    @Override
    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.realiser(j);
        j.deplacer(this.cible, this.direct);
    }
}
