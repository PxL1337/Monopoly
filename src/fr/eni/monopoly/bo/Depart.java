package fr.eni.monopoly.bo;

/**
 * The type Depart.
 */
public class Depart extends Case{

    public int Salaire = 200;

    /**
     * Instantiates a new Depart.
     *
     * @param nom the nom
     */
    public Depart() {
        super("Départ");
    }

    @Override
    public joueurPasser(Joueur j) {
        payerSalaire(j);
    }

    @Override
    public JoueurArrive(Joueur j) {
        payerSalaire(j) * 2;
    }

    private payerSalaire(Joueur j) {
        j.setArgent(j.getArgent() + Salaire);
    }
}
