package fr.eni.monopoly.bo;

public class ServicePublic extends Propriete{
    public ServicePublic(String nom) {
        super(nom);
    }

    @Override
    protected void payerLoyer(Joueur utilisateur, Joueur proprietaire){
        int nb = this.txComplGroupe*2/100;
        String s = nb<2?"":"s"
        int loyer = Monopoly.getDe1().getFaceTiree() + Monopoly.getDe2().getFaceTiree();
        if (nb == 1) {
            loyer *= 4;
        } else {
            loyer *= 10;
            System.out.printf("%s possède %d service%s%n", proprietaire, nb, s, s);
            utilisateur.payeA(proprietaire, loyer);
        }
    }
}
