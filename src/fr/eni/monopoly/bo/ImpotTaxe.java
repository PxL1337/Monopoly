package fr.eni.monopoly.bo;

public class ImpotTaxe extends Case {
    private int prix;

    public ImpotTaxe(String nom, int prix) {
        super(nom);
        this.prix = prix;
    }

    @Override
    public joueurArrive(Joueur j) {
        j.setArgent(j.getArgent() - prix);
    }
}
