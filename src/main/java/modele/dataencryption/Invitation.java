package modele.dataencryption;

import modele.Joueur;

public class Invitation {



    private int idPartie; // Identifiant de la partie concernée par l'invitation
    private Joueur joueurCreateur; // Créateur de la partie concernée par l'invitation

    public int getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    public Joueur getJoueurCreateur() {
        return joueurCreateur;
    }

    public void setJoueurCreateur(Joueur joueurCreateur) {
        this.joueurCreateur = joueurCreateur;
    }
}
