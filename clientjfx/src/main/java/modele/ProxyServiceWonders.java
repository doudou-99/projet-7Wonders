package modele;

import modeles.Joueur;
import modeles.Partie;
import modeles.exceptions.*;

public interface ProxyServiceWonders {
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
