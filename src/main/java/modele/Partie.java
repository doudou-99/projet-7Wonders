package modele;

import modele.dao.BaseMongo;
import modele.exceptions.*;
import modele.interfaces.Score;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    @BsonProperty("_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idPartie;
    private final int nbJoueurs = 4;
    private Joueur createur;
    private Joueur joueur;
    private Joueur joueur1;
    private Joueur joueur2;
    private List<Joueur> participants;
    private EtatPartie etatPartie;
    private GestionTour tour;
    private ScoreGestion scoreGestionCourant;
    private Score scoreCourant;

    public enum EtatPartie{ATTENTE,EN_COURS,ARRET,SAUVEGARDE,REPRISE,FIN}

    public Partie(){}

    public Partie(Joueur createur) {
        this.createur = createur;
        this.participants=new ArrayList<>();
        this.etatPartie = EtatPartie.ATTENTE;
    }

    String creerPartie(Joueur joueur){
        return null;
    }

    void rejoindrePartie(Joueur joueur,String ticket){

    }


    Carte jouerCarte(Joueur joueur, Carte carte){
        return carte;
    }


    void arreterPartie(Joueur joueur){
    }


    void reprendrePartie(Joueur joueur){

    }


    void sauvegarderPartie(Joueur joueur){

    }


    boolean partieCommencee(){
        return this.etatPartie == EtatPartie.EN_COURS;
    }




    List<Piece> getPieces(){
        return createur.getListePieces();
    }



    public void rejoindrePartie(Joueur joueur2) throws PartieDejaPleineException {
        if (etatPartie != EtatPartie.ATTENTE && this.participants.size()==nbJoueurs)
            throw new PartieDejaPleineException();
        this.participants.add(joueur2);
        this.etatPartie = EtatPartie.EN_COURS;

    }


    public void choixJoueur(String joueur, String choix,String nomCarte) throws ChoixDejaFaitException, ConstructionMerveilleImpossible {
        this.scoreGestionCourant.setChoixJoueur(joueur,choix,nomCarte);
        try {
            this.scoreCourant = this.scoreGestionCourant.maj(joueur,choix,nomCarte);
            if (this.scoreGestionCourant.partieTerminee()) {
                this.etatPartie = EtatPartie.FIN;
            }
        } catch (ChoixIncompletsException e) {

        }
    }

    public String getVainqueur() throws PartieNonTermineeException {
        return this.scoreGestionCourant.getVainqueur();
    }

    public boolean partieTerminee(){
        if (tour.getAge()== BaseMongo.getBase().getAges().get(2) && tour.getTourEnCours()>=tour.getAge().getNombreTour()) {
            this.etatPartie=EtatPartie.FIN;
            return true;
        }
        return false;
    }

    public Score getScoreCourant() {
        return scoreCourant;
    }

    public void setCreateur(Joueur createur) {
        this.createur = createur;
    }

    public String getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(String idPartie) {
        this.idPartie = idPartie;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public Joueur getCreateur() {
        return createur;
    }

    public List<Joueur> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Joueur> participants) {
        this.participants = participants;
    }

    public EtatPartie getEtatPartie() {
        return etatPartie;
    }

    public void setEtatPartie(EtatPartie etatPartie) {
        this.etatPartie = etatPartie;
    }


    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }

    public GestionTour getTour() {
        return tour;
    }

    public void setTour(GestionTour tour) {
        this.tour = tour;
    }

    public ScoreGestion getScoreGestionCourant() {
        return scoreGestionCourant;
    }

    public void setScoreGestionCourant(ScoreGestion scoreGestionCourant) {
        this.scoreGestionCourant = scoreGestionCourant;
    }

    public void setScoreCourant(Score scoreCourant) {
        this.scoreCourant = scoreCourant;
    }

    @Override
    public String toString() {
        return "Partie{" +
                "idPartie='" + idPartie + '\'' +
                ", nbJoueurs=" + nbJoueurs +
                ", createur=" + createur +
                ", participants=" + participants +
                ", etatPartie=" + etatPartie +
                '}';
    }
}
