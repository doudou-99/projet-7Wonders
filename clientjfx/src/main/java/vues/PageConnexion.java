package vues;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

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
        ticket.setText(this.controleur.getTicket());
        if (Objects.isNull(ticket)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Le champ ticket est vide");
            alert.setContentText("Il faut remplir le champ ticket!");
            alert.showAndWait();
        }
        Task<Boolean> attenteJoueur = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (!controleur.partieCommencee());
                return true;
            }
        };
        attenteJoueur.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> controleur.rejoindrePartie(this.controleur.getJoueur(),this.ticket.getText()));
        Thread thread = new Thread(attenteJoueur);
        thread.start();
    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.REJOINDRE_PARTIE, Ordre.OrdreType.CHOIX_PLATEAU);
    }

    @Override
    public void broadCast(Ordre ordre) {
        switch (ordre.getType()){
            case REJOINDRE_PARTIE:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Validation ajout joueur à la partie");
                alert.setContentText(" Ajout joueur");
                alert.setHeaderText("Page de ajout de joueurs à la partie");
                alert.showAndWait();
                break;

            case CHOIX_PLATEAU:
                if (controleur.getPartie(this.controleur.getJoueur().getPseudo()).getParticipants().size()==this.controleur.getNombreJoueur()){
                    Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
                    aler.setTitle("Aller à la page de choix de plateau");
                    aler.setContentText("Page de choix de plateau");
                    aler.setHeaderText("Page de choix de plateau pour le joueur");
                    aler.showAndWait();
                }
                break;
        }
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;
    }
}
