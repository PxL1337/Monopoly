package fr.eni.monopoly.cases;

import fr.eni.monopoly.Joueur;

/**
 * The type Terrain.
 */
public class Terrain extends Propriete{
    private int[] loyer;
    private int coutConstruction;
    private int niveauConstruction;
    private int nbMaisonsDispo;
    private int nbHotelsDispo;

    /**
     * Gets cout construction.
     *
     * @return the cout construction
     */
    public int getCoutConstruction() {
        return coutConstruction;
    }

    /**
     * Gets niveau construction.
     *
     * @return the niveau construction
     */
    public int getNiveauConstruction() {
        return niveauConstruction;
    }

    /**
     * Gets nb maisons dispo.
     *
     * @return the nb maisons dispo
     */
    public int getNbMaisonsDispo() {
        return nbMaisonsDispo;
    }

    /**
     * Gets nb hotels dispo.
     *
     * @return the nb hotels dispo
     */
    public int getNbHotelsDispo() {
        return nbHotelsDispo;
    }

    /**
     * Instantiates a new Terrain.
     *
     * @param nom              the nom
     * @param prixAchat        the prix achat
     * @param groupe           the groupe
     * @param coutConstruction the cout construction
     */
    public Terrain(String nom, int prixAchat, Groupe groupe, int coutConstruction){
        super(nom, prixAchat, groupe);
        this.coutConstruction = coutConstruction;
        this.niveauConstruction = 0;
        this.nbMaisonsDispo = 4;
        this.nbHotelsDispo = 1;
        this.loyer = new int[6];
        this.loyer[0] = prixAchat / 10;
        this.loyer[1] = prixAchat / 5;
        this.loyer[2] = prixAchat / 2;
        this.loyer[3] = prixAchat * 3 / 5;
        this.loyer[4] = prixAchat * 2 / 3;
        this.loyer[5] = prixAchat;
    }
@Override
    protected void payerLoyer(Joueur locataire, Joueur proprietaire){
        int loyer = this.loyer[this.niveauConstruction];
        if (this.niveauConstruction == 0 && this.txComplGroupe ==100)
            loyer *= 2;
        locataire.payeA(proprietaire, loyer);
    }

    /**
     * Construire.
     */
    public void construire(){
        if (this.niveauConstruction < 5){
            if (this.niveauConstruction < 4){
                if (this.nbMaisonsDispo > 0){
                    this.nbMaisonsDispo--;
                    this.niveauConstruction++;
                }
            } else {
                if (this.nbHotelsDispo > 0){
                    this.nbHotelsDispo--;
                    this.niveauConstruction++;
                }
            }
        }
    }
}
