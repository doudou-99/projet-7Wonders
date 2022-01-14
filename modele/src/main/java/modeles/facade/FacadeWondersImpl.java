package modeles.facade;

import modeles.*;
import modeles.dao.BaseMongo;
import modeles.dataencryption.DataChiffrement;
import modeles.dataencryption.Invitation;
import modeles.exceptions.*;
import modeles.interfaces.FacadeWonders;
import modeles.interfaces.Score;

import java.util.*;

public class FacadeWondersImpl implements FacadeWonders {
    private Map<String, Partie> parties;
    private Map<String,Partie> associationJoueurPartie;
    DataChiffrement dataChiffrement;

    public FacadeWondersImpl(){
        this.parties=new HashMap<>();
        this.associationJoueurPartie=new HashMap<>();
        this.dataChiffrement=new DataChiffrement("jouerensemble");
    }

    @Override
    public void ajoutJoueur(Joueur joueur){
        BaseMongo.getBase().getJoueurList().add(joueur);
        BaseMongo.getBase().getJoueurs().insertOne(joueur);
    }

    @Override
    public boolean choixPlateauFait(String pseudo) {
        return this.getPartieJeu(pseudo).choixPlateauFait(pseudo);
    }


    @Override
    public String creerPartie(Joueur joueur, int nombreJoueur) {
        Partie partie = new Partie(joueur,nombreJoueur);
        String id = partie.getIdPartie();
        this.associationJoueurPartie.put(joueur.getPseudo(),partie);
        this.parties.put(id,partie);
        this.ajoutJoueur(joueur);

        Invitation data = new Invitation();
        data.setIdPartie(id);
        data.setJoueurCreateur(joueur);
        partie.getParticipants().add(joueur);
        BaseMongo.getBase().getJeu().insertOne(partie);
        return this.dataChiffrement.chiffrement(data);

    }



    @Override
    public Joueur getJoueur(String pseudo) throws JoueurInexistantException {
        for (Joueur j: BaseMongo.getBase().getJoueurList()){
            if (j.getPseudo().equals(pseudo)){
                return j;
            }
        }
        throw new JoueurInexistantException();

    }

    @Override
    public Partie getPartieJeu(String pseudo) {
        return associationJoueurPartie.get(pseudo);
    }

    @Override
    public void rejoindrePartie(Joueur joueur, String ticket) throws TicketPerimeException, TicketInvalideException, PartieDejaPleineException {
        Invitation invitation = this.dataChiffrement.dechiffrement(ticket);
        Partie partie = this.parties.get(invitation.getIdPartie());
        if (Objects.isNull(partie)) {
            throw new TicketPerimeException();
        }
        partie.rejoindrePartie(joueur);
        this.associationJoueurPartie.put(joueur.getPseudo(),this.parties.get(invitation.getIdPartie()));

    }


    @Override
    public void jouer(Joueur joueur, String choixAction, String nomCarte, String choixCarte) throws ChoixIncompletsException, ChoixDejaFaitException, ConstructionMerveilleImpossible, PieceInsuffisanteException, RessourceInexistanteException, RessourceVoisinInsuffisantException, CartePasConstruiteException, RessourceInsuffisanteException, ConstructionImpossibleException, CiteContientCarteException {
        Age age=this.getPartieJeu(joueur.getPseudo()).getPartieGestionCourant().getGestiontour().getTour().getAge();
        this.getPartieJeu(joueur.getPseudo()).choixJoueur(joueur.getPseudo(),choixAction,nomCarte,choixCarte,age);
    }

    @Override
    public void arreterPartie(Joueur joueur) {
        this.getPartieJeu(joueur.getPseudo()).arreterPartie();
    }

    @Override
    public void reprendrePartie(Joueur joueur) {
        this.getPartieJeu(joueur.getPseudo()).reprendrePartie();
    }

    @Override
    public void debutJeu(Joueur joueur, String nomPlateau) {
        this.getPartieJeu(joueur.getPseudo()).debutJeu(joueur,nomPlateau);
    }


    @Override
    public boolean partieTerminee(Joueur joueur) {
        return this.getPartieJeu(joueur.getPseudo()).partieTerminee();
    }



    @Override
    public String vainqueur(Joueur joueur) throws PartieNonTermineeException {
        return this.getPartieJeu(joueur.getPseudo()).vainqueurJeu();
    }


    @Override
    public boolean partieCommencee(Joueur joueur) {
        return this.getPartieJeu(joueur.getPseudo()).partieCommencee();
    }


    @Override
    public void finDePartie() {
        this.associationJoueurPartie.clear();
        this.parties.clear();
    }
}
