package fr.eni.monopoly.cases;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.Detenable;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.util.Outils;

public abstract class Propriete extends Case implements Detenable {
    private int prixAchat;
    private Joueur proprio;
    private Groupe groupe;
    protected int txComplGroupe;
    private boolean hypotheque;
    private Joueur proprio;


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
        if (this.proprio == null) {
            if (Outils.ouiNon(String.format("Voulez-vous acheter %s pour%d€ ?", this.nom, this.prixAchat))) {
                j.debiter(this.prixAchat);
                this.setProprio(j);
            }
        } else {
            if (j.equals(this.proprio)) {
                System.out.printf("%s est chez lui%n", j);
            else{
                    this.payerLoyer(j, this.proprio);
                }
            }
        }
    }

    @Override
    public Joueur getProprio() {
        return this.proprio;
    }

    @Override
    public void setProprio(Joueur j){
        Joueur ancienProprio = this.proprio;
        this.proprio = j;
        if (ancienProprio != null)
            this.calculerCompletionGroupe(ancienProprio);
        this.calculerCompletionGroupe(j);
    }

    @Override
    public int getValeur() {
        return this.prixAchat;
    }


    private void calculerCompletionGroupe(Joueur j) {

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

    protected abstract payerLoyer(Joueur j, Joueur proprio);

    @Override
    public String toString() {
        return String.format("%s (%s))", super.toString(), this.groupe);
    }
}
