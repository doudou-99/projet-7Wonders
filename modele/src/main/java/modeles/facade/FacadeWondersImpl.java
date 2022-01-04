package modeles.facade;

import modeles.*;
import modeles.dao.BaseMongo;
import modeles.dataencryption.DataChiffrement;
import modeles.dataencryption.Invitation;
import modeles.exceptions.JoueurInexistantException;
import modeles.exceptions.PartieDejaPleineException;
import modeles.exceptions.TicketInvalideException;
import modeles.exceptions.TicketPerimeException;
import modeles.interfaces.FacadeWonders;
import modeles.interfaces.Score;

import java.util.*;

public class FacadeWondersImpl implements FacadeWonders {
    private Map<String, Partie> parties;
    private Map<String,Partie> associationJoueurPartie;
    private List<Joueur> joueurs;
    DataChiffrement dataChiffrement;

    public FacadeWondersImpl(){
        this.parties=new HashMap<>();
        this.associationJoueurPartie=new HashMap<>();
        this.joueurs=new ArrayList<>();
        this.dataChiffrement=new DataChiffrement("joue 7Wonders");
    }

    @Override
    public void ajoutJoueur(Joueur joueur){
        this.joueurs.add( joueur);
        BaseMongo.getBase().getJoueurs().insertOne(joueur);
    }

    @Override
    public String creerPartie(Joueur joueur) {
        Partie partie = new Partie(joueur);
        String id = partie.getIdPartie();
        this.associationJoueurPartie.put(joueur.getPseudo(),partie);
        this.parties.put(id,partie);
        this.ajoutJoueur(joueur);
        Invitation data = new Invitation();
        data.setIdPartie(id);
        data.setJoueurCreateur(joueur);
        BaseMongo.getBase().getJeu().insertOne(partie);
        return this.dataChiffrement.chiffrement(data);

    }

    @Override
    public Joueur getJoueur(String pseudo) throws JoueurInexistantException {
        for (Joueur j: joueurs){
            if (j.getPseudo().equals(pseudo)){
                return j;
            }
        }
        throw new JoueurInexistantException();

    }

    @Override
    public void rejoindrePartie(Joueur joueur, String ticket) throws TicketPerimeException, TicketInvalideException, PartieDejaPleineException {
        Invitation invitation = this.dataChiffrement.dechiffrement(ticket);
        Partie partie = this.parties.get(invitation.getIdPartie());
        if (Objects.isNull(partie)) {
            throw new TicketPerimeException();
        }
        while(joueurs.size()< invitation.getNbJoueurs()){
            partie.rejoindrePartie(joueur);
            this.associationJoueurPartie.put(joueur.getPseudo(),this.parties.get(invitation.getIdPartie()));
        }

    }

    @Override
    public Carte jouerCarte(Joueur joueur, Carte carte) {
        return null;
    }

    @Override
    public void arreterPartie(Joueur joueur) {

    }

    @Override
    public void reprendrePartie(Joueur joueur) {

    }

    @Override
    public void sauvegarderPartie(Joueur joueur) {

    }

    @Override
    public boolean partieTerminee(Joueur joueur) {
        return false;
    }

    @Override
    public Joueur getVainqueur(Joueur joueur) {
        return null;
    }

    @Override
    public boolean partieCommencee(Joueur joueur) {
        return false;
    }

    @Override
    public GestionCapacite getCapacites(Joueur joueur) {
        return null;
    }

    @Override
    public List<Piece> getPieces(Joueur joueur) {
        return null;
    }

    @Override
    public Score getScoreCourant(Joueur joueur) {
        return null;
    }

    @Override
    public void finDePartie(Joueur joueur) {

    }

    public Joueur getCreateur(){
        return null;
    }
}
