package vues;

import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import modeles.Joueur;
import modeles.dao.BaseMongo;

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
        String nom = "";
        if (PhareAlexandrie.isPressed()){
            if (PhareAlexandrie.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("Le phare d'Alexandrie").getImage())){
                nom="Le phare d'Alexandrie";
            }
        }else if (PiramideGizeh.isPressed()){
            if (PiramideGizeh.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("La grande piramyde de Gizeh").getImage())){
                nom="La grande piramyde de Gizeh";
            }
        }else if (ColosseDeRhodeus.isPressed()){
            if (ColosseDeRhodeus.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("Le Colosse de Rhodes").getImage())){
                nom="Le Colosse de Rhodes";
            }
        }else if (JardinsSuspendus.isPressed()){
            if (JardinsSuspendus.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("Les jardins suspendus de Babylone").getImage())){
                nom="Les jardins suspendus de Babylone";
            }
        }else if (MausoleeDHalicarnasse.isPressed()){
            if (MausoleeDHalicarnasse.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("Le mausolée d'Halicarnasse").getImage())){
                nom="Le mausolée d'Halicarnasse";
            }
        }else if (StatueDeZeus.isPressed()) {
            if (StatueDeZeus.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("La statue de Zeus à Olympie").getImage())) {
                nom="La statue de Zeus à Olympie";
            }
        }else if (TempleDArtemis.isPressed()) {
            if (TempleDArtemis.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("Le temple d'Artemis à Ephèse").getImage())) {
                nom="Le temple d'Artemis à Ephèse";
            }
        }
        Task<Boolean> attenteChoixPlateau = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                for (Joueur j: controleur.getPartie().getParticipants())
                    if (controleur.choixPlateauFait(j.getPseudo()));
                return true;
            }
        };
        String finalNom = nom;
        attenteChoixPlateau.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e ->controleur.debut(finalNom));
        Thread thread = new Thread(attenteChoixPlateau);
        thread.start();
    }

    @Override
    public void setAbonnements(LanceurOrdre controleur) {
        controleur.abonnement(this, Ordre.OrdreType.JOUER_PARTIE, Ordre.OrdreType.NOUVEAU_PLATEAU);
    }

    @Override
    public void broadCast(Ordre ordre) {
        switch (ordre.getType()){
            case NOUVEAU_PLATEAU:
                if (BaseMongo.getBase().getPlateauList().contains(BaseMongo.getBase().getPlateauNom(this.controleur.getNomPlateau()))){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Choix du plateau effectué");
                    alert.setContentText("Le joueur " + this.controleur.getJoueur().getPseudo()+" a choisi le plateau "+this.controleur.getNomPlateau()+"!");
                    alert.showAndWait();
                }
            case JOUER_PARTIE:
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Debut partie");
                alert.setContentText("Les joueurs peuvent commencer la partie!");
                alert.showAndWait();
        }
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;
    }
}
