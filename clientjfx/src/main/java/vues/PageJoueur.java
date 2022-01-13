package vues;

import com.fasterxml.jackson.databind.ser.Serializers;
import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import modeles.dao.BaseMongo;
import modeles.exceptions.PartieDejaPleineException;
import modeles.exceptions.TicketInvalideException;
import modeles.exceptions.TicketPerimeException;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

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
        String nom = this.nom.getText();
        String prenom=this.prenom.getText();
        String age = this.age.getText();
        String pseudo=this.pseudo.getText();
        String motDePasse = this.motDePasse.getText();
        if (this.nom.getLength() < 3 || Objects.isNull(this.nom) ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie du nom");
            alert.setContentText("Le champ nom ne doit pas être vide et il doit être supérieur à 3");
            alert.showAndWait();
        }
        if (Objects.isNull(this.prenom) || this.prenom.getLength()<3  ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie du champ prenom ");
            alert.setContentText("Le champ prenom ne doit pas être vide et il doit être supérieur à 3");
            alert.showAndWait();
        }
        if (this.age.getLength() <2 || Objects.isNull(this.age)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie du champ age");
            alert.setContentText("Le champ age ne doit pas être vide et il doit être supérieur ou égal à 2");
            alert.showAndWait();
        }
        if (Objects.isNull(this.pseudo) || this.pseudo.getLength() <3){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie du champ pseudo");
            alert.setContentText("Le pseudo ne doit pas être vide et il doit être supérieur à 3");
            alert.showAndWait();
        }
        if(Objects.isNull(this.motDePasse) || this.motDePasse.getLength()<3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie du champ mot de passe");
            alert.setContentText("Le mot de passe ne doit pas être vide et il doit être supérieur à 3");
            alert.showAndWait();
        }

        this.controleur.inscription(nom, prenom, age, pseudo, motDePasse);
        this.controleur.creerPartie(this.controleur.getJoueur());
        this.prenom.setText("");
        this.nom.setText("");
        this.age.setText("");
        this.pseudo.setText("");
        this.motDePasse.setText("");
    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.NOUVEAU_JOUEUR, Ordre.OrdreType.CONNEXION, Ordre.OrdreType.REJOINDRE_PARTIE);
    }

    @Override
    public void broadCast(Ordre ordre) {
        switch (ordre.getType()){
            case NOUVEAU_JOUEUR:
                if (BaseMongo.getBase().getJoueurList().contains(BaseMongo.getBase().getJoueur(pseudo.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmination de l'ajout du joueur");
                    alert.setContentText("Le joueur " + this.pseudo.getText() + " a été ajouté! ");
                    alert.showAndWait();
                }
                break;
            case CONNEXION:
                Alert ale = new Alert(Alert.AlertType.CONFIRMATION);
                ale.setTitle("Page connexion");
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

    /*public void joueursComplets(){
        Task<Boolean> attenteJoueur = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (!controleur.partieCommencee() && controleur.getPartie().getParticipants().size()<controleur.getNombreJoueur());
                return true;
            }
        };
        attenteJoueur.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> controleur.rejoindrePartie(this.controleur.getJoueur(),this.controleur.getTicket()));
        Thread thread = new Thread(attenteJoueur);
        thread.start();
    }*/

    public void rejoindre(ActionEvent actionEvent) {
        TextInputDialog token = new TextInputDialog();
        token.setTitle("Ticket d'invitation");
        token.setHeaderText("Entrez votre ticket");

        Optional<String> resultat = token.showAndWait();
        if (resultat.isPresent() && !(Objects.isNull(pseudo)) && !(Objects.isNull(nom)) && !(Objects.isNull(prenom))
                && !(Objects.isNull(age)) && !(Objects.isNull(motDePasse))) {
            this.controleur.creerJoueur(nom.getText(), prenom.getText(), age.getText(), pseudo.getText(), motDePasse.getText());

            this.controleur.rejoindrePartie(this.controleur.getJoueur(), resultat.get());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur saisie ticket et les autres champs");
            alert.setHeaderText("Saisie du ticket et des champs");
            alert.setContentText("Veuillez saisir le ticket et les autres champs (nom,prenom,age, mot de passe et pseudo)!");
            alert.showAndWait();

        }
    }
}
