package vues;

import controleur.Controleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PagePartie implements VueInteractive {
    @FXML
    private BorderPane pane;
    private Scene scene;
    private Stage stage;
    private Controleur controleur;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void initialisation(){
        this.scene = new Scene(this.pane);
    }

    public static PagePartie creer(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(PagePartie.class.getResource("pagePartie.fxml"));
        try{
            fxmlLoader.load();
            PagePartie vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.initialisation();
            return vue;

        } catch (IOException e) {
            throw new RuntimeException("Erreur chargement menu");
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
}
