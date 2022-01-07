package modeles.interfaces;

import modeles.*;
import modeles.exceptions.*;

import java.util.List;

public interface FacadeWonders {

    void ajoutJoueur(Joueur joueur);
    String creerPartie(Joueur joueur);

    Partie getPartieJeu(String pseudo);

    Joueur getJoueur(String pseudo) throws JoueurInexistantException;

    void rejoindrePartie(Joueur joueur, String ticket) throws TicketPerimeException, TicketInvalideException, PartieDejaPleineException;
    void jouer(Joueur joueur, String choixAction, String nomCarte, String choixCarte);

    void arreterPartie(Joueur joueur);
    void reprendrePartie(Joueur joueur);



    boolean partieTerminee(Joueur joueur);

    String vainqueur(Joueur joueur) throws PartieNonTermineeException;

    boolean partieCommencee(Joueur joueur);
    void finDePartie();

}
