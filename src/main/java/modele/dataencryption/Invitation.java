package modele.dataencryption;

import modele.Joueur;

public class Invitation {



    private String idPartie; // Identifiant de la partie concernée par l'invitation
    private Joueur joueurCreateur; // Créateur de la partie concernée par l'invitation
    private final int nbJoueurs=4;

    public String getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(String idPartie) {
        this.idPartie = idPartie;
    }

    public Joueur getJoueurCreateur() {
        return joueurCreateur;
    }

    public void setJoueurCreateur(Joueur joueurCreateur) {
        this.joueurCreateur = joueurCreateur;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }
}
