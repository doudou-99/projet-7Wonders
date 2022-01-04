package application;

import controleur.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;
import modeles.facade.FacadeWondersImpl;
import modeles.interfaces.FacadeWonders;
import vues.GestionnaireVue;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        GestionnaireVue gestionnaireVue = new GestionnaireVue(stage);
        FacadeWonders facadeParis = new FacadeWondersImpl();
        Controleur controleur = new Controleur(facadeParis, gestionnaireVue);
        controleur.run();
    }

    public static void main(String[] args) {
        launch();

    }
}
