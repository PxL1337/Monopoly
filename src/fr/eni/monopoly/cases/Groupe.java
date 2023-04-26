package fr.eni.monopoly.cases;

import java.util.ArrayList;

public enum Groupe {
    GARE, COMPAGNIE, MAUVE, BLEU_CIEL, ROSE, ORANGE, ROUGE, JAUNE, VERT, BLEU_FONCE;
    private ArrayList<Propriete> proprietes = new ArrayList<Propriete>();
    private String nom;
    private Propriete[] proprietes = new Propriete[4]
    private String couleur;
    private int nbProp = 0;




    void ajouterPropriete(Propriete p){
        if (this.equals(p.getGroupe())){
            this.proprietes.add(p);
        }
    }

    public ArrayList<Propriete> getProprietes(){
        return this.proprietes;
    }

    public int getNbPropriete(){
        return this.proprietes.size();
    }
}
