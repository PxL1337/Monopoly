package fr.eni.monopoly.cases;

import fr.eni.monopoly.Joueur;

public class CaisseDeLaCommunaute extends PiocherCase {
    private Pioche carte;

    public CaisseDeLaCommunaute(String nom) {
        super(nom);
        carte = new Pioche();
    }

    protected tirerCarte(Joueur j) {
        carte.tirerCarte(j);
    }

    public remettreEnJeu() {
        carte.remettreEnJeu();
    }
}
