package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;

public class PvVue {
    private StackPane pvStackPane;
    private Pane paneMap;
    private int pv;
    private Image vie5Image;
    private Image vie4Image;
    private Image vie3Image;
    private Image vie2Image;
    private Image vie1Image;
    private Image vie0Image;
    private ImageView pvImageView;
    public PvVue(int pv, Pane paneMap) {
        this.pv = pv;
        this.paneMap = paneMap;
        pvStackPane = new StackPane();
        this.pvImageView = new ImageView();
        pvStackPane.getChildren().add(pvImageView);
        pvStackPane.setLayoutX(10);
        pvStackPane.setLayoutY(10);
        paneMap.getChildren().add(pvStackPane);
        vie5Image = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/vie5-removebg-preview.png").toString());
        vie4Image = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/vie4-removebg-preview.png").toString());
        vie3Image = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/vie3-removebg-preview.png").toString());
        vie2Image = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/vie2-removebg-preview.png").toString());
        vie1Image = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/vie1-removebg-preview.png").toString());
        vie0Image = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/vie0-removebg-preview.png").toString());
    }

    public void updatePvJoueurImage() {
        pvImageView.setFitHeight(50);
        pvImageView.setFitWidth(150);
        if (this.pv > 110) {
            pvImageView.setImage(vie5Image);
        } else if (this.pv > 80) {
            pvImageView.setImage(vie4Image);
        } else if (this.pv > 60) {
            pvImageView.setImage(vie3Image);
        } else if (this.pv > 30) {
            pvImageView.setImage(vie2Image);
        } else if (this.pv > 15) {
            pvImageView.setImage(vie1Image);
        } else {
            pvImageView.setImage(vie0Image);
        }
    }

    public Node getPvStackPane() {
        return this.pvStackPane;
    }
}
