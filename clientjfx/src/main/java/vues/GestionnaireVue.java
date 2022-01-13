package vues;

import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Objects;

public class GestionnaireVue implements VueInteractive, EcouteurOrdre {
    private Stage stage;
    private PageNbJoueur pageNbJoueur;
    private PageJoueur pageJoueur;
    private PageAccueil pageAccueil;
    private PageChoixPlateau plateau;
    private PageConnexion pageConnexion;
    private PagePartie pagePartie;

    public GestionnaireVue(Stage stage){
        this.stage=stage;

        this.pageNbJoueur= PageNbJoueur.creer();
        this.pageJoueur=PageJoueur.creer();
        this.pageAccueil= PageAccueil.creer();
        this.pageConnexion=PageConnexion.creer();
        this.plateau=PageChoixPlateau.creer();
        this.pagePartie = PagePartie.creer();
    }


    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.ACCUEIL, Ordre.OrdreType.PAGE_NOMBRE,
                Ordre.OrdreType.JOUEUR, Ordre.OrdreType.NOMBRE_JOUEURS, Ordre.OrdreType.NOUVEAU_JOUEUR,
                Ordre.OrdreType.AIDE, Ordre.OrdreType.NOUVEAU_PLATEAU, Ordre.OrdreType.CHOIX_PLATEAU,
                Ordre.OrdreType.ARRETER_PARTIE, Ordre.OrdreType.CONNEXION, Ordre.OrdreType.NOUVELLE_PARTIE,
                Ordre.OrdreType.REPRENDRE_PARTIE, Ordre.OrdreType.SAUVER_PARTIE,
                Ordre.OrdreType.ERREUR_TICKET_PERIME, Ordre.OrdreType.ERREUR_TICKET_INVALIDE,
                Ordre.OrdreType.ERREUR_PARTIE_PLEINE, Ordre.OrdreType.RETOUR, Ordre.OrdreType.NOUVEAU_PLATEAU,
                Ordre.OrdreType.JOUER_PARTIE);
        this.pageAccueil.setAbonnements(controleur);
        this.pageJoueur.setAbonnements(controleur);
        this.pageNbJoueur.setAbonnements(controleur);
        this.pageConnexion.setAbonnements(controleur);
        this.plateau.setAbonnements(controleur);
        this.pagePartie.setAbonnements(controleur);
    }

    @Override
    public void broadCast(Ordre ordre) {

        switch (ordre.getType()){
            case PAGE_NOMBRE:
                this.stage.setScene(this.pageNbJoueur.getScene());
                this.stage.show();
                Controleur controleur = this.pageNbJoueur.getControleur();
                if (!Objects.isNull(controleur.getTicket())){
                    this.pageNbJoueur.nbJoueurs.setText(String.valueOf(controleur.getNombreJoueur()));
                }
                break;
            case ACCUEIL:
                this.stage.setScene(this.pageAccueil.getScene());
                this.stage.show();
                break;
            case JOUEUR:
                this.stage.setScene(this.pageJoueur.getScene());
                this.stage.show();

                break;
            case CONNEXION:
                this.pageConnexion.ticket.setText(this.pageConnexion.getControleur().getTicket());
                this.pageConnexion.ticket.setDisable(false);
                controleur = this.pageConnexion.getControleur();
                this.stage.setScene(this.pageConnexion.getScene());
                this.stage.show();


                break;
            case CHOIX_PLATEAU:
                this.stage.setScene(this.plateau.getScene());
                this.stage.show();
                break;
            case ERREUR_TICKET_INVALIDE:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur: ticket invalide");
                alert.setContentText("Attention! Le ticket est invalide!");
                alert.setHeaderText("Ticket invalide.");
                alert.showAndWait();
                break;
            case JOUER_PARTIE:
                this.stage.setScene(this.pagePartie.getScene());
                this.stage.show();
                break;
            case ERREUR_TICKET_PERIME:
                Alert aler = new Alert(Alert.AlertType.ERROR);
                aler.setTitle("Erreur: ticket périmé");
                aler.setContentText("Attention! Le ticket est périmé!");
                aler.setHeaderText("Ticket périmé.");
                aler.showAndWait();
                break;
            case ERREUR_PARTIE_PLEINE:
                Alert ale = new Alert(Alert.AlertType.ERROR);
                ale.setTitle("Erreur: Partie pleine");
                ale.setContentText("Attention! La partie est déjà en cours !");
                ale.setHeaderText("Partie pleine.");
                ale.showAndWait();
                break;
        }
    }

    @Override
    public void setControleur(Controleur controleur) {
        pageAccueil.setControleur(controleur);
        pageJoueur.setControleur(controleur);
        pageNbJoueur.setControleur(controleur);
        pageConnexion.setControleur(controleur);
        pagePartie.setControleur(controleur);
    }
}
