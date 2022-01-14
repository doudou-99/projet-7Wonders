package vues;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PageNbJoueur implements VueInteractive {

    @FXML
    public TextField nbJoueurs;
    @FXML
    public BorderPane borderpane;
    @FXML
    public Button bouton;
    private Scene scene;
    private Controleur controleur;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage=stage;
    }

    public void initialisation(){
        this.scene=new Scene(this.borderpane);
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public static PageNbJoueur creer(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(PageNbJoueur.class.getResource("pageNbJoueur.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
           throw new RuntimeException("Erreur chargement");
        }
        PageNbJoueur vue = fxmlLoader.getController();
        vue.setStage(stage);
        vue.initialisation();
        return vue;
    }

    public void debut(ActionEvent actionEvent) {
        if (!Objects.isNull(nbJoueurs) && Character.isDigit(nbJoueurs.getText().charAt(0)) && nbJoueurs.getLength()<2 && nbJoueurs.getLength()>0){
            this.controleur.nombre(Integer.parseInt(nbJoueurs.getText()));
            this.nbJoueurs.setText("");
        }
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

    public Controleur getControleur() {
        return controleur;
    }
}
