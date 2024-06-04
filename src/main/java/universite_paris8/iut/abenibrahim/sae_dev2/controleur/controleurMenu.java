package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class controleurMenu {
    @FXML
    private Button playButton;

    @FXML
    private void handlePlayButtonAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) playButton.getScene().getWindow();
        stage.setScene(scene);
    }
}
