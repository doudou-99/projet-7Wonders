package modele.interfaces;

import modele.*;
import modele.exceptions.PartieDejaPleineException;
import modele.exceptions.TicketInvalideException;
import modele.exceptions.TicketPerimeException;

import java.util.List;

public interface FacadeWonders {

    void ajoutJoueur(Joueur joueur);
    String creerPartie(Joueur joueur);

    Joueur getJoueur(String pseudo) throws JoueurInexistantException;

    void rejoindrePartie(Joueur joueur, String ticket) throws TicketPerimeException, TicketInvalideException, PartieDejaPleineException;
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
