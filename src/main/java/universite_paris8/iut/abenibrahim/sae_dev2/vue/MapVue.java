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

    @FXML
    private TilePane premierPlanMap;

    public MapVue(Environnement e, TilePane tilePaneMap,TilePane premierPlanMap){
        this.environnement = e;
        this.tilePaneMap = tilePaneMap;
        this.premierPlanMap = premierPlanMap;
    }

    public void remplirMap() {
       // Image murImage = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/obstacleMur.jpeg").toString());
        Image herbeImage = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/Sol.png").toString());
        Image eau = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/eau-removebg-preview.png").toString());

        for (int i = 0; i < this.environnement.getMap().getTab().length; i++) {
            for (int j = 0; j < this.environnement.getMap().getTab()[i].length; j++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                switch (this.environnement.getMap().getTab()[i][j]) {
                 //   case 0 -> imageView.setImage(murImage);
                    case 1 -> imageView.setImage(herbeImage);
                }
                this.tilePaneMap.getChildren().add(imageView);
            }
        }
        for (int i = 0; i < this.environnement.getMap().getTab2().length; i++) {
            for (int j = 0; j < this.environnement.getMap().getTab2()[i].length; j++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                switch (this.environnement.getMap().getTab2()[i][j]) {
                    //   case 0 -> imageView.setImage(murImage);
                    case 1 -> imageView.setImage(eau);
                }
                this.premierPlanMap.getChildren().add(imageView);
            }
        }

    }

}
