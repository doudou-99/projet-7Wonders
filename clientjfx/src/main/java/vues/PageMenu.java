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
    }

    public void reprendrePartie(ActionEvent actionEvent) {
    }

    public void aide(ActionEvent actionEvent) {
    }

    public void retour(ActionEvent actionEvent) {
    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.NOUVELLE_PARTIE,
                Ordre.OrdreType.REPRENDRE_PARTIE, Ordre.OrdreType.AIDE, Ordre.OrdreType.ACCUEIL, Ordre.OrdreType.AIDE);
    }

    @Override
    public void broadCast(Ordre ordre) {

    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;
    }
}
