package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;

public class EnnemiVue {

    private Environnement environnement;
    private Pane paneMap;

    public EnnemiVue(Environnement environnement, Pane paneMap){
        this.environnement = environnement;
        this.paneMap = paneMap;
    }
    public void creerSpriteEnnemi(){
        Circle a = new Circle(10);
        a.setFill(Color.BLUEVIOLET);
        this.paneMap.getChildren().add(a);
        a.translateXProperty().bind(environnement.getEnnemi().XProprety());
        a.translateYProperty().bind(environnement.getEnnemi().YProprety());
    }
}
