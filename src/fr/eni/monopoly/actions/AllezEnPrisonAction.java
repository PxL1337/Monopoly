package fr.eni.monopoly.actions;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;

public class AllezEnPrisonAction extends Action {

    public AllezEnPrisonAction() {
        super("Allez en prison. Avancez tout droit en prison. Ne passez pas par la case Départ. Ne recevez pas 200 €.");
    }

    @Override
    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.realiser(j);
        throw new AllerEnPrisonException();
    }

}
