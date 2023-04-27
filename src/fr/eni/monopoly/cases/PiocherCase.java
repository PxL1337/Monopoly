package fr.eni.monopoly.cases;

import fr.eni.monopoly.AllerEnPrisonException;
import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;

/**
 * The type Piocher case.
 */
public abstract class PiocherCase extends Case{
    /**
     * Instantiates a new Piocher case.
     *
     * @param nom the nom
     */
    public PiocherCase(String nom) {
        super(nom);
    }
    
    public void joueurArrive(Joueur j) throws FailliteException, AllerEnPrisonException {
        super.joueurArrive(j);
        this.tirerCarte(j);
    }

    protected abstract void tirerCarte(Joueur j) throws FailliteException, AllerEnPrisonException;
}
