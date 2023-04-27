package fr.eni.monopoly.cases;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.monopoly.Pioche;
import fr.eni.monopoly.actions.Action;

/**
 * The type Caisse de la communaute.
 */
public class CaisseDeLaCommunaute extends PiocherCase {
    private static Pioche cartes = new Pioche();

    /**
     * Instantiates a new Caisse de la communaute.
     */
    public CaisseDeLaCommunaute() {
        super("Caisse de la communauté");
    }

    /**
     * Instantiates a new Tirer carte.
     *
     * @param j the j
     * @throws FailliteException      the faillite exception
     * @throws AllerEnPrisonException the aller en prison exception
     */
    protected tirerCarte(Joueur j) throws FailliteException, AllerEnPrisonException {
        Action a = CaisseDeLaCommunaute.cartes.tirer();
        a.realiser(j);
    }

    /**
     * Gets cartes.
     *
     * @return the cartes
     */
    public static Pioche getCartes() {
        return CaisseDeLaCommunaute.cartes;
    }

    /**
     * Instantiates a new Remettre en jeu.
     *
     * @param carte the carte
     */
    public static void remettreEnJeu(Action carte) {
        CaisseDeLaCommunaute.cartes.ajouter(carte);
    }
}
