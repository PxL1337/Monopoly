package fr.eni.monopoly.bo;

public class Propriete extends Case{
    private int prixAchat;
    private Joueur proprio;
    private Groupe groupe;
    private int txComplGroupe;
    private boolean hypotheque;


    public Propriete(int prixAchat, Groupe groupe) {
        this.groupe;
        groupe.ajouterPropriete(this);
        this.prixAchat = prixAchat;
        this.txComplGroupe = 0;
        this.hypotheque = false;
    }

    public Groupe getGoupre(){
        return groupe;
    }
    public joueurArrive(Joueur j) {
        if (this.proprio == null) {
            if (j.getArgent() >= this.prixAchat) {
                j.setArgent(j.getArgent() - this.prixAchat);
                this.setProprio(j);
            }
        } else {
            if (this.proprio != j) {
                int montant = this.prixAchat * this.txComplGroupe / 100;
                j.setArgent(j.getArgent() - montant);
                this.proprio.setArgent(this.proprio.getArgent() + montant);
            }
        }
    }

    public Joueur getProprio() {
        return proprio;
    }

    public void setProprio(Joueur j){
        Joueur oldProprio = this.proprio;
        this.proprio = j;
        if (oldProprio != null)
            this.clalculerCompletionGroupe(oldProprio);
        this.clalculerCompletionGroupe(j);
    }

    private void clalculerCompletionGroupe(Joueur j) {

    }

    public Groupe getGroupe() {
        return groupe;
    }

    public int getCompletionGroupe() {
        return txComplGroupe;
    }

    public booblean isHypotheque() {
        return hypotheque;
    }

    public void setHypotheque(boolean hypotheque) {
        this.hypotheque = hypotheque;
    }

    public int getValeur() {
        if (this.hypotheque)
            return this.prixAchat / 2;
        return this.prixAchat;
    }
}
