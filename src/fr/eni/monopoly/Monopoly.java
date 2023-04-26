package fr.eni.monopoly;

public class Monopoly {
    public static void main(String[] args) {

    }

    private static void jouer() throws AllerEnPrisonException {



        try {
            if (Monopoly.de1.lancer() == Monopoly.de2.lancer()) {
                nbDoubles++;
                if (nbDoubles == 3) {
                    System.out.printf("%s a fait %d et %d aux dés%n", joueurCourant.get(), Monopoly.de1.getFaceTiree(), Monopoly.de2.getFaceTiree());
                    throw new AllerEnPrisonException("3 doubles consécutifs");
                }
            }
            joueurCourant.get().jouer(Monopoly.de1.getFaceTiree(), Monopoly.de2.getFaceTiree());
        } catch (FailliteException e){
            Monopoly.joueurs.retirer(e.getJoueur());
            System.err.println(e.getMessage());
        }

    }
}
