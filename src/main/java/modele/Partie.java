package modele;

import modele.interfaces.Score;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partie {
    @BsonProperty("_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idPartie;
    private int nbJoueurs;
    private Joueur createur;
    private List<Joueur> participants;
    private EtatPartie etatPartie;

    public enum EtatPartie{ATTENTE,EN_COURS,ARRET,SAUVEGARDE,REPRISE,FIN}

    public Partie(){}

    public Partie(Joueur createur, int nbJoueurs) {
        this.createur = createur;
        this.nbJoueurs=nbJoueurs;
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


    boolean partieTerminee(Joueur joueur){
        return true;
    }


    Joueur getVainqueur(){
        return createur;
    }


    boolean partieCommencee(){
        return this.etatPartie == EtatPartie.EN_COURS;
    }




    List<Piece> getPieces(){
        return createur.getListePieces();
    }





    void finDePartie(Joueur joueur){

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

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
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
