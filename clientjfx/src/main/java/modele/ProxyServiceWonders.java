package modele;

import modeles.Joueur;
import modeles.Partie;
import modeles.exceptions.*;

import java.rmi.RemoteException;

public interface ProxyServiceWonders {
    void ajoutJoueur(Joueur joueur);
    String creerPartie(Joueur joueur);

    Partie getPartieJeu(String pseudo);

    Joueur getJoueur(String pseudo) throws JoueurInexistantException;

    void rejoindrePartie(Joueur joueur, String ticket) throws TicketPerimeException, TicketInvalideException, PartieDejaPleineException;
    void jouer(Joueur joueur, String choixAction, String nomCarte, String choixCarte) throws ChoixIncompletsException, ChoixDejaFaitException, ConstructionMerveilleImpossible, PieceInsuffisanteException, RessourceInexistanteException, RessourceVoisinInsuffisantException, CartePasConstruiteException, RessourceInsuffisanteException, ConstructionImpossibleException, CiteContientCarteException, RemoteException;

    void arreterPartie(Joueur joueur);
    void reprendrePartie(Joueur joueur);
    void debutJeu(Joueur joueur,String nomPlateau);

    boolean partieTerminee(Joueur joueur);

    String vainqueur(Joueur joueur) throws PartieNonTermineeException;

    boolean partieCommencee(Joueur joueur);
    void finDePartie();
}
