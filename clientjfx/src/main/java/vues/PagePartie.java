package vues;

import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PagePartie implements EcouteurOrdre, VueInteractive {
    private BorderPane pane;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    public void initialisation(){
        this.scene = new Scene(this.pane);
    }

    public static PagePartie creer() {
        FXMLLoader fxmlLoader = new FXMLLoader(PagePartie.class.getResource("pagePartie.fxml"));
        try{
            fxmlLoader.load();
            PagePartie vue = fxmlLoader.getController();
            vue.initialisation();
            return vue;

        } catch (IOException e) {
            throw new RuntimeException("Erreur chargement menu");
        }
    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {

    }

    @Override
    public void broadCast(Ordre ordre) {

    }

    @Override
    public void setControleur(Controleur controleur) {

    }
}
