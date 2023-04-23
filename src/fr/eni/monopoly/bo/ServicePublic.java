package fr.eni.monopoly.bo;

public class ServicePublic extends Propriete{
    public ServicePublic(String nom) {
        super(nom);
    }


    protected payerLoyer(Joueur j, Joueur proprio){
        int nbService = 0;
        for (Propriete p : proprio.getProprietes()){
            if (p instanceof ServicePublic){
                nbService++;
            }
        }
        int loyer = 0;
        switch (nbService){
            case 1:
                loyer = 4 * j.getLanceDe();
                break;
            case 2:
                loyer = 10 * j.getLanceDe();
                break;
        }
        j.setArgent(j.getArgent() - loyer);
        proprio.setArgent(proprio.getArgent() + loyer);
    }
}
