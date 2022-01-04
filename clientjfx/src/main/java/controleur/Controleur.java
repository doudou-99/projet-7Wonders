package controleur;

import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import modeles.Joueur;
import modeles.facade.FacadeWondersImpl;
import modeles.interfaces.FacadeWonders;
import vues.GestionnaireVue;

import java.util.*;

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
        this.joueur = new Joueur(nom,prenom,pseudo,age,motDePasse);
        this.facadeWonders=new FacadeWondersImpl();
        this.facadeWonders.ajoutJoueur(joueur);
        this.fireOrdre(new Ordre(Ordre.OrdreType.NOUVEAU_JOUEUR));
        this.fireOrdre(new Ordre(Ordre.OrdreType.MENU));
        System.out.println(this.joueur);
    }

    public void nombre(int nombreJoueurs){
        this.nombreJoueur=nombreJoueurs;
        this.fireOrdre(new Ordre(Ordre.OrdreType.NOMBRE_JOUEURS));
        this.fireOrdre(new Ordre(Ordre.OrdreType.JOUEUR));
    }




    @Override
    public void abonnement(EcouteurOrdre o, Ordre.OrdreType... types) {
        Arrays.stream(types).forEach(e -> this.abonnes.get(e).add(o));
    }

    @Override
    public void fireOrdre(Ordre ordre) {

        this.abonnes.get(ordre.getType()).stream().forEach(e -> e.broadCast(ordre));
    }
}
