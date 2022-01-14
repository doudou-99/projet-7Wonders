package vues;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PageAccueil implements VueInteractive {
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
    private Stage stage;

    public void initialisation(){this.scene = new Scene(this.pane);}

    public Scene getScene() {
        return scene;
    }

    public static PageAccueil creer(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(PageAccueil.class.getResource("pageAccueil.fxml"));
        try{
            fxmlLoader.load();


        } catch (IOException e) {
            throw new RuntimeException("Erreur chargement menu");
        }
        PageAccueil vue = fxmlLoader.getController();
        vue.setStage(stage);
        vue.initialisation();
        vue.initailiserBouton();
        return vue;
    }

    public void initailiserBouton(){
        this.jouer.setOnAction(e -> jouerPartie());
    }


    public void jouerPartie() {
        this.controleur.goToNombreJoueurs();
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
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
