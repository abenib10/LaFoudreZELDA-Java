package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;

public class MapVue {
    private Environnement environnement;
    @FXML
    private TilePane tilePaneMap;

    public MapVue(Environnement e, TilePane tilePaneMap){
        this.environnement = e;
        this.tilePaneMap = tilePaneMap;
    }

    public void remplirMap() {
       // Image murImage = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/obstacleMur.jpeg").toString());
        Image herbeImage = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/Sol.png").toString());
        Image eau = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/eau.png").toString());

        for (int i = 0; i < this.environnement.getMap().getTab().length; i++) {
            for (int j = 0; j < this.environnement.getMap().getTab()[i].length; j++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                switch (this.environnement.getMap().getTab()[i][j]) {
                 //   case 0 -> imageView.setImage(murImage);
                    case 1 -> imageView.setImage(herbeImage);
                    case 2 -> imageView.setImage(eau);
                }
                this.tilePaneMap.getChildren().add(imageView);
            }
        }
    }

}
