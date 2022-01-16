package controleur;

import javafx.stage.Stage;
import modele.ProxyServiceWonders;
import modele.ProxyServiceWondersImpl;
import modeles.Carte;
import modeles.Joueur;
import modeles.Partie;
import modeles.Plateau;
import modeles.dao.BaseMongo;
import modeles.exceptions.PartieDejaPleineException;
import modeles.exceptions.TicketInvalideException;
import modeles.exceptions.TicketPerimeException;
import vues.*;

import java.util.*;

public class Controleur{
    private ProxyServiceWonders wonders;
    private PageAccueil accueil;
    private PagePartie pagePartie;
    private PageConnexion connexion;
    private PageChoixPlateau pageChoixPlateau;
    private PageNbJoueur pageNbJoueur;
    private PageJoueur pageJoueur;
    private String ticket;

    public Controleur(Stage stage){
        this.wonders=new ProxyServiceWondersImpl();
        this.accueil=PageAccueil.creer(stage);
        this.accueil.setControleur(this);
        this.pageJoueur=PageJoueur.creer(stage);
        this.pageJoueur.setControleur(this);
        this.pageNbJoueur=PageNbJoueur.creer(stage);
        this.pageNbJoueur.setControleur(this);
        this.pageChoixPlateau=PageChoixPlateau.creer(stage);
        this.pageChoixPlateau.setControleur(this);
        this.pagePartie=PagePartie.creer(stage);
        this.pagePartie.setControleur(this);
        this.connexion=PageConnexion.creer(stage);
        this.connexion.setControleur(this);
    }

    public void run(){
        accueil.show();
    }

    private Joueur joueur;
    private int nombreJoueur;
    private String nomPlateau;
    private Partie partie;
    private Plateau plateau;
    private String url;
    private List<Carte> cartesMain;

    public void creerJoueur(String nom, String prenom, String age, String pseudo, String motDePasse){
        this.joueur = new Joueur(nom,prenom,pseudo,age,motDePasse);
        this.wonders.ajoutJoueur(this.joueur);
    }

    public void inscription(String nom, String prenom, String age, String pseudo, String motDePasse) {
        this.creerJoueur(nom, prenom, age, pseudo, motDePasse);
        this.connexion.show();
        System.out.println(BaseMongo.getBase().getJoueur(joueur.getPseudo()));
    }

    public void nombre(int nombreJoueurs){
        this.nombreJoueur=nombreJoueurs;
        this.pageJoueur.show();
    }


    public Joueur getJoueur() {
        return joueur;
    }



    public void creerPartie(Joueur joueur) {
        this.joueur=joueur;
        this.ticket = this.wonders.creerPartie(this.joueur, this.nombreJoueur);
        this.connexion.chargerDonnees();
        this.connexion.show();
        System.out.println(this.ticket);
        this.partie=this.wonders.getPartieJeu(joueur.getPseudo());
        System.out.println(this.partie);
    }

    public int getNombreJoueur() {
        return nombreJoueur;
    }

    public String getTicket() {
        return ticket;
    }

    public void goToPartie(){
        this.pagePartie.show();
    }

    public void reprendre(Joueur joueur) {
        this.wonders.reprendrePartie(joueur);
        this.pagePartie.show();
    }

    public void retourAccueil() {
        this.accueil.show();
    }

    public void rejoindrePartie(Joueur joueur,String ticket) throws TicketInvalideException, PartieDejaPleineException, TicketPerimeException {
            if (Objects.isNull(this.partie)){
                this.partie=BaseMongo.getBase().getPartieList().get(0);
                this.wonders.rejoindrePartie(joueur, ticket);
                this.pageChoixPlateau.chargerDonnees();
                this.goToPlateau();
            }

    }

    public void goToPlateau(){
       this.pageChoixPlateau.show();
    }

    public void goToNombreJoueurs() {
       this.pageNbJoueur.show();
    }

    public boolean partieCommencee() {
        return this.wonders.partieCommencee(this.joueur);
    }

    public boolean choixPlateauFait(String pseudo){
        return this.wonders.choixPlateauFait(pseudo);
    }

    public String getNomPlateau() {
        return nomPlateau;
    }

    public Partie getPartie() {
        return partie;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void debut(String nomPlateau){
        this.nomPlateau=nomPlateau;
        this.wonders.debutJeu(this.joueur,nomPlateau);
        this.plateau = this.wonders.getPlateau(this.joueur.getPseudo());
        System.out.println(this.url);
        this.pagePartie.initialiserPlateaux();
        this.cartesMain=this.wonders.getCartesMainJoueur(joueur.getPseudo());
        System.out.println(this.cartesMain);
        this.pagePartie.initialiserCartes();
        this.goToPartie();
        System.out.println(this.joueur.getMerveilles());
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public List<Carte> getCartesMain() {
        return cartesMain;
    }
}
