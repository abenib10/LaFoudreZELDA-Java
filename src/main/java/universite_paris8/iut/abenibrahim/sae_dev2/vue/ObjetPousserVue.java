package universite_paris8.iut.abenibrahim.sae_dev2.vue;


import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Constants;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.acteur.ObjetPousser;

public class ObjetPousserVue {
    private IntegerProperty objXProperty;
    private IntegerProperty objYProperty;
    private Pane paneMap;
    public ObjetPousserVue( IntegerProperty x,IntegerProperty y ,Pane pMap){
        this.objXProperty = x;
        this.objYProperty = y;
        this.paneMap = pMap;
    }
    public void CreerObjetPousserVue(){
        Rectangle rc = new Rectangle();
        rc.setFill(Color.BLUE);
        rc.setHeight(Constants.largeurObj);
        rc.setWidth(Constants.longueurObj);
        this.paneMap.getChildren().add(rc);
        rc.translateXProperty().bind( this.objXProperty);
        rc.translateYProperty().bind(this.objYProperty);


    }


}