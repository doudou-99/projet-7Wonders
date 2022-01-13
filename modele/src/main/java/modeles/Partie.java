package modeles;

import modeles.dao.BaseMongo;
import modeles.exceptions.*;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Partie implements Serializable {
    private static final long serialVersionUID=1L;
    @BsonProperty("_id")
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idPartie;
    private int nbJoueurs;
    private Joueur createur;
    private Joueur joueur;
    private Joueur joueur1;
    private Joueur joueur2;
    private List<Joueur> participants;
    private EtatPartie etatPartie;
    private PartieGestion partieGestionCourant;


    public enum EtatPartie{ATTENTE,EN_COURS,ARRET,REPRISE,FIN}

    public Partie(){}

    public Partie(Joueur createur) {
        this.createur = createur;
        this.participants=new ArrayList<>();
        this.etatPartie = EtatPartie.ATTENTE;
        this.participants.add(this.createur);
    }


    public void arreterPartie(){
        this.sauvegarderPartie();
        this.etatPartie= EtatPartie.ARRET;
    }


    public void reprendrePartie(){
        this.etatPartie= EtatPartie.REPRISE;
    }


    public void sauvegarderPartie(){
        BaseMongo.getBase().majPartie(idPartie,partieGestionCourant);
    }


    public boolean partieCommencee(){
        return this.etatPartie == EtatPartie.EN_COURS;
    }

    public void faireAffaire(String pseudo,String choixCite, String nomCarte) throws ConstructionImpossibleException, RessourceInexistanteException, PieceInsuffisanteException, RessourceVoisinInsuffisantException {
        this.partieGestionCourant.getGestionCommerce().faireAffaire(pseudo, choixCite, nomCarte);
    }

    public void debutJeu(Joueur joueur,String nomPlateau){
        this.partieGestionCourant.choisirPlateau(joueur,nomPlateau);
    }

    public void rejoindrePartie(Joueur joueur) throws PartieDejaPleineException {

        if (etatPartie != EtatPartie.ATTENTE && this.participants.size()==nbJoueurs)
            throw new PartieDejaPleineException();
        while (this.participants.size()<nbJoueurs){
            this.participants.add(joueur);
        }
        this.partieGestionCourant=new PartieGestion(participants);
        this.etatPartie = EtatPartie.EN_COURS;

    }

    public boolean choixPlateauFait(String pseudo){
        for(Joueur j: participants){
            if (j.getPseudo().equals(pseudo) && !(j.getMerveilles().equals(""))){
                return true;
            }
        }
        return false;
    }

    public String vainqueurBatailleAge(String joueur,GestionTour gestionTour){
        GestionBataille gestionBataille = new GestionBataille();
        gestionBataille.setGestionTour(gestionTour);
        gestionBataille.setJoueurBataille(this.partieGestionCourant.getJoueurs());
        this.partieGestionCourant.setGestionBataille(gestionBataille);
        this.partieGestionCourant.getGestionBataille().vainqueurBataille(joueur,gestionTour.getTour().getAge());
        return this.partieGestionCourant.getGestionBataille().getBataille().getNomDuVainqueur();
    }


    public void choixJoueur(String joueur, String choix,String nomCarte,String choixCarte,Age age) throws ChoixDejaFaitException, ConstructionMerveilleImpossible, PieceInsuffisanteException, RessourceInexistanteException, RessourceVoisinInsuffisantException, CartePasConstruiteException, RessourceInsuffisanteException, CiteContientCarteException, ConstructionImpossibleException, ChoixIncompletsException {
        this.partieGestionCourant.setGestiontour(new GestionTour(age));
        while (this.partieGestionCourant.getGestiontour().getTour().getNombreTourEnCours()<= age.getNombreTour()) {
                this.partieGestionCourant.choixJoueur(joueur, choix, nomCarte, choixCarte);
                this.partieGestionCourant.maj(joueur, choix, nomCarte, choixCarte);
                if (partieGestionCourant.tourJoueursFini() &&
                        this.partieGestionCourant.getGestiontour().getTour().getNombreTourEnCours()==this.partieGestionCourant.getGestiontour().getTour().getAge().getNombreTour()) {
                    this.vainqueurBatailleAge(joueur,this.partieGestionCourant.getGestiontour());
                    this.partieGestionCourant.getGestiontour().getTour().setAge(BaseMongo.getBase().getAges().get(1));
                }
                if (this.partieGestionCourant.partieTerminee()) {
                    this.etatPartie = EtatPartie.FIN;
                }
        }
    }

    /*public void jouerPartie(String pseudo,){
        Age age = BaseMongo.getBase().getAges().get(0);
        Tour tour = new Tour(age);
        this.gestionTour=new GestionTour(tour);
        while (this.gestionTour.getTour().getNombreTourEnCours()<= age.getNombreTour()){
            this.choixJoueur();

        }
    }*/

    public String vainqueurJeu() throws PartieNonTermineeException {
        return this.partieGestionCourant.vainqueur();
    }

    public boolean partieTerminee(){
        if (partieGestionCourant.getGestiontour().getTour().getAge()== BaseMongo.getBase().getAges().get(2) &&
                partieGestionCourant.getGestiontour().getTourEnCours()>=partieGestionCourant.getGestiontour().getTour().getAge().getNombreTour()) {
            this.etatPartie= EtatPartie.FIN;
            return true;
        }
        return false;
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

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }



    public PartieGestion getScoreGestionCourant() {
        return partieGestionCourant;
    }

    public void setScoreGestionCourant(PartieGestion partieGestionCourant) {
        this.partieGestionCourant = partieGestionCourant;
    }


    public PartieGestion getPartieGestionCourant() {
        return partieGestionCourant;
    }

    public void setPartieGestionCourant(PartieGestion partieGestionCourant) {
        this.partieGestionCourant = partieGestionCourant;
    }

    @Override
    public String toString() {
        return "Partie{" +
                "idPartie='" + idPartie + '\'' +
                ", nbJoueurs=" + nbJoueurs +
                ", createur=" + createur +
                ", joueur=" + joueur +
                ", joueur1=" + joueur1 +
                ", joueur2=" + joueur2 +
                ", participants=" + participants +
                ", etatPartie=" + etatPartie +
                ", partieGestionCourant=" + partieGestionCourant +
                '}';
    }
}
