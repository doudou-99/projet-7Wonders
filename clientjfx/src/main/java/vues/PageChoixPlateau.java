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
        if (PhareAlexandrie.isPressed()){
            if (PhareAlexandrie.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("Le phare d'Alexandrie").getImage())){
                this.controleur.debut("Le phare d'Alexandrie");
            }
        }else if (PiramideGizeh.isPressed()){
            if (PiramideGizeh.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("La grande piramyde de Gizeh").getImage())){
                this.controleur.debut("La grande piramyde de Gizeh");
            }
        }else if (ColosseDeRhodeus.isPressed()){
            if (ColosseDeRhodeus.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("Le Colosse de Rhodes").getImage())){
                this.controleur.debut("Le Colosse de Rhodes");
            }
        }else if (JardinsSuspendus.isPressed()){
            if (JardinsSuspendus.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("Les jardins suspendus de Babylone").getImage())){
                this.controleur.debut("Les jardins suspendus de Babylone");
            }
        }else if (MausoleeDHalicarnasse.isPressed()){
            if (MausoleeDHalicarnasse.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("Le mausolée d'Halicarnasse").getImage())){
                this.controleur.debut("Le mausolée d'Halicarnasse");
            }
        }else if (StatueDeZeus.isPressed()) {
            if (StatueDeZeus.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("La statue de Zeus à Olympie").getImage())) {
                this.controleur.debut("La statue de Zeus à Olympie");
            }
        }else if (TempleDArtemis.isPressed()) {
            if (TempleDArtemis.getImage().getUrl().split("/")[4].equals(BaseMongo.getBase().getPlateauNom("Le temple d'Artemis à Ephèse").getImage())) {
                this.controleur.debut("Le temple d'Artemis à Ephèse");
            }
        }

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
