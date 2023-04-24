package fr.eni.monopoly.cases;

import fr.eni.monopoly.Joueur;

public class Chance extends PiocherCase {
    private Pioche carte;

    public Chance() {
        super("Chance");
        carte = new Pioche();
    }

    protected tirerCarte(Joueur j) {
        carte.tirerCarte(j);
    }

    public remettreEnJeu() {
        carte.remettreEnJeu();
    }
}
