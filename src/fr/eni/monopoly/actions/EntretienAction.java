package fr.eni.monopoly.actions;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.monopoly.Monopoly;
import fr.eni.monopoly.cases.Case;
import fr.eni.monopoly.cases.Terrain;
import fr.eni.util.Maillon;

/**
 * The type Entretien action.
 */
public class EntretienAction extends Action {
    private final int coutMaison;
    private final int coutHotel;

    public EntretienAction(String libelle, int coutMaison, int coutHotel) {
        super(String.format(libelle, coutMaison, coutHotel));
        this.coutMaison = coutMaison;
        this.coutHotel = coutHotel;
    }

    @Override
    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.realiser(j);
        int nbMaison = 0;
        int nbHotel = 0;
        Maillon<Case> c = Monopoly.getPlateau().getEntree();
        for (int i = 0; i < Monopoly.getPlateau().getNbElements(); i++) {
            if (c.get() instanceof Terrain t) {
                if (j.equals(t.getProprio())) {
                    if (t.getNiveauConstruction() == 5)
                        nbHotel++;
                    else
                        nbMaison += t.getNiveauConstruction();
                }
            }
            c = c.suivant();
        }
        int cout = nbMaison * this.coutMaison + nbHotel * this.coutHotel;
        System.out.printf("Vous devez payer %d€ pour l'entretien de vos maisons et hotels\n", cout);
        j.debiter(cout);
    }
}
