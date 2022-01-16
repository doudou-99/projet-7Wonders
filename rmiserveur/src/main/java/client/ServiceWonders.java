package client;

import modeles.Carte;
import modeles.Joueur;
import modeles.Partie;
import modeles.Plateau;
import modeles.exceptions.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServiceWonders extends Remote {
    String SERVEUR="registre";
    void ajoutJoueur(Joueur joueur) throws RemoteException;
    String creerPartie(Joueur joueur, int nombreJoueur) throws RemoteException;
    boolean choixPlateauFait(String pseudo) throws RemoteException;
    List<Carte> donnerCarteJoueur(Joueur joueur) throws RemoteException;
    Partie getPartieJeu(String pseudo) throws RemoteException;

    Joueur getJoueur(String pseudo) throws JoueurInexistantException,RemoteException;

    void rejoindrePartie(Joueur joueur, String ticket) throws TicketPerimeException, TicketInvalideException, PartieDejaPleineException,RemoteException;
    void jouer(Joueur joueur, String choixAction, String nomCarte, String choixCarte) throws RemoteException, ChoixIncompletsException, ChoixDejaFaitException, ConstructionMerveilleImpossible, PieceInsuffisanteException, RessourceInexistanteException, RessourceVoisinInsuffisantException, CartePasConstruiteException, RessourceInsuffisanteException, ConstructionImpossibleException, CiteContientCarteException;

    void arreterPartie(Joueur joueur) throws RemoteException;
    void reprendrePartie(Joueur joueur) throws RemoteException;
    Plateau getPlateau(String pseudo) throws RemoteException;
    List<Carte> getCartesMainJoueur(String pseudo) throws RemoteException;



    boolean partieTerminee(Joueur joueur) throws RemoteException;

    void debutJeu(Joueur joueur,String nomPlateau) throws RemoteException;

    String vainqueur(Joueur joueur) throws PartieNonTermineeException,RemoteException;

    boolean partieCommencee(Joueur joueur) throws RemoteException;
    void finDePartie() throws RemoteException;
}
