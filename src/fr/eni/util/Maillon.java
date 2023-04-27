package fr.eni.util;

/**
 * The type Maillon.
 *
 * @param <T> the type parameter
 */
public class Maillon<T> {

    private T valeur;
    private Maillon<T> suivant;

    /**
     * Instantiates a new Maillon.
     *
     * @param valeur  the valeur
     * @param suivant the suivant
     */
    public Maillon(T valeur, Maillon<T> suivant) {
        this.valeur = valeur;
        this.suivant = suivant;
    }

    /**
     * Get t.
     *
     * @return the t
     */
    public T get() {
        return this.valeur;
    }

    /**
     * Suivant maillon.
     *
     * @return the maillon
     */
    public Maillon<T> suivant() {
        return this.suivant;
    }

    /**
     * To string string.
     *
     * @param max the max
     * @return the string
     */
    public String toString(int max) {
        if (max > 0) {
            return this.valeur + " > " + this.suivant.toString(max - 1);
        } else {
            return "...";
        }
    }

    /**
     * Sets suivant.
     *
     * @param maillon the maillon
     */
    public void setSuivant(Maillon<T> maillon) {
        this.suivant = maillon;
    }

}
