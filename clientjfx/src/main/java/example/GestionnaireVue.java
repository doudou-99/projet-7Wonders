package example;

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

    public GestionnaireVue(Stage stage){
        this.stage=stage;
        this.pageAccueil=PageAccueil.creer();
        this.pageJoueur=PageJoueur.creer();
        this.pageMenu=PageMenu.creer();

    }


    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.ACCUEIL, Ordre.OrdreType.MENU,
                Ordre.OrdreType.JOUEUR, Ordre.OrdreType.NOMBRE_JOUEURS, Ordre.OrdreType.NOUVEAU_JOUEUR);
        this.pageAccueil.setAbonnements(controleur);
        this.pageJoueur.setAbonnements(controleur);
        this.pageMenu.setAbonnements(controleur);
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
        }
    }

    @Override
    public void setControleur(Controleur controleur) {
        pageAccueil.setControleur(controleur);
        pageJoueur.setControleur(controleur);
        pageMenu.setControleur(controleur);
    }
}
