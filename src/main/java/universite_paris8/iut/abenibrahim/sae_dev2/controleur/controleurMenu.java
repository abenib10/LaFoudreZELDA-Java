package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class controleurMenu {
    @FXML
    private Button playButton;
    private MediaPlayer mediaPlayer;
    public void initialize() {
        String audioFile = "src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/son.mp3"; // Chemin relatif vers le fichier audio
        String absolutePath = Paths.get(audioFile).toAbsolutePath().toUri().toString();
            Media media = new Media(absolutePath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(0.5); // Volume à 50%
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Boucle infinie
            mediaPlayer.play();
    }
    @FXML
    private void handlePlayButtonAction() throws IOException {
        if (mediaPlayer != null) {
            mediaPlayer.stop(); // Arrêter la lecture du son
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.setScene(scene);
    }
}
