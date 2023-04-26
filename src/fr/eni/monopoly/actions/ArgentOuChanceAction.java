package fr.eni.monopoly.actions;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.util.Outils;
import fr.eni.monopoly.cases.Chance

import java.util.Arrays;
import java.util.List;

public class ArgentOuChanceAction extends Action{
    private int somme;

    public ArgentOuChanceAction(String libelle, int somme) {
        super(String.format("%s%d€ ou bien tirez une carte Chance", libelle, Math.abs(somme)));
        this.somme = somme;
    }

    @Override
    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.realiser(j);
        List<String> propositions = Arrays.asList(...a:"Payer", "Tirer une carte Chance");
        int choix = Outils.choix("Que choisissez-vous ?", propositions);
        if(choix == 0){
            j.debiter(somme);
        } else {
            Action a = Chance.getCartes().tirer();
            a.realiser(j);
        }
    }
}
