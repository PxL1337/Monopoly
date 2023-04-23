package fr.eni.monopoly.bo;

public class Terrain extends Propriete{
    private int[] loyer;
    private int coutConstruction;
    private int niveauConstruction;
    private int nbMaisonsDispo;
    private int nbHotelsDispo;

    public int getCoutConstruction() {
        return coutConstruction;
    }

    public int getNiveauConstruction() {
        return niveauConstruction;
    }

    public int getNbMaisonsDispo() {
        return nbMaisonsDispo;
    }

    public int getNbHotelsDispo() {
        return nbHotelsDispo;
    }

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

    protected payerLoyer(Joueur j, Joueur proprio){
        int montant = this.loyer[this.niveauConstruction];
        j.setArgent(j.getArgent() - montant);
        proprio.setArgent(proprio.getArgent() + montant);
    }

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
