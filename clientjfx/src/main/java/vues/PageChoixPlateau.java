package vues;

import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;


public class PageChoixPlateau implements EcouteurOrdre,VueInteractive {
    @FXML
    public ImageView PiramideGizeh;
    @FXML
    public ImageView PhareAlexandrie;
    @FXML
    public ImageView JardinsSuspendus;
    @FXML
    public ImageView MausoleeDHalicarnasse;
    @FXML
    public ImageView ColosseDeRhodeus;
    @FXML
    public ImageView StatueDeZeus;
    @FXML
    public ImageView TempleDArtemis;
    @FXML
    public BorderPane borderpane;

    private Scene scene;
    private Controleur controleur;

    public void initialisation(){
        this.scene=new Scene(this.borderpane);
    }

    public Scene getScene() {
        return scene;
    }

    public static PageChoixPlateau creer(){
        FXMLLoader fxmlLoader = new FXMLLoader(PageChoixPlateau.class.getResource("pageChoixPlateau.fxml"));
        try{
            fxmlLoader.load();
            PageChoixPlateau vue = fxmlLoader.getController();
            vue.initialisation();
            return vue;

        } catch (IOException e) {
            throw new RuntimeException("Erreur chargement choix plateau");
        }
    }

    public void choix(MouseEvent mouseEvent) {

    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.JOUEUR, Ordre.OrdreType.CHOIX_PLATEAU, Ordre.OrdreType.NOUVEAU_PLATEAU);
    }

    @Override
    public void broadCast(Ordre ordre) {

    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;
    }
}
