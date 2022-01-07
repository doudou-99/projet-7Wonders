package vues;

import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PageMenu implements EcouteurOrdre,VueInteractive {
    @FXML
    public BorderPane pane;
    @FXML
    public Button jouer;
    @FXML
    public Button reprendre;
    @FXML
    public Button aide;
    @FXML
    public Button accueil;

    private Scene scene;
    private Controleur controleur;

    public void initialisation(){this.scene = new Scene(this.pane);}

    public Scene getScene() {
        return scene;
    }

    public static PageMenu creer(){
        FXMLLoader fxmlLoader = new FXMLLoader(PageMenu.class.getResource("pageMenu.fxml"));
        try{
            fxmlLoader.load();
            PageMenu vue = fxmlLoader.getController();
            vue.initialisation();
            return vue;

        } catch (IOException e) {
            throw new RuntimeException("Erreur chargement menu");
        }
    }


    public void jouerPartie(ActionEvent actionEvent) {
        this.controleur.creerPartie(this.controleur.getJoueur());
    }

    public void reprendrePartie(ActionEvent actionEvent) {
        this.controleur.reprendre(this.controleur.getJoueur());
    }

    public void aide(ActionEvent actionEvent) {
    }

    public void retour(ActionEvent actionEvent) {
        this.controleur.retourAccueil();

    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.NOUVELLE_PARTIE,
                Ordre.OrdreType.REPRENDRE_PARTIE, Ordre.OrdreType.AIDE, Ordre.OrdreType.ACCUEIL);
    }

    @Override
    public void broadCast(Ordre ordre) {
        switch (ordre.getType()){
            case NOUVELLE_PARTIE:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Nouvelle partie");
                alert.setContentText("Partie nouvelle");
                alert.setHeaderText("Partie commencé");
                alert.showAndWait();
                break;
            case REPRENDRE_PARTIE:
                Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
                aler.setTitle("Reprise partie");
                aler.setContentText("Partie reprise");
                aler.setHeaderText("Partie reprise");
                aler.showAndWait();
                break;
            case AIDE:
                Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
                alerte.setTitle("Aide partie");
                alerte.setContentText("Aide partie");
                alerte.setHeaderText("Aide partie");
                alerte.showAndWait();
                break;
            case ACCUEIL:
                Alert ale = new Alert(Alert.AlertType.CONFIRMATION);
                ale.setTitle("Accueil");
                ale.setContentText("Retour à l'accueil!");
                ale.setHeaderText("Accueil");
                ale.showAndWait();
                break;

        }

    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;
    }
}
