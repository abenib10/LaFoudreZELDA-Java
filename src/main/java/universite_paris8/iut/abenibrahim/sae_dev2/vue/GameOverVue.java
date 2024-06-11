package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class GameOverVue extends VBox {
    private StackPane pvStackPane;
    private ImageView imageView

    public GameOverVue(Pane paneMap) {
        pvStackPane = new StackPane();
        pvStackPane.getChildren().add(imageView);
        pvStackPane.setLayoutX(10);
        pvStackPane.setLayoutY(10);
        paneMap.getChildren().add(pvStackPane);
        Image gameOverImage = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/gameover.png").toExternalForm());
        ImageView imageView = new ImageView(gameOverImage);
        imageView.setPreserveRatio(true);

        getChildren().addAll(imageView);
        setAlignment(Pos.CENTER);
    }

    public Node getPvStackPane() {
        return this.pvStackPane;
    }

}
