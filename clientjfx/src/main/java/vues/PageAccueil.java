package vues;

import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class PageAccueil implements EcouteurOrdre,VueInteractive {

    @FXML
    public TextField nbJoueurs;
    @FXML
    public BorderPane borderpane;
    @FXML
    public Button bouton;
    private Scene scene;
    private Controleur controleur;

    public void initialisation(){
        this.scene=new Scene(this.borderpane);
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public static PageAccueil creer(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PageAccueil.class.getResource("pageAccueil.fxml"));
            fxmlLoader.load();
            PageAccueil vue = fxmlLoader.getController();
            vue.initialisation();
            return vue;
        } catch (IOException e) {
           throw new RuntimeException("Erreur chargement");
        }
    }

    public void debut(ActionEvent actionEvent) {
        if (!Objects.isNull(nbJoueurs) && Character.isDigit(nbJoueurs.getText().charAt(0)) && nbJoueurs.getLength()<2 && nbJoueurs.getLength()>0){
            this.controleur.nombre(Integer.parseInt(nbJoueurs.getText()));
        }
    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.NOMBRE_JOUEURS, Ordre.OrdreType.JOUEUR);
    }

    @Override
    public void broadCast(Ordre ordre) {
        switch (ordre.getType()){
            case NOMBRE_JOUEURS:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de nombre de joueurs pour 7Wonders");
                alert.setContentText("Le nombre de joueurs requis est bien "+ this.nbJoueurs.getText() +" !");
                alert.showAndWait();
                break;
            case JOUEUR:
                Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
                aler.setTitle("Confirmation page joueur");
                aler.setContentText("On peut passer à la page d'inscription !");
                aler.showAndWait();
                break;
        }
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;
    }
}
