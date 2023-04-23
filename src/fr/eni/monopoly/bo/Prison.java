package fr.eni.monopoly.bo;

import java.util.Map;

public class Prison extends Case{
    private Map<Joueur,Integer> nbTentatives;

    public Prison() {
        super("Prison");
    }

    public boolean joueurPart(Joueur j) {
        if (nbTentatives.get(j) == 3) {
            return true;
        }
        return false;
    }
}
