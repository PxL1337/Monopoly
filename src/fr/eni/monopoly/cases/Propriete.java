package fr.eni.monopoly.cases;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.Detenable;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.util.Outils;

/**
 * The type Propriete.
 */
public abstract class Propriete extends Case implements Detenable {
    private int prixAchat;
    private Joueur proprio;
    private Groupe groupe;
    /**
     * The Tx compl groupe.
     */
    protected int txComplGroupe;
    private boolean hypotheque;
    private Joueur proprio;


    /**
     * Instantiates a new Propriete.
     *
     * @param nom       the nom
     * @param prixAchat the prix achat
     * @param groupe    the groupe
     */
    public Propriete(String nom, int prixAchat, Groupe groupe) {
        super(nom);
        this.groupe;
        groupe.ajouterPropriete(this);
        this.prixAchat = prixAchat;
        this.txComplGroupe = 0;
        this.hypotheque = false;
    }

    /**
     * Get groupe groupe.
     *
     * @param p the p
     * @return the groupe
     */
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
            }else{
                    this.payerLoyer(j, this.proprio);
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

    /**
     * Gets groupe.
     *
     * @return the groupe
     */
    public Groupe getGroupe() {
        return groupe;
    }

    /**
     * Gets completion groupe.
     *
     * @return the completion groupe
     */
    public int getCompletionGroupe() {
        return txComplGroupe;
    }

    /**
     * Is hypotheque boolean.
     *
     * @return the boolean
     */
    public boolean isHypotheque() {
        return hypotheque;
    }

    /**
     * Sets hypotheque.
     *
     * @param hypotheque the hypotheque
     */
    public void setHypotheque(boolean hypotheque) {
        this.hypotheque = hypotheque;
    }

    /**
     * Payer loyer.
     *
     * @param j       the j
     * @param proprio the proprio
     */
    protected abstract void payerLoyer(Joueur j, Joueur proprio);

    @Override
    public String toString() {
        return String.format("%s (%s))", super.toString(), this.groupe);
    }
}
