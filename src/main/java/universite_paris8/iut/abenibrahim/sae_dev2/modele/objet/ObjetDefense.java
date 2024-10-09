package universite_paris8.iut.abenibrahim.sae_dev2.modele.objet;

import javafx.scene.image.Image;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.ImageObjet;

public class ObjetDefense {
    private int x;
    private int y;
    private Image image;
    private int defDonner;

    public ObjetDefense() {
        this.x = Constante.POSITION_X_OBJETDEF;
        this.y =Constante.POSITION_Y_OBJETDEF;
        this.image = ImageObjet.IMAGE_OBJET_DEF;
        this.defDonner = Constante.POINT_DONNER_AVEC_OBJETDEF;
    }
    // un autre constructeur paramétrer.
    public ObjetDefense(int x, int y,int defDonner) {
        this.x = x;
        this.y = y;
        this.image = ImageObjet.IMAGE_OBJET_DEF;
        this.defDonner = defDonner;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDefDonner() {
        return defDonner;
    }
}
