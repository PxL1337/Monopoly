package fr.eni.util;

/**
 * The type Anneau.
 *
 * @param <T> the type parameter
 */
public class Anneau<T> {
    private Maillon<T> ptEntree;
    private int nbElements = 0;

    /**
     * Ajouter maillon.
     *
     * @param element the element
     * @return the maillon
     */
    public Maillon<T> ajouter(T element) {
        this.nbElements++;
        if (this.ptEntree == null) {
            this.ptEntree = new Maillon<T>(element, null);
            this.ptEntree.setSuivant(this.ptEntree);
            return this.ptEntree;
        } else {
            Maillon<T> nouveau = new Maillon<T>(element, this.ptEntree.suivant());
            this.ptEntree.setSuivant(nouveau);
            this.ptEntree = nouveau;
        }
        return this.ptEntree;
    }

    /**
     * Retirer.
     *
     * @param element the element
     */
    public void retirer(T element) {
        if (this.nbElements > 0) {
            int i = 0;
            Maillon<T> courant = this.ptEntree;
            while (i < this.nbElements && courant.suivant().get() != element) {
                i++;
                courant = courant.suivant();
            }
            if (courant.suivant().get() == element) {
                this.nbElements--;
                if (this.nbElements == 0)
                    this.ptEntree = null;
                else if (this.ptEntree == courant.suivant())
                    this.ptEntree = courant;
                courant.setSuivant(courant.suivant().suivant());
            }
        }
    }

    /**
     * Gets nb elements.
     *
     * @return the nb elements
     */
    public int getNbElements() {
        return nbElements;
    }

    @Override
    public String toString() {
        if (this.ptEntree == null)
            return "[vide]";
        return this.ptEntree.toString(this.nbElements);
    }

    /**
     * Gets pt entree.
     *
     * @return the pt entree
     */
    public Maillon<T> getPtEntree() {
        return this.ptEntree;
    }
}
