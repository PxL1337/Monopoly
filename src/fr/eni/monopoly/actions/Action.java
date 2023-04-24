package fr.eni.monopoly.actions;

import fr.eni.monopoly.Joueur;

public abstract class Action {
    private String Libelle;

    public Action(String libelle) {
        this.Libelle = libelle;
    }

    public String getLibelle() {
        return this.Libelle;
    }

    public void realiser(Joueur j) {
        System.out.printf("%s réalise l'action %s%n", j, this.Libelle);
    }

    @Override
    public String toString() {
        return this.Libelle;
    }

}
