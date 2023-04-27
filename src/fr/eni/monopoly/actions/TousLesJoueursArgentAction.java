package fr.eni.monopoly.actions;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.monopoly.Monopoly;
import fr.eni.util.Maillon;

/**
 * The type Tous les joueurs argent action.
 */
public class TousLesJoueursArgentAction extends Action {
    private final int somme;

    public TousLesJoueursArgentAction(String libelle, int somme) {
        super(String.format("%s %d€", libelle, Math.abs(somme)));
        this.somme = somme;
    }

    @Override
    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.realiser(j);
        Maillon<Joueur> jc = Monopoly.getJoueurs().getEntree();
        for (int i = 0; i < Monopoly.getJoueurs().getNbElements(); i++) {
            if (!jc.get().equals(j)) {
                jc.get().payeA(j, this.somme);
            }
            jc = jc.suivant();
        }
    }
}
