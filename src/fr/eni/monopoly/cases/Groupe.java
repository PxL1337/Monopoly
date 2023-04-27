package fr.eni.monopoly.cases;

import java.util.ArrayList;

/**
 * The enum Groupe.
 */
public enum Groupe {
    /**
     * Gare groupe.
     */
    GARE,
    /**
     * Compagnie groupe.
     */
    COMPAGNIE,
    /**
     * Mauve groupe.
     */
    MAUVE,
    /**
     * Bleu ciel groupe.
     */
    BLEU_CIEL,
    /**
     * Rose groupe.
     */
    ROSE,
    /**
     * Orange groupe.
     */
    ORANGE,
    /**
     * Rouge groupe.
     */
    ROUGE,
    /**
     * Jaune groupe.
     */
    JAUNE,
    /**
     * Vert groupe.
     */
    VERT,
    /**
     * Bleu fonce groupe.
     */
    BLEU_FONCE;
    private ArrayList<Propriete> proprietes = new ArrayList<Propriete>();
    private String nom;
    private Propriete[] proprietes = new Propriete[4]
    private String couleur;
    private int nbProp = 0;


    /**
     * Ajouter propriete.
     *
     * @param p the p
     */
    void ajouterPropriete(Propriete p){
        if (this.equals(p.getGroupe())){
            this.proprietes.add(p);
        }
    }

    /**
     * Get proprietes array list.
     *
     * @return the array list
     */
    public ArrayList<Propriete> getProprietes(){
        return this.proprietes;
    }

    /**
     * Get nb propriete int.
     *
     * @return the int
     */
    public int getNbPropriete(){
        return this.proprietes.size();
    }
}
