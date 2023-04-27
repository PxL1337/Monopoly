package fr.eni.monopoly;

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
     * @param somme the prix
     * @throws FailliteException the faillite exception
     */
    public void debiter(int somme) throws FailliteException {
        this.argent -= somme;
        if (this.argent < 0) {
            throw new FailliteException("Le joueur " + this.nom + " est en faillite, il quitte la partie !", this);
        }
    }

    /**
     * Crediter.
     *
     * @param SALAIRE the salaire
     */
    public void crediter(int SALAIRE) {
        this.argent += SALAIRE;
    }

    /**
     * Paye a.
     *
     * @param j     the j
     * @param somme the somme
     * @throws FailliteException the faillite exception
     */
    public void payeA(Joueur j, int somme) throws FailliteException {
        this.debiter(somme);
        j.crediter(somme);
    }
}
