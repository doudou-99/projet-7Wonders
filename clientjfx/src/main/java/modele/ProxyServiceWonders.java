package modele;

import modeles.Carte;
import modeles.Joueur;
import modeles.Partie;
import modeles.Plateau;
import modeles.exceptions.*;

import java.rmi.RemoteException;
import java.util.List;

public interface ProxyServiceWonders {
    void ajoutJoueur(Joueur joueur);
    boolean choixPlateauFait(String pseudo);
    String creerPartie(Joueur joueur,int nombreJoueur);

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
    Plateau getPlateau(String pseudo);
    List<Carte> donnerCarteJoueur(Joueur joueur);
    List<Carte> getCartesMainJoueur(String pseudo);
}
