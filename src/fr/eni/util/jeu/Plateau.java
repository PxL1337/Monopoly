package fr.eni.util.jeu;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Plateau.
 *
 * @param <T> the type parameter
 */
public class Plateau<T extends Affichable> {
    private List<T> plateauDeJeu;
    private int hauteur;
    private int largeur;


    /**
     * Instantiates a new Plateau.
     *
     * @param hauteur        the hauteur
     * @param largeur        the largeur
     * @param valeurInitiale the valeur initiale
     */
    public Plateau(int hauteur, int largeur, T valeurInitiale) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.plateauDeJeu = new ArrayList<>(hauteur * largeur);
        for (int k = 0; k < hauteur * largeur; k++) {
            this.plateauDeJeu.add(valeurInitiale);
        }
    }

    /**
     * Afficher.
     */
    public void afficher() {
        System.out.println();
        for (int i = 1; i <= this.largeur; i++)
            System.out.printf("%d ", i);
        System.out.println();
        for (int j = 0; j < this.hauteur; j++) {
            System.out.printf("%d ", j + 1);
            for (int i = 0; i < this.largeur; i++) {
                System.out.printf("%s ", this.get(i, j).getSymbole());
            }
            System.out.println();
        }
    }

    /**
     * Get t.
     *
     * @param ligne   the ligne
     * @param colonne the colonne
     * @return the t
     */
    public T get(int ligne, int colonne) {
        return this.plateauDeJeu.get(ligne * this.hauteur + colonne);
    }

    /**
     * Set.
     *
     * @param ligne   the ligne
     * @param colonne the colonne
     * @param valeur  the valeur
     */
    public void set(int ligne, int colonne, T valeur) {
        this.plateauDeJeu.set(ligne * this.hauteur + colonne, valeur);
    }
}
