package controleur;

import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import modele.Joueur;
import modele.interfaces.FacadeWonders;
import example.GestionnaireVue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Controleur implements LanceurOrdre {
    private FacadeWonders facadeWonders;
    private GestionnaireVue gestionnaireVue;
    private Map<Ordre.OrdreType, Collection<EcouteurOrdre>> abonnes;

    public Controleur(FacadeWonders facadeWonders, GestionnaireVue gestionnaireVue){
        this.facadeWonders=facadeWonders;
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


    public void ajoutJoueur(String nom, String prenom, String age, String pseudo, String motDePasse) {
        this.joueur = new Joueur(nom,prenom,age, pseudo,motDePasse);
        this.facadeWonders.ajoutJoueur(joueur);
        this.fireOrdre(new Ordre(Ordre.OrdreType.NOUVEAU_JOUEUR));
        this.fireOrdre(new Ordre(Ordre.OrdreType.MENU));
    }

    public void nombre(int nombreJoueurs){
        this.nombreJoueur=nombreJoueurs;
        this.fireOrdre(new Ordre(Ordre.OrdreType.NOMBRE_JOUEURS));
        this.fireOrdre(new Ordre(Ordre.OrdreType.JOUEUR));
    }




    @Override
    public void abonnement(EcouteurOrdre o, Ordre.OrdreType... types) {
        for (Ordre.OrdreType ordre: types){
            this.abonnes.get(ordre).add(o);
        }
    }

    @Override
    public void fireOrdre(Ordre ordre) {
        this.abonnes.get(ordre.getType()).stream().forEach(e -> e.broadCast(ordre));
    }
}
