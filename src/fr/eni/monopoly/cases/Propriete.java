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

    protected int txComplGroupe;
    private int prixAchat;
    private Joueur proprio;
    private Groupe groupe;
    private boolean hypothequee;

    public Propriete(String nom, int prixAchat, Groupe groupe) {
        super(nom);
        this.groupe = groupe;
        groupe.ajouterPropriete(this);
        this.prixAchat = prixAchat;
        this.txComplGroupe = 0;
        this.hypothequee = false;
    }

    /**
     * {@inheritDoc}
     *
     * @throws FailliteException
     */
    @Override
    public void joueurArrive(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.joueurArrive(j);
        if (this.proprio == null) {
            if (Outils.ouiNon(String.format("Voulez-vous acheter %s pour %d€ ?", this.nom, this.prixAchat))) {
                j.debiter(this.prixAchat);
                this.setProprio(j);
            }
        } else {
            if (j.equals(this.proprio))
                System.out.printf("%s est à domicile%n", j);
            else
                this.payerLoyer(j, this.proprio);
        }
    }

    /**
     *
     */
    private void calculerCompletionGroupe(Joueur j) {
        int nbPJ = 0;
        int nbPT = 0;
        Iterable<Propriete> groupeProp = this.groupe.getProprietes();
        for (Propriete p : groupeProp) {
            nbPT++;
            if (p.proprio == j)
                nbPJ++;
        }
        for (Propriete p : groupeProp) {
            if (p.proprio == j)
                p.txComplGroupe = 100 * nbPJ / nbPT;
        }
        System.out.printf("Groupe détenu à %d%% (%d/%d) par %s%n", 100 * nbPJ / nbPT, nbPJ, nbPT, j);
    }

    /**
     * Getter pour groupe.
     *
     * @return le groupe d'appartenance de la propriété
     */
    public Groupe getGroupe() {
        return groupe;
    }

    /**
     * Getter pour txComplGroupe.
     *
     * @return le pourcentage de complétion du groupe de propriétés
     */
    public int getTxComplGroupe() {
        return txComplGroupe;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Joueur getProprio() {
        return proprio;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProprio(Joueur j) {
        Joueur ancienProprio = this.proprio;
        this.proprio = j;
        if (ancienProprio != null)
            this.calculerCompletionGroupe(ancienProprio);
        this.calculerCompletionGroupe(j);
    }

    /**
     * Getter pour hypothequee.
     *
     * @return the hypothequee
     */
    public boolean isHypothequee() {
        return hypothequee;
    }

    /**
     * Setter pour hypothequee.
     *
     * @param hypothequee the hypothequee to set
     */
    public void setHypothequee(boolean hypothequee) {
        this.hypothequee = hypothequee;
    }

    /**
     * @param j
     */
    protected abstract void payerLoyer(Joueur j, Joueur proprio) throws FailliteException;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s (%s)", super.toString(), this.groupe);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValeur() {
        return this.prixAchat;
    }
}
