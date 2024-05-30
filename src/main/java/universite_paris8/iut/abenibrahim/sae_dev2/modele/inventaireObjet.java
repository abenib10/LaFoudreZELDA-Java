package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.scene.image.Image;

public class inventaireObjet {
    private Image image;

    private Arme arme;

    public inventaireObjet(Image image,Arme arme) {
        this.image = image;
        this.arme = arme;
    }

    public Image getImage() {
        return image;
    }
}
