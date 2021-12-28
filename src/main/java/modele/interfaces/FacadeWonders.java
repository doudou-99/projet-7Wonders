package modele.interfaces;

import modele.*;

import java.util.List;

public interface FacadeWonders {

    String creerPartie(Joueur joueur);
    void rejoindrePartie(Joueur joueur,String ticket);
    Carte jouerCarte(Joueur joueur, Carte carte);
    void arreterPartie(Joueur joueur);
    void reprendrePartie(Joueur joueur);
    void sauvegarderPartie(Joueur joueur);
    boolean partieTerminee(Joueur joueur);
    Joueur getVainqueur(Joueur joueur);
    boolean partieCommencee(Joueur joueur);
    GestionCapacite getCapacites(Joueur joueur);
    List<Piece> getPieces(Joueur joueur);
    Score getScoreCourant(Joueur joueur);
    void finDePartie(Joueur joueur);

}
