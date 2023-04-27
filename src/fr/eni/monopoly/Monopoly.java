package fr.eni.monopoly;

import fr.eni.monopoly.actions.*;
import fr.eni.monopoly.cases.*;
import fr.eni.util.Anneau;
import fr.eni.util.Maillon;
import fr.eni.util.Outils;
import fr.eni.util.jeu.deDes.De;
import fr.eni.monopoly.cases.Groupe;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Monopoly.
 */
public class Monopoly {
    private static final Anneau<Joueur> joueurs = new Anneau<>();
    private static final Anneau<Case> plateau = new Anneau<>();
    private static Maillon<Case> prison;
    private static final De de1 = new De(6);
    private static final De de2 = new De(6);
    private static final LibereDePrison libereChance = new LibereDePrison();
    private static final LibereDePrison libereCaisse = new LibereDePrison();


    public static Anneau<Case> getPlateau() {
        return plateau;
    }

    public static Anneau<Joueur> getJoueurs() {
        return joueurs;
    }

    protected static LibereDePrison getLibereChance() {
        return libereChance;
    }

    protected static LibereDePrison getLibereCaisse() {
        return libereCaisse;
    }

    public static De getDe1() {
        return de1;
    }

    public static De getDe2() {
        return de2;
    }

    public static void main(String[] args) throws FileNotFoundException, AllerEnPrisonException {
        Monopoly.jouer();
    }

    private static void jouer() throws AllerEnPrisonException {
        Maillon<Joueur> joueurCourant = Monopoly.joueurs.getEntree();
        while (Monopoly.joueurs.getNbElements() > 1) {
            System.out.printf("C'est au tour de %s (solde : %d€)%n", joueurCourant.get(), joueurCourant.get().getArgent());
            int nbDoubles = 0;
            try {
                do {
                    try {
                        if (Monopoly.de1.lancer() == Monopoly.de2.lancer()) {
                            nbDoubles++;
                            if (nbDoubles == 3) {
                                System.out.printf("%s a fait %d et %d aux dés%n", joueurCourant.get(), Monopoly.de1.getFaceTiree(), Monopoly.de2.getFaceTiree());
                                throw new AllerEnPrisonException("3 doubles consécutifs");
                            }
                        }
                        joueurCourant.get().jouer(Monopoly.de1.getFaceTiree(), Monopoly.de2.getFaceTiree());
                    } catch (FailliteException e) {
                        Monopoly.joueurs.retirer(e.getJoueur());
                        System.err.println(e.getMessage());
                    }
                } while (Monopoly.de1.getFaceTiree() == Monopoly.de2.getFaceTiree());
            } catch (AllerEnPrisonException e) {
                if (e.getMessage() != null)
                    System.out.println(e.getMessage());
                System.out.printf("%s va en prison sans passer par la case départ et sans toucher 200€%n", joueurCourant.get());
                joueurCourant.get().setPosition(Monopoly.prison);
            }
            joueurCourant = joueurCourant.suivant();
        }
        System.out.printf("%s a gagné !%n", joueurCourant.get());
    }

