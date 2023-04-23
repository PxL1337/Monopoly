package fr.eni.monopoly.bo;

public abstract class Propriete extends Case{
    private int prixAchat;
    private Joueur proprio;
    private Groupe groupe;
    protected int txComplGroupe;
    private boolean hypotheque;


    public Propriete(String nom, int prixAchat, Groupe groupe) {
        super(nom);
        this.groupe;
        groupe.ajouterPropriete(this);
        this.prixAchat = prixAchat;
        this.txComplGroupe = 0;
        this.hypotheque = false;
    }

    public Groupe getGroupe(Propriete p){
        return groupe;
    }
    @Override
    public void joueurArrive(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.joueurArrive(j);
        if (this.proprio == null){
            if (Outils.ouiNon(String.format("Voulez-vous acheter %s pour%d€ ?", this.nom, this.prixAchat))){
                j.debiter(this.prixAchat);
                this.setProprio(j);
            }
        }else {
            if (j.equals(this.proprio)){
                System.out.printf("%s est chez lui%n", j);
            else {
                this.payerLoyer(j, this.proprio);
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

    public boolean isHypotheque() {
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

    protected abstract payerLoyer(Joueur j, Joueur proprio);

    @Override
    public String toString() {
        return String.format("%s (%s))", super.toString(), this.groupe);
    }
}
