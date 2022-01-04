package vues;

import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PageJoueur implements EcouteurOrdre,VueInteractive {
    @FXML
    public BorderPane pane;
    @FXML
    public TextField age;
    @FXML
    public TextField pseudo;
    @FXML
    public TextField prenom;
    @FXML
    public TextField nom;
    @FXML
    public PasswordField motDePasse;
    @FXML
    public Button inscrire;
    private Scene scene;
    private Controleur controleur;

    public void initialisation(){
        this.scene=new Scene(this.pane);
    }

    public static PageJoueur creer(){
        FXMLLoader fxmlLoader = new FXMLLoader(PageJoueur.class.getResource("pageJoueur.fxml"));
        try {
            fxmlLoader.load();
            PageJoueur vue= fxmlLoader.getController();
            vue.initialisation();
            return vue;

        }catch (IOException e) {
            throw new RuntimeException("Erreur chargement page joueur");
        }
    }

    public void inscription(ActionEvent actionEvent) {

        if (nom.getText().length() < 3 || this.nom.getText().isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie du nom");
            alert.setContentText("Le champ nom ne doit pas être vide et il doit être supérieur à 3");
            alert.showAndWait();
        }
        if (prenom.getText().isEmpty() || prenom.getText().length()<3  ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie du champ prenom ");
            alert.setContentText("Le champ prenom ne doit pas être vide et il doit être supérieur à 3");
            alert.showAndWait();
        }
        if (age.getText().length() <2 || age.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie du champ age");
            alert.setContentText("Le champ age ne doit pas être vide et il doit être supérieur ou égal à 2");
            alert.showAndWait();
        }
        if (pseudo.getText().isEmpty() || pseudo.getText().length() <3){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie du champ pseudo");
            alert.setContentText("Le pseudo ne doit pas être vide et il doit être supérieur à 3");
            alert.showAndWait();
        }
        if(motDePasse.getText().isEmpty() || motDePasse.getText().length()<3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie du champ mot de passe");
            alert.setContentText("Le mot de passe ne doit pas être vide et il doit être supérieur à 3");
            alert.showAndWait();
        }

        this.controleur.ajoutJoueur(nom.getText(),prenom.getText(),age.getText(),pseudo.getText(),motDePasse.getText());

    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.NOUVEAU_JOUEUR, Ordre.OrdreType.MENU);
    }

    @Override
    public void broadCast(Ordre ordre) {
        switch (ordre.getType()){
            case NOUVEAU_JOUEUR:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmination de l'ajout du joueur");
                alert.setContentText("Le joueur "+ this.pseudo.getText() +" a été ajouté! ");
                alert.showAndWait();
            case MENU:
                Alert ale = new Alert(Alert.AlertType.CONFIRMATION);
                ale.setTitle("Page joueur");
                ale.setContentText("Affichage de l'inscription du joueur");
                ale.showAndWait();
                break;

        }
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;
    }

    public Scene getScene() {
        return scene;
    }
}
