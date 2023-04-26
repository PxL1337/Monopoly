package fr.eni.monopoly.actions;

import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.monopoly.AllerEnPrisonException;

public abstract class Action {
    private String libelle;

    public Action(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void realiser(Joueur j) throws FailliteException, AllerEnPrisonException {
        System.out.printf("Carte Triée %s%n", libelle);
    }

    @Override
    public String toString() {
        return "Carte" + libelle;
    }

}
