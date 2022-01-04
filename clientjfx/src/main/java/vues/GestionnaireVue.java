package vues;

import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.stage.Stage;

public class GestionnaireVue implements VueInteractive, EcouteurOrdre {
    private Stage stage;
    private PageAccueil pageAccueil;
    private PageJoueur pageJoueur;
    private PageMenu pageMenu;
    private PageChoixPlateau plateau;

    public GestionnaireVue(Stage stage){
        this.stage=stage;

        this.pageAccueil=PageAccueil.creer();
        this.pageJoueur=PageJoueur.creer();
        this.pageMenu=PageMenu.creer();
        this.plateau=PageChoixPlateau.creer();

    }


    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.ACCUEIL, Ordre.OrdreType.MENU,
                Ordre.OrdreType.JOUEUR, Ordre.OrdreType.NOMBRE_JOUEURS, Ordre.OrdreType.NOUVEAU_JOUEUR,
                Ordre.OrdreType.AIDE, Ordre.OrdreType.NOUVEAU_PLATEAU, Ordre.OrdreType.CHOIX_PLATEAU,
                Ordre.OrdreType.ARRETER_PARTIE, Ordre.OrdreType.CONNEXION, Ordre.OrdreType.NOUVELLE_PARTIE,
                Ordre.OrdreType.REPRENDRE_PARTIE, Ordre.OrdreType.SAUVER_PARTIE);
        this.pageAccueil.setAbonnements(controleur);
        this.pageJoueur.setAbonnements(controleur);
        this.pageMenu.setAbonnements(controleur);
        this.plateau.setAbonnements(controleur);
    }

    @Override
    public void broadCast(Ordre ordre) {
        switch (ordre.getType()){
            case MENU:
                this.stage.setScene(this.pageMenu.getScene());
                this.stage.show();
                break;
            case ACCUEIL:
                this.stage.setScene(this.pageAccueil.getScene());
                this.stage.show();
                break;
            case JOUEUR:
                this.stage.setScene(this.pageJoueur.getScene());
                this.stage.show();
                break;
            case CHOIX_PLATEAU:
                this.stage.setScene(this.plateau.getScene());
                this.stage.show();
                break;
        }
    }

    @Override
    public void setControleur(Controleur controleur) {
        pageAccueil.setControleur(controleur);
        pageJoueur.setControleur(controleur);
        pageMenu.setControleur(controleur);
    }
}
