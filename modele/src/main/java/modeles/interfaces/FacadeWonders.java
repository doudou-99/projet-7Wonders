package modeles.interfaces;

import modeles.*;
import modeles.exceptions.*;

import java.util.List;

public interface FacadeWonders {

    void ajoutJoueur(Joueur joueur);
    boolean choixPlateauFait(String pseudo);
    String creerPartie(Joueur joueur);

    Partie getPartieJeu(String pseudo);

    Joueur getJoueur(String pseudo) throws JoueurInexistantException;

    void rejoindrePartie(Joueur joueur, String ticket) throws TicketPerimeException, TicketInvalideException, PartieDejaPleineException;
    void jouer(Joueur joueur, String choixAction, String nomCarte, String choixCarte) throws ChoixIncompletsException, ChoixDejaFaitException, ConstructionMerveilleImpossible, PieceInsuffisanteException, RessourceInexistanteException, RessourceVoisinInsuffisantException, CartePasConstruiteException, RessourceInsuffisanteException, ConstructionImpossibleException, CiteContientCarteException;

    void arreterPartie(Joueur joueur);
    void reprendrePartie(Joueur joueur);

    void debutJeu(Joueur joueur,String nomPlateau);

    boolean partieTerminee(Joueur joueur);

    String vainqueur(Joueur joueur) throws PartieNonTermineeException;

    boolean partieCommencee(Joueur joueur);
    void finDePartie();

}
