package fr.eni.monopoly.bo;

public class Gare extends Propriete{
    public Gare(String nom) {
        super(nom);
    }


    protected payerLoyer(Joueur j, Joueur proprio){
        int nbGare = 0;
        for (Propriete p : proprio.getPopriete()){
            if (p instanceof Gare){
                nbGare++;
            }
        }
        int loyer = 0;
        switch (nbGare){
            case 1:
                loyer = 25;
                break;
            case 2:
                loyer = 50;
                break;
            case 3:
                loyer = 100;
                break;
            case 4:
                loyer = 200;
                break;
        }
        j.setArgent(j.getArgent() - loyer);
        proprio.setArgent(proprio.getArgent() + loyer);
    }
}
