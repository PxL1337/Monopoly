package fr.eni.util.jeu.deDes;

import java.util.Random;

public class De {
    private int nbFaces;
    private int FaceTiree;
    private static Random rand = new Random();


    public De(int nbFaces) {
        this.setNbFaces(nbFaces);
        this.lancer();
    }

    public De() {
        this(6);
    }

    public int getNbFaces() {
        return nbFaces;
    }

    public void setNbFaces(int nbFaces) {
        De.verifNbFaces(nbFaces);
        this.nbFaces = nbFaces;
    }

    private static void verifNbFaces(int nbFaces) {
        if (nbFaces <= 1) {
            throw new IllegalArgumentException("Un dé doit avoir au moins 2 faces");
        }
    }

    public int getFaceTiree() {
        return this.FaceTiree;
    }

    public int lancer() {
        return this.FaceTiree = rand.nextInt(this.nbFaces) + 1;
    }
}
