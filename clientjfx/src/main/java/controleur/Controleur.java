package controleur;

import client.ServiceWonders;
import client.ServiceWondersImpl;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import modele.ProxyServiceWonders;
import modele.ProxyServiceWondersImpl;
import modeles.Joueur;
import modeles.Partie;
import modeles.dao.BaseMongo;
import modeles.exceptions.PartieDejaPleineException;
import modeles.exceptions.TicketInvalideException;
import modeles.exceptions.TicketPerimeException;
import vues.GestionnaireVue;

import java.util.*;

public class Controleur implements LanceurOrdre {
    private ProxyServiceWonders wonders;
    private GestionnaireVue gestionnaireVue;
    private Map<Ordre.OrdreType, Collection<EcouteurOrdre>> abonnes;
    private String ticket;

    public Controleur(GestionnaireVue gestionnaireVue){
        this.wonders=new ProxyServiceWondersImpl();
        this.gestionnaireVue=gestionnaireVue;
        this.abonnes = new HashMap<>();
        for (Ordre.OrdreType type: Ordre.OrdreType.values()){
            this.abonnes.put(type, new ArrayList<>());
        }
        this.gestionnaireVue.setControleur(this);
        this.gestionnaireVue.setAbonnements(this);
    }

    public void run(){
        this.fireOrdre(new Ordre(Ordre.OrdreType.ACCUEIL));
    }

    private Joueur joueur;
    private int nombreJoueur;
    private String nomPlateau;


    public void inscription(String nom, String prenom, String age, String pseudo, String motDePasse) {
        this.joueur = new Joueur(nom,prenom,pseudo,age,motDePasse);
        this.wonders.ajoutJoueur(joueur);
        this.fireOrdre(new Ordre(Ordre.OrdreType.NOUVEAU_JOUEUR));
        System.out.println(BaseMongo.getBase().getJoueur(joueur.getPseudo()));
    }

    public void nombre(int nombreJoueurs){
        this.nombreJoueur=nombreJoueurs;
        this.fireOrdre(new Ordre(Ordre.OrdreType.NOMBRE_JOUEURS));
        this.fireOrdre(new Ordre(Ordre.OrdreType.JOUEUR));
    }


    public Joueur getJoueur() {
        return joueur;
    }

    @Override
    public void abonnement(EcouteurOrdre o, Ordre.OrdreType... types) {
        Arrays.stream(types).forEach(e -> this.abonnes.get(e).add(o));
    }

    @Override
    public void fireOrdre(Ordre ordre) {
        this.abonnes.get(ordre.getType()).stream().forEach(e -> e.broadCast(ordre));
    }

    public void creerPartie(Joueur joueur) {
        this.joueur=joueur;
        this.ticket = this.wonders.creerPartie(joueur);
        this.fireOrdre(new Ordre(Ordre.OrdreType.NOUVELLE_PARTIE));
        this.fireOrdre(new Ordre(Ordre.OrdreType.CONNEXION));
    }

    public int getNombreJoueur() {
        return nombreJoueur;
    }

    public String getTicket() {
        return ticket;
    }


    public Partie getPartie(String pseudo){
        Partie partie =this.wonders.getPartieJeu(pseudo);
        partie.setCreateur(this.joueur);
        partie.setNbJoueurs(this.nombreJoueur);
        return partie;

    }

    public void reprendre(Joueur joueur) {
        this.wonders.reprendrePartie(joueur);
        this.fireOrdre(new Ordre(Ordre.OrdreType.REPRENDRE_PARTIE));
    }

    public void retourAccueil() {
        this.fireOrdre(new Ordre(Ordre.OrdreType.ACCUEIL));
    }

    public void rejoindrePartie(Joueur joueur,String ticket){
        try {
            this.wonders.rejoindrePartie(joueur, ticket);
            this.fireOrdre(new Ordre(Ordre.OrdreType.REJOINDRE_PARTIE));
            this.fireOrdre(new Ordre(Ordre.OrdreType.CHOIX_PLATEAU));
        } catch (TicketPerimeException e) {
            this.fireOrdre(new Ordre(Ordre.OrdreType.ERREUR_TICKET_PERIME));
        } catch (TicketInvalideException e) {
            this.fireOrdre(new Ordre(Ordre.OrdreType.ERREUR_TICKET_INVALIDE));
        } catch (PartieDejaPleineException e) {
            this.fireOrdre(new Ordre(Ordre.OrdreType.ERREUR_PARTIE_PLEINE));
        }

    }

    public void goToNombreJoueurs() {
        this.fireOrdre(new Ordre(Ordre.OrdreType.PAGE_NOMBRE));
    }

    public boolean partieCommencee() {
        return this.wonders.partieCommencee(this.joueur);
    }

    public void debut(String nomPlateau){
        this.nomPlateau=nomPlateau;
        this.wonders.debutJeu(this.joueur,nomPlateau);
        this.fireOrdre(new Ordre(Ordre.OrdreType.NOUVEAU_PLATEAU));
        this.fireOrdre(new Ordre(Ordre.OrdreType.JOUER_PARTIE));
    }
}
