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

    @FXML
    private TilePane murMap;



    public MapVue(Environnement e, TilePane tilePaneMap,TilePane premierPlanMap,TilePane murMap){
        this.environnement = e;
        this.tilePaneMap = tilePaneMap;
        this.premierPlanMap = premierPlanMap;
        this.murMap = murMap;
    }

    public void remplirMap() {
       // Image murImage = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/obstacleMur.jpeg").toString());
        Image herbeImage = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/Sol.png").toString());
        Image sol2 = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/solv2-removebg-preview.png").toString());
        Image Mur = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/mu_r.png").toString());
        Image Mur2 = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/mur_2.png").toString());
        Image arbre = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/arbre-removebg-preview.png").toString());
        Image lum = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/lum-removebg-preview.png").toString());

        for (int i = 0; i < this.environnement.getMap().getTab().length; i++) {
            for (int j = 0; j < this.environnement.getMap().getTab()[i].length; j++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                switch (this.environnement.getMap().getTab()[i][j]) {
                    case 0 -> imageView.setImage(sol2);
                    case 1 -> imageView.setImage(herbeImage);
                }
                this.tilePaneMap.getChildren().add(imageView);
            }
        }
        for (int i = 0; i < this.environnement.getMap().getTab2().length; i++) {
            for (int j = 0; j < this.environnement.getMap().getTab2()[i].length; j++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                switch (this.environnement.getMap().getTab2()[i][j]) {
                    //   case 0 -> imageView.setImage(murImage);
                    case 1 -> imageView.setImage(Mur);
                    case 2 -> imageView.setImage(Mur2);
                    case 3 -> imageView.setImage(arbre);
                    case 4 -> imageView.setImage(sol2);
                    case 5 -> imageView.setImage(lum);
                }
                this.premierPlanMap.getChildren().add(imageView);
            }
        }

    }

}
