package vues;

import controleur.Controleur;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modeles.dao.BaseMongo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

    public void choix(MouseEvent mouseEvent) {
        String nom = "";
        //new File(getClass().getResource(String.valueOf(ColosseDeRhodeus.getImage().getUrl())).getFile()).getName();
        String colosse= new File(getClass().getResource(String.valueOf(ColosseDeRhodeus.getImage().getUrl())).getFile().replaceAll("/","//")).getName();
        String phare = new File(getClass().getResource(String.valueOf(PhareAlexandrie.getImage().getUrl())).getFile().replaceAll("/","//")).getName();
        String piramide= new File(getClass().getResource(String.valueOf(PiramideGizeh.getImage().getUrl())).getFile().replaceAll("/","//")).getName();
        String statue = new File(getClass().getResource(String.valueOf(StatueDeZeus.getImage().getUrl())).getFile().replaceAll("/","//")).getName();
        String mausolee = new File(getClass().getResource(String.valueOf(MausoleeDHalicarnasse.getImage().getUrl())).getFile().replaceAll("/","//")).getName();
        String jardins = new File(getClass().getResource(String.valueOf(JardinsSuspendus.getImage().getUrl())).getFile().replaceAll("/","//")).getName();
        String temple = new File(getClass().getResource(String.valueOf(TempleDArtemis.getImage().getUrl())).getFile().replaceAll("/","//")).getName();
        if (PhareAlexandrie.isPressed()){
            if (phare.equals(BaseMongo.getBase().getPlateauNom("Le phare d'Alexandrie").getImage())){
                nom="Le phare d'Alexandrie";
                PhareAlexandrie.setImage(null);
            }
        }else if (PiramideGizeh.isPressed()){
            if (piramide.equals(BaseMongo.getBase().getPlateauNom("La grande piramyde de Gizeh").getImage())){
                nom="La grande piramyde de Gizeh";
                PiramideGizeh.setImage(null);
            }
        }else if (ColosseDeRhodeus.isPressed()){
            if (colosse.equals(BaseMongo.getBase().getPlateauNom("Le Colosse de Rhodes").getImage())){
                nom="Le Colosse de Rhodes";
                ColosseDeRhodeus.setImage(null);
            }
        }else if (JardinsSuspendus.isPressed()){
            if (jardins.equals(BaseMongo.getBase().getPlateauNom("Les jardins suspendus de Babylone").getImage())){
                nom="Les jardins suspendus de Babylone";
                JardinsSuspendus.setImage(null);
            }
        }else if (MausoleeDHalicarnasse.isPressed()){
            if (mausolee.equals(BaseMongo.getBase().getPlateauNom("Le mausolée d'Halicarnasse").getImage())){
                nom="Le mausolée d'Halicarnasse";
                MausoleeDHalicarnasse.setImage(null);
            }
        }else if (StatueDeZeus.isPressed()) {
            if (statue.equals(BaseMongo.getBase().getPlateauNom("La statue de Zeus à Olympie").getImage())) {
                nom="La statue de Zeus à Olympie";
                StatueDeZeus.setImage(null);
            }
        }else if (TempleDArtemis.isPressed()) {
            if (temple.equals(BaseMongo.getBase().getPlateauNom("Le temple d'Artemis à Ephèse").getImage())) {
                nom="Le temple d'Artemis à Ephèse";
                TempleDArtemis.setImage(null);
            }
        }
        Task<Boolean> attenteChoixPlateau = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                while (!controleur.choixPlateauFait(controleur.getJoueur().getPseudo()));
                return true;
            }
        };
        String finalNom = nom;
        attenteChoixPlateau.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e ->controleur.debut(finalNom));
        Thread thread = new Thread(attenteChoixPlateau);
        thread.start();
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
}
