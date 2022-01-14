package vues;

import controleur.Controleur;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PageConnexion implements VueInteractive {
    @FXML
    public Button connecter;
    @FXML
    public TextField ticket;
    @FXML
    public BorderPane borderpane;

    private Scene scene;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    private Controleur controleur;

    public void initialisation(){
        this.scene=new Scene(this.borderpane);
    }

    public static PageConnexion creer(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(PageConnexion.class.getResource("pageConnexion.fxml"));
        try{
            fxmlLoader.load();
            PageConnexion vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.initialisation();
            return vue;

        } catch (IOException e) {
            throw new RuntimeException("Erreur chargement page connexion");
        }
    }

    public void chargerDonnees() {
        this.ticket.setText(this.controleur.getTicket());
        this.ticket.setDisable(false);
        Task<Boolean> attenteJoueur = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (!controleur.partieCommencee());
                return true;
            }
        };
        attenteJoueur.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,e -> controleur.goToPlateau());
        Thread thread = new Thread(attenteJoueur);
        thread.start();

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
