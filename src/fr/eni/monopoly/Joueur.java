package fr.eni.monopoly;

import fr.eni.monopoly.cases.Case;
import fr.eni.monopoly.cases.Groupe;
import fr.eni.monopoly.cases.Propriete;
import fr.eni.monopoly.cases.Terrain;
import fr.eni.util.Maillon;
import fr.eni.util.Outils;

import java.util.ArrayList;
import java.util.Iterator;
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

    private void echanger() {
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
        int sous = Outils.saisie("Compensation financière (négative si c'est vous qui payez). En tout équité cela représente " + compEq + "€", -this.argent, adv.argent);
        if(Outils.ouiNon(String.format("#> %s, acceptez-vous cet échange ?", adv))) {
            for (Detenable d : desirs)
                d.setProprio(this);
            for (Detenable d : troc)
                d.setProprio(adv);
            if (sous > 0)
                adv.payeA(this, sous);
            else
                this.payeA(adv, -sous);
        }
    }

    private List<Detenable> choixCartes(Joueur j) {
        List<Detenable> cartes = new ArrayList<>();
        List<Detenable> possJ = j.getCartesEchangeables();
        System.out.println("Caertes détenues par " + j);
        int nb = 0;
        for (Detenable c : possJ) {
            System.out.println(c);
            nb++;
        }
        while (nb > 0 && Outils.ouiNon("Selectionner une carte ?")) {
            cartes.add(possJ.remove(Outils.choix("Quelle carte ?", possJ)));
            nb--;
        }
        return cartes;
    }

    private void construire(Groupe groupe) throws FailliteException {
        Iterable<Propriete> proprietes = groupe.getProprietes();
        Terrain t = (Terrain) proprietes.iterator().next();
        int nbProp = 0;
        int nbConstructions = 0;
        for(Propriete p : proprietes) {
            nbConstructions += ((Terrain)p).getNiveauConstruction();
            nbProp++;
        }
        if (nbConstructions / nbProp < 5) {
            int nbConsManq = nbProp * 5 - nbConstructions;
            String s = nbConsManq > 1 ? "s" : "";
            int nb = Outils.saisie(String.format("Combien voulez-vous construire sur le goupe %s (Il reste %d construction%s possible%s sur ce groupe, %d€ par construction)", groupe, nbConsManq, s, s, t.getCoutConstruction(), 0,nbConsManq);
            if (nb > 0) {
                int nbMaisonsConstructibles = nbProp * 4 - nbConstructions;
                int nbMaisonsAConstruire = Math.min(nbMaisonsConstructibles, nb);
                int nbDispo = Terrain.getNbMaisonsDispo();
                if (nbMaisonsAConstruire > nbDispo) {
                    s = nbDispo > 1 ? "s" : "";
                    System.out.printf("Il y a actuellement %d maison%s disponible%s\n", nbDispo, s, s);
                    nb = nbDispo;
                }
                //TODO : Vérifier stock Hotel
                if (nb > 0) {
                    this.debiter(nb * t.getCoutConstruction());
                    // égalisation
                    List<Terrain> moinsFournies = new ArrayList<>();
                    moinsFournies.add(t);
                    int nivMin = t.getNiveauConstruction();
                    for (Propriete p : proprietes) {
                        Terrain ter = (Terrain) p;
                        if (ter.getNiveauConstruction() < nivMin) {
                            moinsFournies.clear();
                            moinsFournies.add(ter);
                            nivMin = ter.getNiveauConstruction();
                        } else if (ter.getNiveauConstruction() == nivMin) {
                            moinsFournies.add(ter);
                        }
                    }
                    if (nb >= moinsFournies.size()) {
                        for (Terrain ter : moinsFournies) {
                            ter.construire();
                            nb--;
                        }
                        moinsFournies.clear();
                        for (Propriete p : proprietes) {
                            moinsFournies.add((Terrain) p);
                        }
                        int nbPourTous = nb / nbProp;
                        if (nbPourTous != 0) {
                            nb -= nbPourTous * nbProp;
                            for(Propriete p : proprietes){
                                Terrain ter = (Terrain) p;
                                for (int i = 0; i < nbPourTous; i++) {
                                    ter.construire();
                                }
                            }
                        }
                    }
                    while(nb > 0) {
                        Terrain ter = moinsFournies.get(Outils.choix("Sur quelle propriété voulez-vous construire ?", moinsFournies));
                        ter.construire();
                        nb--;
                        moinsFournies.remove(ter);
                    }
                }
            }
        }
    }

    private List<Groupe> getGroupesConstructibles() {
        List<Groupe> groupes = new ArrayList<>();
        for (Groupe g : Groupe.values()) {
            Iterable<Propriete> proprietes = g.getProprietes();
            Propriete pr = proprietes.iterator().next();
            if (pr instanceof Terrain && this.equals(pr.getProprio()) && pr.getTxComplGroupe() == 100) {
                int nbProp = g.getNbProprietes();
                int nbConstructions = 0;
                for (Propriete p : proprietes)
                    nbConstructions += ((Terrain) p).getNiveauConstruction();
                if (nbConstructions / nbProp < 5) {
                    groupes.add(g);
                }

            }
        }
        return groupes;
    }



    /**
     * Paye a.
     *
     * @param j     the j
     * @param somme the somme
     * @throws FailliteException the faillite exception
     */
    public void payeA(Joueur j, int somme) throws FailliteException {
        System.out.printf("%s paye %d€ à %s%n", this.nom, somme, j);
        this.debiter(somme);
        j.crediter(somme);
    }

    public boolean possedeLibereDePrison() {
        return this.equals(Monopoly.getLibereCaisse().getProprio()) || this.equals(Monopoly.getLibereChance().getProprio());
    }

    public void deplacer(Maillon<Case> cible, boolean direct) throws FailliteException, AllerEnPrisonException {
    while (!this.position.suivant().equals(cible)) {
        this.position = this.position.suivant();
        if(!direct)
            this.position.get().joueurPasse(this);
        }
        this.position = this.position.suivant();
        this.position.get().joueurArrive(this);
    }

    public void deplacer(int nb) throws FailliteException, AllerEnPrisonException {
        for (int i = 0; i < nb; i++)
            this.position = this.position.suivant();
        this.position.get().joueurArrive(this);
    }

    public List<Detenable> getCartesEchangeables() {
        List<Detenable> cartes = new ArrayList<>();
        Maillon<Case> courant = Monopoly.getPlateau().getEntree();
        for (int i = 0; i < Monopoly.getPlateau().getNbElements(); i++) {
            if (courant.get() instanceof Propriete) {
                Propriete p = (Propriete) courant.get();
                if (this.equals(p.getProprio())) {
                    if (p instanceof Terrain) {
                        boolean terrainNu = true;
                        Iterable<Propriete> prop = p.getGroupe().getProprietes();
                        Iterator<Propriete> itProp = prop.iterator();
                        while (terrainNu && itProp.hasNext())
                            terrainNu = ((Terrain) itProp.next()).getNiveauConstruction() == 0;
                        if (terrainNu)
                            cartes.add(p);
                    } else {
                        cartes.add(p);
                    }
                }
            }
            courant = courant.suivant();
        }
        if (this.equals(Monopoly.getLibereCaisse().getProprio()))
            cartes.add(Monopoly.getLibereCaisse());
        if (this.equals(Monopoly.getLibereChance().getProprio()))
            cartes.add(Monopoly.getLibereChance());
        return cartes;
    }

}
