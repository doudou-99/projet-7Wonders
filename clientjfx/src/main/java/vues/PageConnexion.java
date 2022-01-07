package vues;

import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PageConnexion implements EcouteurOrdre,VueInteractive {
    @FXML
    public Button connecter;
    @FXML
    public TextField ticket;
    @FXML
    public BorderPane borderpane;

    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    private Controleur controleur;

    public void initialisation(){
        this.scene=new Scene(this.borderpane);
    }

    public static PageConnexion creer(){
        FXMLLoader fxmlLoader = new FXMLLoader(PageConnexion.class.getResource("pageConnexion.fxml"));
        try{
            fxmlLoader.load();
            PageConnexion vue = fxmlLoader.getController();
            vue.initialisation();
            return vue;

        } catch (IOException e) {
            throw new RuntimeException("Erreur chargement page connexion");
        }
    }

    public void connexion(ActionEvent actionEvent) {
    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {

    }

    @Override
    public void broadCast(Ordre ordre) {

    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;
    }
}
