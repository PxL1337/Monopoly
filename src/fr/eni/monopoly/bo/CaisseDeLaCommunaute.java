package fr.eni.monopoly.bo;

public class CaisseDeLaCommunaute extends PicocherCase{
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
