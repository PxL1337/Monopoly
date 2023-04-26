package fr.eni.util.jeu.deDes;

import java.util.Random;

public class De2 {
    private int nbFaces;
    private int FaceTiree;
    private static Random rand = new Random();

    private De2() {
    }

    public static De2 creerDe(int nbFaces) {
        De2.verifNbFaces(nbFaces);
        De2 d = new De2();
        d.setNbFaces(nbFaces);
        d.lancer();
        return d;
    }

    public int getNbFaces() {
        return nbFaces;
    }

    private void setNbFaces(int nbFaces) {
        De2.verifNbFaces(nbFaces);
        this.nbFaces = nbFaces;
    }

    private static void verifNbFaces(int nbFaces) {
        if (nbFaces <= 1) {
            throw new IllegalArgumentException("Un dé doit avoir au moins 2 faces");
        }
    }

    public int lancer() {
        return this.FaceTiree = rand.nextInt(this.nbFaces) + 1;
    }

    public int getFaceTiree() {
        return this.FaceTiree;
    }

}
