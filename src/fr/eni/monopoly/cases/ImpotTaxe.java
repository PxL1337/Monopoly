package fr.eni.monopoly.cases;

import fr.eni.monopoly.Joueur;

public class ImpotTaxe extends Case {
    private int prix;

    public ImpotTaxe(String nom, int prix) {
        super(nom);
        this.prix = prix;
    }

    @Override
    public void joueurArrive(Joueur j) {
        super.joueurArrive(j);
        System.out.printf("%s paye %d€ à la banque%n", j, this.prix);
        j.debiter(this.prix);
    }
}
