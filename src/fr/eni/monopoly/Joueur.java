package fr.eni.monopoly;

import fr.eni.monopoly.cases.Case;
import fr.eni.monopoly.cases.Groupe;
import fr.eni.util.Maillon;
import fr.eni.util.Outils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Joueur.
 */
public class Joueur {
    private int argent;
    private String nom;
    private Maillon<Case> position;

    /**
     * Instantiates a new Joueur.
     *
     * @param nom the nom
     */
    public Joueur(String nom, Maillon<Case> positionInitiale) {
        this.nom = nom;
        this.argent = 15000;
        this.position = positionInitiale;
    }


    /**
     * Gets argent.
     *
     * @return the argent
     */
    public int getArgent() {
        return argent;
    }

    public void setPosition(Maillon<Case> position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return this.nom;
    }



    /**
     * Debiter.
     *
     * @param somme the prix
     * @throws FailliteException the faillite exception
     */
    public void debiter(int somme) throws FailliteException {
        this.argent -= somme;
        if (this.argent < 0) {
            System.out.printf("Le joueur %s doit payer %d€, mais il n'a plus d'argent !\n", this.nom, somme);
            throw new FailliteException("Le joueur " + this.nom + " est en faillite, il quitte la partie !", this);
        }
    }

    /**
     * Crediter.
     *
     * @param somme the salaire
     */
    public void crediter(int somme) {
        this.argent += somme;
    }

    public void jouer(int de1, int de2) throws FailliteException, AllerEnPrisonException {
        System.out.printf("%s a fait %d et %d aux dés%n", this.nom, de1, de2);
        if (this.position.get().joueurPart(this)) {
            for (int i = 0; i < de1 + de2 - 1; i++) {
                this.position = this.position.suivant();
                this.position.get().joueurPasse(this);
            }
            this.position = this.position.suivant();
            this.position.get().joueurArrive(this);
        }
        int choix;
        do {
            List<String> propositions = new ArrayList<>();
            propositions.add("Finir le tour");
            propositions.add("Proposer un echange de terrain");
            List<Groupe> constructibles = this.getGroupesConstructibles();
            for (Groupe g : constructibles) {
                propositions.add("Construire sur le " + g);
            }
            choix = Outils.choix("Que voulez-vous faire ?", propositions);
            if (choix == 1)
                echanger();
            if (choix > 1)
                construire(constructibles.get(choix - 2));
        } while (choix != 0);
    }

    /*private void echanger() {
        List<Joueur> adversaires = new ArrayList<>();
        Maillon<Joueur> courant = Monopoly.getJoueurs().getEntree();
        for (int i = 0; i < Monopoly.getJoueurs().getNbElements(); i++) {
            if (!this.equals(courant.get()))
                adversaires.add(courant.get());
            courant = courant.suivant();
        }
        int choix = Outils.choix("Avec qui voulez-vous échanger ?", adversaires);
        Joueur adv = adversaires.get(choix);
        List<Detenable> desirs = choixCartes(adv);
        List<Detenable> troc = choixCartes(this);
        int compEq = 0;
        for (Detenable d : desirs)
            compEq -= d.getValeur();
        for (Detenable d : troc)
            compEq += d.getValeur();
        int sous = Outils.saisie()
    }*/

    /**
     * Paye a.
     *
     * @param j     the j
     * @param somme the somme
     * @throws FailliteException the faillite exception
     */
    public void payeA(Joueur j, int somme) throws FailliteException {
        this.debiter(somme);
        j.crediter(somme);
    }

    public void deplacer(int decalage) {
    }
}
