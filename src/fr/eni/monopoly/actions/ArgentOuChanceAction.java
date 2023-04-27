package fr.eni.monopoly.actions;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.monopoly.cases.Chance;
import fr.eni.util.Outils;

import java.util.Arrays;
import java.util.List;

/**
 * The type Argent ou chance action.
 */
public class ArgentOuChanceAction extends Action {
    private int somme;

    /**
     * Instantiates a new Argent ou chance action.
     *
     * @param libelle the libelle
     * @param somme   the somme
     */
    public ArgentOuChanceAction(String libelle, int somme) {
        super(String.format("%s%d€ ou bien tirez une carte Chance", libelle, Math.abs(somme)));
        this.somme = somme;
    }

    @Override
    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.realiser(j);
        List<String> propositions = Arrays.asList("Payer", "Tirer une carte Chance");
        int choix = Outils.choix("Que choisissez-vous ?", propositions);
        if (choix == 0) {
            j.debiter(somme);
        } else {
            Action a = Chance.getCartes().tirer();
            a.realiser(j);
        }
    }
}
