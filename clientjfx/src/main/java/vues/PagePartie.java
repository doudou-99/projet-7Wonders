package vues;

import controleur.Controleur;
import controleur.ordre.EcouteurOrdre;
import controleur.ordre.LanceurOrdre;
import controleur.ordre.Ordre;
import javafx.scene.layout.BorderPane;

public class PagePartie implements EcouteurOrdre, VueInteractive {
    public BorderPane pane;

    @Override
    public void setAbonnements(LanceurOrdre controleur) {

    }

    @Override
    public void broadCast(Ordre ordre) {

    }

    @Override
    public void setControleur(Controleur controleur) {

    }
}
