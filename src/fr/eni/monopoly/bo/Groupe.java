package fr.eni.monopoly.bo;

public class Groupe {
    private Propriete[] proprietes = new Propriete[4]
    private String couleur;
    private String nbProp = 0;

    void ajouterPropriete(Propriete p){
        if (this.equals(p.getGroupe())){
            this.proprietes[this.nbProp] = p;
            this.nbProp++;
        }
    }

    public Propriete[] getProprietes(){
        return this.proprietes.clone();
    }

    public int getNbPropriete(){
        return this.proprietes.size();
    }
}
