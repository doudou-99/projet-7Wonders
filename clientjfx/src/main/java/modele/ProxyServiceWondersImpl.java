package modele;

import client.ServiceWonders;
import modeles.Carte;
import modeles.Joueur;
import modeles.Partie;
import modeles.Plateau;
import modeles.exceptions.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import static client.ServiceWonders.SERVEUR;

public class ProxyServiceWondersImpl implements ProxyServiceWonders{
    public ServiceWonders serviceWonders;

    public ProxyServiceWondersImpl(){
        System.out.println("Lancement du client");
        try {
            this.serviceWonders =(ServiceWonders) Naming.lookup(SERVEUR);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        System.out.println("Client initialisé");
    }

    @Override
    public void ajoutJoueur(Joueur joueur) {
        try {
            this.serviceWonders.ajoutJoueur(joueur);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean choixPlateauFait(String pseudo) {
        try {
            return this.serviceWonders.choixPlateauFait(pseudo);
        } catch (RemoteException e) {
            throw new RuntimeException(e.detail);
        }
    }

    @Override
    public String creerPartie(Joueur joueur, int nombreJoueur) {
        try {

            return this.serviceWonders.creerPartie(joueur,nombreJoueur);
        } catch (RemoteException e) {
            throw new RuntimeException(e.detail);
        }
    }

    @Override
    public Partie getPartieJeu(String pseudo) {
        try {
            return this.serviceWonders.getPartieJeu(pseudo);
        } catch (RemoteException e) {
            throw new RuntimeException("Probleme RMI: partie");
        }
    }

    @Override
    public Joueur getJoueur(String pseudo) throws JoueurInexistantException {
        try {
            return this.serviceWonders.getJoueur(pseudo);
        } catch (RemoteException e) {
            throw new RuntimeException("Probleme RMI: joueur");
        }
    }

    @Override
    public void rejoindrePartie(Joueur joueur, String ticket) throws TicketPerimeException, TicketInvalideException, PartieDejaPleineException {
        try {
            this.serviceWonders.rejoindrePartie(joueur, ticket);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jouer(Joueur joueur, String choixAction, String nomCarte, String choixCarte) throws RessourceVoisinInsuffisantException, ChoixDejaFaitException, CiteContientCarteException, ConstructionMerveilleImpossible, PieceInsuffisanteException, ChoixIncompletsException, RemoteException, CartePasConstruiteException, RessourceInsuffisanteException, ConstructionImpossibleException, RessourceInexistanteException {
        try {
            this.serviceWonders.jouer(joueur, choixAction, nomCarte, choixCarte);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void arreterPartie(Joueur joueur) {
        try {
            this.serviceWonders.arreterPartie(joueur);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reprendrePartie(Joueur joueur) {
        try {
            this.serviceWonders.reprendrePartie(joueur);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void debutJeu(Joueur joueur, String nomPlateau) {
        try {
            this.serviceWonders.debutJeu(joueur, nomPlateau);
        } catch (RemoteException e) {
            throw new RuntimeException(e.detail);
        }
    }

    @Override
    public boolean partieTerminee(Joueur joueur) {
        try {
            return this.serviceWonders.partieTerminee(joueur);
        } catch (RemoteException e) {
            throw new RuntimeException("Probleme RMI: partie terminée");
        }
    }

    @Override
    public String vainqueur(Joueur joueur) throws PartieNonTermineeException {
        try {
            return this.serviceWonders.vainqueur(joueur);
        } catch (RemoteException e) {
            throw new RuntimeException("Probleme RMI: vainqueur partie");
        }
    }

    @Override
    public boolean partieCommencee(Joueur joueur) {
        try {
            return this.serviceWonders.partieCommencee(joueur);
        } catch (RemoteException e) {
            throw new RuntimeException("Probleme RMI: démarrer partie");
        }
    }

    @Override
    public void finDePartie() {
        try {
            this.serviceWonders.finDePartie();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Plateau getPlateau(String pseudo) {
        try {
            return this.serviceWonders.getPlateau(pseudo);
        } catch (RemoteException e) {
            throw new RuntimeException(e.detail);
        }
    }

    @Override
    public List<Carte> donnerCarteJoueur(Joueur joueur) {
        try {
            return this.serviceWonders.donnerCarteJoueur(joueur);
        } catch (RemoteException e) {
            throw new RuntimeException(e.detail);
        }
    }
    @Override
    public List<Carte> getCartesMainJoueur(String pseudo){
        try {
            return this.serviceWonders.getCartesMainJoueur(pseudo);
        } catch (RemoteException e) {
            throw new RuntimeException(e.detail);
        }
    }


}
