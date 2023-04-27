package fr.eni.monopoly.cases;

import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;

/**
 * The type Terrain.
 */
public class Terrain extends Propriete {
    private static int nbMaisonsDispo = 32;
    private static int nbHotelsDispo = 12;
    private int[] loyer;
    private int coutConstruction;
    private int niveauConstruction;

    public Terrain(String nom, int prixAchat, int[] loyers, Groupe groupe, int coutConstruction) {
        super(nom, prixAchat, groupe);
        this.loyer = loyers;
        this.coutConstruction = coutConstruction;
        this.niveauConstruction = 0;
    }

    public static int getNbMaisonsDispo() {
        return nbMaisonsDispo;
    }

    public static int getNbHotelsDispo() {
        return nbHotelsDispo;
    }

    @Override
    public void payerLoyer(Joueur locataire, Joueur proprietaire) throws FailliteException {
        int loyer = this.loyer[this.niveauConstruction];
        if (this.niveauConstruction == 0 && this.txComplGroupe == 100)
            loyer *= 2;
        locataire.payeA(proprietaire, loyer);
    }

    public int getCoutConstruction() {
        return coutConstruction;
    }

    public int getNiveauConstruction() {
        return niveauConstruction;
    }

    public void construire() {
        if (this.niveauConstruction < 4)
            Terrain.nbMaisonsDispo--;
        else if (this.niveauConstruction == 4) {
            Terrain.nbMaisonsDispo += 4;
            Terrain.nbHotelsDispo--;
        }
        this.niveauConstruction++;
    }

}
