package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.scene.image.Image;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.objet.CheminImage;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class ImageObjet {
    public static final Image IMAGE_ARME = new Image(getResource(CheminImage.CHEMIN_IMAGE_ARME).toString());

    public static final Image IMAGE_SOIN = new Image(getResource(CheminImage.CHEMIN_IMAGE_SOIN).toString());

    public static final Image IMAGE_OBJET_DEF = new Image(getResource(CheminImage.CHEMIN_IMAGE_OBJET_DEF).toString());
}