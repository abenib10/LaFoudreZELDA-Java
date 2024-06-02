package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class inventaireObjet {
    private Arme arme;
    private Image image;
    public inventaireObjet(Image image,Arme arme) {
        this.arme = arme;
        this.image = image;
    }
    public Image getImage() {
        return image;
    }
}
