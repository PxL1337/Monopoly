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

    /**
     * Sets argent.
     *
     * @param argent the argent
     */
    public void setArgent(int argent) {
        this.argent = argent;
    }

    /**
     * Gets argent.
     *
     * @return the argent
     */
    public int getArgent() {
        return argent;
    }

    /**
     * Debiter.
     *
     * @param prix the prix
     */
    public void debiter(int prix) {
        this.argent -= prix;
    }

    /**
     * Crediter.
     *
     * @param SALAIRE the salaire
     */
    public void crediter(int SALAIRE) {
        this.argent += SALAIRE;
    }
}
