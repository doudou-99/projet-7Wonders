package vues;

import controleur.Controleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modeles.Carte;
import modeles.Joueur;
import modeles.dao.BaseMongo;

import java.io.IOException;

public class PagePartie implements VueInteractive {

    @FXML
    private HBox carteAjouer;
    @FXML
    private HBox plateaux;
    @FXML
    private HBox plateauCreateur;
    @FXML
    private BorderPane pane;
    private Scene scene;
    private Stage stage;
    private Controleur controleur;

    public void initialiserPlateaux(){
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        Image image = new Image(controleur.getUrl());
        imageView.setImage(image);
        plateauCreateur.getChildren().add(imageView);
        for (Joueur j: controleur.getPartie().getParticipants()){
            if(j != this.controleur.getJoueur() && !j.getMerveilles().equals("")){
                ImageView imageView1 = new ImageView();
                imageView1.setImage(new Image(String.valueOf(PagePartie.class.getResource("images/PlateauMerveilles/"+BaseMongo.getBase().getPlateauNom(j.getMerveilles()).getImage()))));
                plateaux.getChildren().add(imageView1);
            }
        }
    }

    public void initialiserCartes(){

        for (Carte carte:controleur.getCartesMain()){
            ImageView imageView = new ImageView();
            imageView.setPreserveRatio(false);
            imageView.setSmooth(true);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            if (carte.getAge().equals(BaseMongo.getBase().getAges().get(0).getId())){
                imageView.setImage(new Image(String.valueOf(PagePartie.class.getResource("images/cartesAgeI/"+ BaseMongo.getBase().getCartesNom(carte.getNom()).getImage()))));
                carteAjouer.getChildren().add(imageView);
            }else if (carte.getAge().equals(BaseMongo.getBase().getAges().get(1).getId())){
                imageView.setImage(new Image(String.valueOf(PagePartie.class.getResource("images/cartesAgeII/"+ BaseMongo.getBase().getCartesNom(carte.getNom()).getImage()))));
                carteAjouer.getChildren().add(imageView);
            }else if (carte.getAge().equals(BaseMongo.getBase().getAges().get(2).getId())){
                imageView.setImage(new Image(String.valueOf(PagePartie.class.getResource("images/cartesAgeIII/"+ BaseMongo.getBase().getCartesNom(carte.getNom()).getImage()))));
                carteAjouer.getChildren().add(imageView);
            }
        }
    }

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
