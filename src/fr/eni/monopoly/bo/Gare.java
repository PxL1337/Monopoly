package fr.eni.monopoly.bo;

public class Gare extends Propriete{
    public Gare(String nom) {
        super(nom);
    }

    @Override
    protected void payerLoyer(Joueur passager, Joueur p){
        int loyer = this.txComplGroupe;
        if (loyer == 75)
            loyer = 100;
        else if (loyer == 100)
            loyer = 200;
        System.out.printf("%s possède %d gare%s%n", p, this.txComplGroupe*4/100, loyer>25?"s":"");
        passager.payeA(p, loyer);
    }
}