    private static void init() {
        /////////////////////////// Création du plateau de jeu
        /////////////////////////// ///////////////////////////
		/*for(int i=0; i<10; i++)
			Monopoly.plateau.ajouter(new Chance());
		/*
		for(int i=0; i<10; i++)
			Monopoly.plateau.ajouter(new AllezEnPrison());*/

        int[] c1 = { 2, 10, 30, 90, 160, 250 };
        Maillon<Case> belleville = Monopoly.plateau.ajouter(new Terrain("Boulevard de Belleville", 60, c1, Groupe.MAUVE, 50));
        Monopoly.plateau.ajouter(new CaisseDeLaCommunaute());
        int[] c2 = { 4, 20, 60, 180, 320, 450 };
        Monopoly.plateau.ajouter(new Terrain("Rue Lecourbe", 60, c2, Groupe.MAUVE, 50));
        Monopoly.plateau.ajouter(new ImpotTaxe("Impôts sur le revenu", 200));
        Monopoly.plateau.ajouter(new Gare("Montparnasse"));
        int[] c3 = { 6, 30, 90, 270, 400, 550 };
        Monopoly.plateau.ajouter(new Terrain("Rue de Vaugirard", 100, c3, Groupe.BLEU, 50));
        Monopoly.plateau.ajouter(new Chance());
        Monopoly.plateau.ajouter(new Terrain("Rue de Courcelle", 100, c3, Groupe.BLEU, 50));
        int[] c4 = { 8, 40, 100, 300, 450, 600 };
        Monopoly.plateau.ajouter(new Terrain("Avenue de la République", 120, c4, Groupe.BLEU, 50));
        Monopoly.plateau.ajouter(new Case("Simple Visite de la prison"));
        int[] c5 = { 10, 50, 150, 450, 625, 750 };
        Maillon<Case> laVillette = Monopoly.plateau.ajouter(new Terrain("Boulevard de la Villette", 140, c5, Groupe.VIOLET, 100));
        Monopoly.plateau.ajouter(new ServicePublic("Compagnie de distribution d'électricité"));
        Monopoly.plateau.ajouter(new Terrain("Avenue de Neuilly", 140, c5, Groupe.VIOLET, 100));
        int[] c6 = { 12, 60, 180, 500, 700, 900 };
        Monopoly.plateau.ajouter(new Terrain("Rue de Paradis", 160, c6, Groupe.VIOLET, 100));
        Maillon<Case> lyon = Monopoly.plateau.ajouter(new Gare("de Lyon"));
        int[] c7 = { 14, 70, 200, 550, 750, 900 };
        Monopoly.plateau.ajouter(new Terrain("Avenue Mozart", 180, c7, Groupe.ORANGE, 100));
        Monopoly.plateau.ajouter(new CaisseDeLaCommunaute());
        Monopoly.plateau.ajouter(new Terrain("Boulevard Saint-Michel", 180, c7, Groupe.ORANGE, 100));
        int[] c8 = { 16, 80, 220, 600, 800, 1000 };
        Monopoly.plateau.ajouter(new Terrain("Place Pigale", 200, c8, Groupe.ORANGE, 100));
        Monopoly.plateau.ajouter(new Case("Parc Gratuit"));
        int[] c9 = { 18, 90, 250, 700, 875, 1050 };
        Monopoly.plateau.ajouter(new Terrain("Avenue Matignon", 220, c9, Groupe.ROUGE, 150));
        Monopoly.plateau.ajouter(new Chance());
        Monopoly.plateau.ajouter(new Terrain("Boulevard Malesherbes", 220, c9, Groupe.ROUGE, 150));
        int[] c10 = { 20, 100, 300, 750, 925, 1100 };
        Maillon<Case> henriMartin = Monopoly.plateau.ajouter(new Terrain("Avenue Henri-Martin", 240, c10, Groupe.ROUGE, 150));
        Monopoly.plateau.ajouter(new Gare("du Nord"));
        int[] c11 = { 22, 110, 330, 800, 975, 1150 };
        Monopoly.plateau.ajouter(new Terrain("Faubourg Saint-Honoré", 260, c11, Groupe.JAUNE, 150));
        Monopoly.plateau.ajouter(new Terrain("Place de la Bourse", 260, c11, Groupe.JAUNE, 150));
        Monopoly.plateau.ajouter(new ServicePublic("Compagnie de distribution des eaux"));
        int[] c12 = { 24, 120, 360, 850, 1025, 1200 };
        Monopoly.plateau.ajouter(new Terrain("Rue La Fayette", 280, c12, Groupe.JAUNE, 150));
        Monopoly.plateau.ajouter(new AllezEnPrison());
        int[] c13 = { 26, 130, 390, 900, 1100, 1275 };
        Monopoly.plateau.ajouter(new Terrain("Avenue de Breuteuil", 300, c13, Groupe.VERT, 200));
        Monopoly.plateau.ajouter(new Terrain("Avenue Foch", 300, c13, Groupe.VERT, 200));
        Monopoly.plateau.ajouter(new CaisseDeLaCommunaute());
        int[] c14 = { 28, 150, 390, 900, 1200, 1400 };
        Monopoly.plateau.ajouter(new Terrain("Boulevard des Capucines", 320, c14, Groupe.VERT, 200));
        Monopoly.plateau.ajouter(new Gare("Saint-Lazare"));
        Monopoly.plateau.ajouter(new Chance());
        int[] c15 = { 35, 175, 500, 1200, 1500, 1700 };
        Monopoly.plateau.ajouter(new Terrain("Avenue des Champs Élysées", 350, c15, Groupe.MARINE, 200));
        Monopoly.plateau.ajouter(new ImpotTaxe("Taxe de Luxe", 100));
        int[] c16 = { 50, 200, 600, 1400, 1700, 2000 };
        Maillon<Case> rueDeLaPaix = Monopoly.plateau.ajouter(new Terrain("Rue de la Paix", 400, c16, Groupe.MARINE, 200));
        Maillon<Case> depart = Monopoly.plateau.ajouter(new Depart());

        // La prison a pour case suivante le boulevard de la villette
        Monopoly.prison = new Maillon<Case>(new Prison(), laVillette);

        /////////////////////////// Création des joueurs
        /////////////////////////// ///////////////////////////
        int nbJoueurs = Outils.saisie("Combien de joueurs ?", 2, 10);
        List<Joueur> concurents = new ArrayList<>();
        for (int i = 0; i < nbJoueurs; i++) {
            String nom = Outils.saisie(String.format("Entrez le nom du joueur n°%d", i + 1));
            if (nom.isEmpty())
                nom = "Joueur " + (i + 1);
            concurents.add(new Joueur(nom, Monopoly.plateau.getEntree()));
        }
        // l'ordre des joueurs est tiré aléatoirement
        while (!concurents.isEmpty()) {
            Monopoly.joueurs.ajouter(concurents.remove(Outils.nombreAlea(concurents.size())));
        }
        System.out.println("Les joueurs jouront dans cet ordre : " + Monopoly.joueurs);


        /////////////////////////// création de la pioche pour les cartes
        /////////////////////////// "caisse de la communauté"
        /////////////////////////// ///////////////////////////
        List<Action> liste = new ArrayList<>();
        liste.add(new ArgentAction("Erreur de la banque en votre faveur. Recevez", 200));
        liste.add(new ArgentAction("Recevez votre intérêt sur l'emprunt à 7%.", 25));
        liste.add(new ArgentAction("Payez votre police d'assurance s'élevant à", -50));
        liste.add(new ArgentAction("Vous héritez", 100));
        liste.add(new ArgentAction("Les contributions vous remboursent la somme de", 20));
        liste.add(new ArgentAction("Payez la note du médecin", -50));
        liste.add(new ArgentAction("Payez à l'hôpital", -100));
        liste.add(new ArgentAction("Vous avez gagné le deuxième prix de beauté. Recevez", 10));
        liste.add(new ArgentAction("La vente de votre stock vous rapporte", 50));
        liste.add(new ArgentAction("Recevez votre revenu annuel", 100));
        liste.add(Monopoly.libereCaisse);
        liste.add(new AllezEnPrisonAction());
        liste.add(new DeplacementAbsoluAction("Avancer jusqu'à la case Départ", depart, true));
        liste.add(new DeplacementAbsoluAction("Retournez à Belleville", belleville, true));
        liste.add(new ArgentOuChanceAction("Payez une amende de", -10));
        liste.add(new TousLesJoueursArgentAction("C'est votre anniversaire : chaque joueur doit vous donner", 10));
        while (!liste.isEmpty()) {
            CaisseDeLaCommunaute.getCartes().ajouter(liste.remove(Outils.nombreAlea(liste.size())));
        }
        /////////////////////////// création de la pioche pour les cartes
        /////////////////////////// "chance" ///////////////////////////
        liste.add(new ArgentAction("Votre immeuble et votre prêt rapportent. Vous devez toucher", 150));
        liste.add(new ArgentAction("Vous avez gagné le prix de mots croisés", 100));
        liste.add(new ArgentAction("La banque vous verse un dividende de", 50));
        liste.add(new ArgentAction("Amende pour excès de vitesse :", -15));
        liste.add(new ArgentAction("Amende pour ivresse :", -20));
        liste.add(new ArgentAction("Payez pour frais de scolarité", -150));
        liste.add(Monopoly.libereChance);
        liste.add(new AllezEnPrison());
        liste.add(new DeplacementAbsoluAction("Avancer jusqu'à la case Départ", depart, true));
        liste.add(new DeplacementAbsoluAction("Rendez-vous à la Rue de la Paix", rueDeLaPaix, false));
        liste.add(new DeplacementAbsoluAction("Rendez-vous à l'Avenue Henri-Martin. Si vous passez par la case Départ recevez 200€", henriMartin, false));
        liste.add(new DeplacementAbsoluAction("Avancez au Boulevard de la Villette. Si vous passez par la case Départ recevez 200€", laVillette, false));
        liste.add(new DeplacementAbsoluAction("Allez à la gare de Lyon. Si vous passez par la case Départ recevez 200€", lyon, false));
        liste.add(new DeplacementRelatifAction("Reculez de trois cases", -3));
        liste.add(new EntretienAction("Vous êtes imposé pour les réparations de voirie à raison de %d€ par maison et %d€ par hôtel.", 40, 115));
        liste.add(new EntretienAction("Faites des réparations dans toutes vos maisons. Versez pour chaque maison %d€. Versez pour chaque hôtel %d€.", 25, 100));
        while (!liste.isEmpty()) {
            Chance.getCartes().ajouter(liste.remove(Outils.nombreAlea(liste.size())));
        }
    }

    /**
     * @param j
     */
    public static void utiliserLibereDePrison(Joueur j) {
        if(j.equals(Monopoly.getLibereCaisse().getProprio())) {
            Monopoly.getLibereCaisse().setProprio(null);
            CaisseDeLaCommunaute.remettreEnJeu(Monopoly.getLibereCaisse());
        } else {
            Monopoly.getLibereChance().setProprio(null);
            Chance.remettreEnJeu(Monopoly.getLibereChance());
        }
    }
}