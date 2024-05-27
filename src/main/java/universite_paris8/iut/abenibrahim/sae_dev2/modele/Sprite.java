package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    protected ImageView imageView;
    protected double largeur;
    protected double hauteur;
    protected boolean visible;

    public Sprite(double x, double y, String cheminImage) {
        this.imageView = new ImageView(new Image(cheminImage));
        this.imageView.setX(x);
        this.imageView.setY(y);
        this.largeur = imageView.getImage().getWidth();
        this.hauteur = imageView.getImage().getHeight();
        this.visible = true;
        
    }

    public ImageView getVueImage() {
        return imageView;
    }

    public double getX() {
        return imageView.getX();
    }

    public double getY() {
        return imageView.getY();
    }

    public void setX(double x) {
        imageView.setX(x);
    }

    public void setY(double y) {
        imageView.setY(y);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        imageView.setVisible(visible);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D(getX(), getY(), largeur, hauteur);
    }
}
