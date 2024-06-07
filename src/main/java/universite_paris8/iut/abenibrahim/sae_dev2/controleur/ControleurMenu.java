package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.io.IOException;
import java.nio.file.Paths;


public class ControleurMenu {
    public VBox vbox;
    @FXML
    private Button playButton;
    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    public void initialize() {
        String audioFile = "src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/son.mp3";
        String audio = "src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/son2.mp3";// Chemin relatif vers le fichier audio
        String absolutePath = Paths.get(audioFile).toAbsolutePath().toUri().toString();
        String absolutePath2 = Paths.get(audio).toAbsolutePath().toUri().toString();
        Media media = new Media(absolutePath);
        Media media2 = new Media(absolutePath2);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer2 = new MediaPlayer(media2);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer2.setVolume(0.3);
    }
    @FXML
    private void handlePlayButtonAction() throws IOException {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
       vbox.setVisible(false);


        String videoFile = "src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/video1.mp4";
        String videoPath = Paths.get(videoFile).toAbsolutePath().toUri().toString();
        Media videoMedia = new Media(videoPath);
        MediaPlayer videoMediaPlayer = new MediaPlayer(videoMedia);
        mediaView.setMediaPlayer(videoMediaPlayer);
        videoMediaPlayer.setVolume(0.5);
        // Définir le début et la fin de la partie de la vidéo que vous souhaitez lire
        Duration startTime = Duration.seconds(63);  // Commence à 10 secondes
        Duration endTime = Duration.seconds(101);    // Se termine à 30 secondes
        videoMediaPlayer.setStartTime(startTime);
        videoMediaPlayer.setStopTime(endTime);

        videoMediaPlayer.play();
        videoMediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer2.play();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/hello-view.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) playButton.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}