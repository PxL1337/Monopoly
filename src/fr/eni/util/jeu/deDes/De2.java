package fr.eni.util.jeu.deDes;

import java.util.Random;

/**
 * The type De 2.
 */
public class De2 {
    private static Random rand = new Random();
    private int nbFaces;
    private int FaceTiree;

    private De2() {
    }

    /**
     * Creer de de 2.
     *
     * @param nbFaces the nb faces
     * @return the de 2
     */
    public static De2 creerDe(int nbFaces) {
        De2.verifNbFaces(nbFaces);
        De2 d = new De2();
        d.setNbFaces(nbFaces);
        d.lancer();
        return d;
    }

    private static void verifNbFaces(int nbFaces) {
        if (nbFaces <= 1) {
            throw new IllegalArgumentException("Un dé doit avoir au moins 2 faces");
        }
    }

    /**
     * Gets nb faces.
     *
     * @return the nb faces
     */
    public int getNbFaces() {
        return nbFaces;
    }

    private void setNbFaces(int nbFaces) {
        De2.verifNbFaces(nbFaces);
        this.nbFaces = nbFaces;
    }

    /**
     * Lancer int.
     *
     * @return the int
     */
    public int lancer() {
        return this.FaceTiree = rand.nextInt(this.nbFaces) + 1;
    }

    /**
     * Gets face tiree.
     *
     * @return the face tiree
     */
    public int getFaceTiree() {
        return this.FaceTiree;
    }

}
