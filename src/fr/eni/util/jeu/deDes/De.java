package fr.eni.util.jeu.deDes;

import java.util.Random;

/**
 * The type De.
 */
public class De {
    private int nbFaces;
    private int FaceTiree;
    private static Random rand = new Random();


    /**
     * Instantiates a new De.
     *
     * @param nbFaces the nb faces
     */
    public De(int nbFaces) {
        this.setNbFaces(nbFaces);
        this.lancer();
    }

    /**
     * Instantiates a new De.
     */
    public De() {
        this(6);
    }

    /**
     * Gets nb faces.
     *
     * @return the nb faces
     */
    public int getNbFaces() {
        return nbFaces;
    }

    /**
     * Sets nb faces.
     *
     * @param nbFaces the nb faces
     */
    public void setNbFaces(int nbFaces) {
        De.verifNbFaces(nbFaces);
        this.nbFaces = nbFaces;
    }

    private static void verifNbFaces(int nbFaces) {
        if (nbFaces <= 1) {
            throw new IllegalArgumentException("Un dé doit avoir au moins 2 faces");
        }
    }

    /**
     * Gets face tiree.
     *
     * @return the face tiree
     */
    public int getFaceTiree() {
        return this.FaceTiree;
    }

    /**
     * Lancer int.
     *
     * @return the int
     */
    public int lancer() {
        return this.FaceTiree = rand.nextInt(this.nbFaces) + 1;
    }
}
