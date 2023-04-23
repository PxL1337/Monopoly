package fr.eni.monopoly.bo;

/**
 * The type Case.
 */
public class Case {
    /**
     * The Nom.
     */
    protected String nom;

    /**
     * Instantiates a new Case.
     *
     * @param nom the nom
     */
    public Case(String nom) {
        this.nom = nom;
    }

    /**
     * Joueur part boolean.
     *
     * @param j the j
     * @return the boolean
     */
    public boolean joueurPart(Joueur j) {
        System.out.printf("%s est sur la case %s%n", j, this.nom);
        return true;
    }

    public void joueurPasse(Joueur j) {

    }

    public void joueurArrive(Joueur j) {
        System.out.printf("%s arrive sur la case %s%n", j, this.nom);
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
