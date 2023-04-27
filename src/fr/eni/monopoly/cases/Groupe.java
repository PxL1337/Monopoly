package fr.eni.monopoly.cases;

import java.util.ArrayList;
import java.util.List;

/**
 * The enum Groupe.
 */
public enum Groupe {
    GARE("groupe des gares"),
    SERVICE_PUBLIC("groupe des services publics"),
    MAUVE,
    BLEU,
    VIOLET,
    ORANGE,
    ROUGE,
    JAUNE,
    VERT,
    MARINE("groupe bleu marine");
    private List<Propriete> proprietes = new ArrayList<>();
    private String nom;

    private Groupe() {
        this.nom = "Groupe " + this.name().toLowerCase();
    }

    private Groupe(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    void ajouterPropriete(Propriete p) {
        assert this.equals(p.getGroupe());
        this.proprietes.add(p);
    }

    public Iterable<Propriete> getProprietes() {
        return this.proprietes;
    }

    public int getNbProprietes() {
        return this.proprietes.size();
    }
}
