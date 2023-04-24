package fr.eni.monopoly;


public class FailliteException extends Exception{
    private static final long serialVersionUID = 1L;
    private Joueur joueur;
    public FailliteException(String message, Joueur j) {
        super(message);
        this.joueur = j;
    }
    public Joueur getJoueur() {
        return this.joueur;
    }
}
