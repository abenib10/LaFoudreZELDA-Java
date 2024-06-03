package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Arme {
    protected int attaque;
    private int x;
    private int y;
    private Image image;
    private static int id = 0;

    public Arme(int a){
        this.attaque=a;
        this.image = new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/epée-removebg-preview.png");
        this.x=350;
        this.y=350;
    }
    public int getPointAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public int getId() {
        return id;
    }




}
