package client;

import modeles.Carte;
import modeles.Joueur;
import modeles.Partie;
import modeles.Plateau;
import modeles.exceptions.*;
import modeles.facade.FacadeWondersImpl;
import modeles.interfaces.FacadeWonders;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ServiceWondersImpl extends UnicastRemoteObject implements ServiceWonders {
    public FacadeWonders facadeWonders;


    public ServiceWondersImpl() throws RemoteException {
        super();
        this.facadeWonders=new FacadeWondersImpl();
    }

    public static ServiceWonders creer() throws RemoteException {
        return new ServiceWondersImpl();
    }


    @Override
    public synchronized void ajoutJoueur(Joueur joueur) throws RemoteException {
        this.facadeWonders.ajoutJoueur(joueur);
    }

    @Override
    public synchronized String creerPartie(Joueur joueur, int nombreJoueur) throws RemoteException {
        return this.facadeWonders.creerPartie(joueur,nombreJoueur);
    }

    @Override
    public synchronized boolean choixPlateauFait(String pseudo) throws RemoteException {
        return this.facadeWonders.choixPlateauFait(pseudo);
    }

    @Override
    public List<Carte> donnerCarteJoueur(Joueur joueur) throws RemoteException {
        this.facadeWonders.donnerCarteJoueur(joueur);
        return null;
    }

    @Override
    public synchronized Partie getPartieJeu(String pseudo) throws RemoteException {
        return this.facadeWonders.getPartieJeu(pseudo);

    }

    @Override
    public synchronized Joueur getJoueur(String pseudo) throws JoueurInexistantException, RemoteException {
        return this.facadeWonders.getJoueur(pseudo);
    }

    @Override
    public synchronized void rejoindrePartie(Joueur joueur, String ticket) throws TicketPerimeException, TicketInvalideException, PartieDejaPleineException, RemoteException {
        this.facadeWonders.rejoindrePartie(joueur, ticket);
    }

    @Override
    public synchronized void jouer(Joueur joueur, String choixAction, String nomCarte, String choixCarte) throws RemoteException, ChoixDejaFaitException, CiteContientCarteException, ConstructionMerveilleImpossible, PieceInsuffisanteException, ChoixIncompletsException, RessourceVoisinInsuffisantException, CartePasConstruiteException, RessourceInsuffisanteException, ConstructionImpossibleException, RessourceInexistanteException {
        this.facadeWonders.jouer(joueur, choixAction, nomCarte, choixCarte);
    }

    @Override
    public synchronized void arreterPartie(Joueur joueur) throws RemoteException {
        this.facadeWonders.arreterPartie(joueur);
    }

    @Override
    public synchronized void reprendrePartie(Joueur joueur) throws RemoteException {
        this.facadeWonders.reprendrePartie(joueur);
    }

    @Override
    public Plateau getPlateau(String pseudo) throws RemoteException {
        return this.facadeWonders.getPlateau(pseudo);
    }


    @Override
    public synchronized boolean partieTerminee(Joueur joueur) throws RemoteException {
        return this.facadeWonders.partieTerminee(joueur);
    }

    @Override
    public synchronized void debutJeu(Joueur joueur, String nomPlateau) throws RemoteException{
        this.facadeWonders.debutJeu(joueur, nomPlateau);
    }

    @Override
    public synchronized String vainqueur(Joueur joueur) throws PartieNonTermineeException, RemoteException {
        return this.facadeWonders.vainqueur(joueur);
    }

    @Override
    public synchronized boolean partieCommencee(Joueur joueur) throws RemoteException {
        return this.facadeWonders.partieCommencee(joueur);
    }

    @Override
    public synchronized void finDePartie() throws RemoteException {
        this.facadeWonders.finDePartie();
    }

    @Override
    public List<Carte> getCartesMainJoueur(String pseudo) throws RemoteException{
        return this.facadeWonders.getCartesMainJoueur(pseudo);
    }
}
