package vues;

import controleur.Controleur;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeles.Joueur;
import modeles.dao.BaseMongo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Objects;


public class PageChoixPlateau implements VueInteractive {
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
    public VBox vbox;

    private Scene scene;
    private Controleur controleur;
    private Stage stage;

    public void initialisation(){
        this.scene=new Scene(this.borderpane);
    }

    public Scene getScene() {
        return scene;
    }

    public static PageChoixPlateau creer(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(PageChoixPlateau.class.getResource("pageChoixPlateau.fxml"));
        try{
            fxmlLoader.load();
            PageChoixPlateau vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.initialisation();

            return vue;

        } catch (IOException e) {
            throw new RuntimeException("Erreur chargement choix plateau");
        }
    }

    public void chargerDonnees(){
        Task<Boolean> attenteChoixPlateau = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (!controleur.choixPlateauFait(controleur.getJoueur().getPseudo()));
                return true;
            }
        };

        attenteChoixPlateau.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e ->controleur.goToPartie());
        Thread thread = new Thread(attenteChoixPlateau);
        thread.start();
    }

    public void choix(MouseEvent mouseEvent) {
        String colosse = ColosseDeRhodeus.getImage().getUrl();
        System.out.println();
        if (Objects.isNull(controleur.getJoueur().getMerveilles()) || controleur.getJoueur().getMerveilles().isEmpty()) {
            if (mouseEvent.getClickCount()==1) {
                if (colosse.contains(BaseMongo.getBase().getPlateauNom("Le Colosse de Rhodes").getImage())) {
                    String nom = "Le Colosse de Rhodes";
                    System.out.println(nom);
                    ColosseDeRhodeus.setVisible(false);
                    controleur.setUrl(colosse);
                    controleur.debut(nom);

                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur choix plateau");
            alert.setContentText("Vous avez déjà choisi votre merveille!");
            alert.showAndWait();
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void choixMausolee(MouseEvent mouseEvent) {
        String mausolee = MausoleeDHalicarnasse.getImage().getUrl();
        if(Objects.isNull(controleur.getJoueur().getMerveilles()) || controleur.getJoueur().getMerveilles().isEmpty()) {
            if (mouseEvent.getClickCount()==1) {
                if (mausolee.contains(BaseMongo.getBase().getPlateauNom("Le mausolée d'Halicarnasse").getImage())) {
                    String nom = "Le mausolée d'Halicarnasse";
                    System.out.println(nom);
                    MausoleeDHalicarnasse.setVisible(false);
                    controleur.setUrl(mausolee);
                    controleur.debut(nom);

                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur choix plateau");
            alert.setContentText("Vous avez déjà choisi votre merveille!");
            alert.showAndWait();
        }
    }

    public void choixStatue(MouseEvent mouseEvent) {
        String statue = StatueDeZeus.getImage().getUrl();
        if(Objects.isNull(controleur.getJoueur().getMerveilles()) || controleur.getJoueur().getMerveilles().isEmpty()) {
            if (mouseEvent.getClickCount()==1) {
                if (statue.contains(BaseMongo.getBase().getPlateauNom("La statue de Zeus à Olympie").getImage())) {
                    String nom ="La statue de Zeus à Olympie";
                    System.out.println(nom);
                    StatueDeZeus.setVisible(false);
                    controleur.setUrl(statue);
                    controleur.debut(nom);

                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur choix plateau");
            alert.setContentText("Vous avez déjà choisi votre merveille!");
            alert.showAndWait();
        }
    }

    public void choixTemple(MouseEvent mouseEvent) {
        String temple = TempleDArtemis.getImage().getUrl();
        if(Objects.isNull(controleur.getJoueur().getMerveilles()) || controleur.getJoueur().getMerveilles().isEmpty()) {
            if (mouseEvent.getClickCount()==1) {
                if (temple.contains(BaseMongo.getBase().getPlateauNom("Le temple d'Artemis à Ephèse").getImage())) {
                    String nom = "Le temple d'Artemis à Ephèse";
                    System.out.println(nom);
                    TempleDArtemis.setVisible(false);
                    controleur.setUrl(temple);
                    controleur.debut(nom);

                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur choix plateau");
            alert.setContentText("Vous avez déjà choisi votre merveille!");
            alert.showAndWait();
        }
    }

    public void choixJardins(MouseEvent mouseEvent) {
        String jardins = JardinsSuspendus.getImage().getUrl();
        if(Objects.isNull(controleur.getJoueur().getMerveilles()) || controleur.getJoueur().getMerveilles().isEmpty()) {
            if (mouseEvent.getClickCount()==1) {
                if (jardins.contains(BaseMongo.getBase().getPlateauNom("Les jardins suspendus de Babylone").getImage())) {
                    String nom = "Les jardins suspendus de Babylone";
                    System.out.println(nom);
                    JardinsSuspendus.setVisible(false);
                    controleur.setUrl(jardins);
                    controleur.debut(nom);

                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur choix plateau");
            alert.setContentText("Vous avez déjà choisi votre merveille!");
            alert.showAndWait();
        }
    }

    public void choixPhare(MouseEvent mouseEvent) {
        String phare=PhareAlexandrie.getImage().getUrl();
        if(Objects.isNull(controleur.getJoueur().getMerveilles()) || controleur.getJoueur().getMerveilles().isEmpty()) {
            if (mouseEvent.getClickCount()==1) {
                if (phare.contains(BaseMongo.getBase().getPlateauNom("Le phare d'Alexandrie").getImage())) {
                    String nom = "Le phare d'Alexandrie";
                    System.out.println(nom);
                    PhareAlexandrie.setVisible(false);
                    controleur.setUrl(phare);
                    controleur.debut(nom);

                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur choix plateau");
            alert.setContentText("Vous avez déjà choisi votre merveille!");
            alert.showAndWait();
        }
    }

    public void choixPiramide(MouseEvent mouseEvent) {
        String piramide=PiramideGizeh.getImage().getUrl();
        if(Objects.isNull(controleur.getJoueur().getMerveilles()) || controleur.getJoueur().getMerveilles().isEmpty()) {
                if (mouseEvent.getClickCount()==1){
                    if (piramide.contains(BaseMongo.getBase().getPlateauNom("La grande pyramide de Gizeh").getImage())){
                        String nom="La grande pyramide de Gizeh";
                        System.out.println(nom);
                        PiramideGizeh.setVisible(false);
                        controleur.setUrl(piramide);
                        controleur.debut(nom);

                    }
                }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur choix plateau");
            alert.setContentText("Vous avez déjà choisi votre merveille!");
            alert.showAndWait();
        }

    }
}
