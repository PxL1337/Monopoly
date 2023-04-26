package fr.eni.monopoly.cases;

import java.util.Hashtable;
import java.util.Map;

import fr.eni.monopoly.FailliteException;
import fr.eni.monopoly.Joueur;
import fr.eni.monopoly.Monopoly;
import fr.eni.util.Outils;

public class Prison extends Case{
    private Map<Joueur,Integer> nbTentatives = new Hashtable<>();

    public Prison() {
        super("Prison");
    }

    @Override
    public boolean joueurPart(Joueur j) throws FailliteException {
        super.joueurPart(j);
        boolean part = false;
        if(Monopoly.getDe1().gestFaceTiree() == Monopoly.getDe2().getFaceTiree()) {
            part = true;
            System.out.printf("Un double fait sortir %s gratuitement de prison%n", j);
        } else {
            int nb = 1;
            if (this.nbTentatives.containsKey(j))
                nb = this.nbTentatives.get(j) + 1;
            this.nbTentatives.put(j, nb);
            boolean carteLibere = j.possedeCarteLiberePrison() && Outils.saisieOuiNon("Voulez-vous utiliser votre carte de libération de prison ?");
            if (carteLibere) {
                Monopoly.utiliserLiberePrison();
                part = true;
            }
            boolean paye = !carteLibere && (nb >= 3 || Outils.saisieOuiNon("Voulez-vous payer 50€ pour sortir de prison ?"));
            if (paye) {
                System.out.printf("%s paye 50€ pour sortir de prison%n", j);
                j.debiter(50);
                part = true;
            }
        }
            if (part)
                this.nbTentatives.remove(j);
            return part;
    }
}
