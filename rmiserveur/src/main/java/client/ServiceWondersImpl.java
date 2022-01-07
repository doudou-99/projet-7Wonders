package client;

import modeles.Joueur;
import modeles.Partie;
import modeles.exceptions.*;
import modeles.facade.FacadeWondersImpl;
import modeles.interfaces.FacadeWonders;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
    public void ajoutJoueur(Joueur joueur) throws RemoteException {
        this.facadeWonders.ajoutJoueur(joueur);
    }

    @Override
    public String creerPartie(Joueur joueur) throws RemoteException {
        return this.facadeWonders.creerPartie(joueur);
    }

    @Override
    public Partie getPartieJeu(String pseudo) throws RemoteException {
        return this.facadeWonders.getPartieJeu(pseudo);
    }

    @Override
    public Joueur getJoueur(String pseudo) throws JoueurInexistantException, RemoteException {
        return this.facadeWonders.getJoueur(pseudo);
    }

    @Override
    public void rejoindrePartie(Joueur joueur, String ticket) throws TicketPerimeException, TicketInvalideException, PartieDejaPleineException, RemoteException { ;
        this.facadeWonders.rejoindrePartie(joueur,ticket);
    }

    @Override
    public void jouer(Joueur joueur, String choixAction, String nomCarte, String choixCarte) throws RemoteException {
        this.facadeWonders.jouer(joueur, choixAction, nomCarte, choixCarte);
    }

    @Override
    public void arreterPartie(Joueur joueur) throws RemoteException {
        this.facadeWonders.arreterPartie(joueur);
    }

    @Override
    public void reprendrePartie(Joueur joueur) throws RemoteException {
        this.facadeWonders.reprendrePartie(joueur);
    }

    @Override
    public boolean partieTerminee(Joueur joueur) throws RemoteException {
        return this.facadeWonders.partieTerminee(joueur);
    }

    @Override
    public String vainqueur(Joueur joueur) throws PartieNonTermineeException, RemoteException {
        return this.facadeWonders.vainqueur(joueur);
    }

    @Override
    public boolean partieCommencee(Joueur joueur) throws RemoteException {
        return this.facadeWonders.partieCommencee(joueur);
    }

    @Override
    public void finDePartie() throws RemoteException {
        this.facadeWonders.finDePartie();
    }
}
