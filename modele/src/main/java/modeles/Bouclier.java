package modeles;

public class Bouclier {
    private Joueur joueur;
    private int nombreBouclier=0;

    public Bouclier(Joueur joueur, int nombreBouclier) {
        this.joueur = joueur;
        this.nombreBouclier = nombreBouclier;
    }

    public int getNombreBouclier() {
        return nombreBouclier;
    }

    public Joueur getJoueur() {
        return joueur;
    }
}
