package fr.eni.monopoly.bo;

public class Chance extends PicocherCase{
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
