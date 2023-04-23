package fr.eni.monopoly.bo;

/**
 * The type Joueur.
 */
public class Joueur {
    private int argent;
    private String nom;
    private int position;

    /**
     * Instantiates a new Joueur.
     *
     * @param nom the nom
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.argent = 1500;
        this.position = 0;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public int getArgent() {
        return argent;
    }
}
