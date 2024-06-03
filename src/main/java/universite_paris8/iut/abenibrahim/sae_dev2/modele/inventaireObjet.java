package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class inventaireObjet {
    private Arme arme;
    private Image image;
    private int id = 0;
    public inventaireObjet(Image image,Arme arme) {
        this.arme = arme;
        this.image = image;
    }
    public Image getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Arme getArme() {
        return arme;
    }
}
