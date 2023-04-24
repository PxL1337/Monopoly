package fr.eni.util.jeu.deDes;

public class JeuException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private int nbFaces;

    public void setNbFaces(int nbFaces) {
        De.verifNbFaces(nbFaces);
        this.nbFaces = nbFaces;
    }

    private static void verifNbFaces(int nbFaces) {
        if (nbFaces <= 1)
            throw new JeuException("Le nombre de faces doit être supérieur à 1");
    }
}
