package application;

import controleur.Controleur;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class App extends Application {
    MediaPlayer mediaPlayer;
    @Override
    public void start(Stage stage) {
        Controleur controleur = new Controleur(stage);
        controleur.run();
        Media media = new Media("music/akatsuki.mp3");
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }
    public static void main(String[] args) {
        launch();

    }
}
