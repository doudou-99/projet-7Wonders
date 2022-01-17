package application;

import controleur.Controleur;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

public class App extends Application {
    //MediaPlayer mediaPlayer;
    @Override
    public void start(Stage stage) {
        Controleur controleur = new Controleur(stage);
        controleur.run();
        //Media media= new Media(new File("clientjfx/src/main/resources/vues/music/akatsuki.mp3").getAbsolutePath());
        //mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.setAutoPlay(true);
    }

    public static void main(String[] args) {
        launch();

    }
}
