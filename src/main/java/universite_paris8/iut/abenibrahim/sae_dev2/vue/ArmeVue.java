package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.objet.Arme;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.objet.Constante;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.objet.ObjetDefense;

import java.util.ArrayList;
import java.util.List;

public class ArmeVue {
    private final Arme arme;
    private final ImageView imageView;

    public ArmeVue(Pane paneMap,Arme arme) {
        this.arme = arme;
        this.imageView = new ImageView(ImageObjet.IMAGE_ARME); // Charger l'image depuis ImageObjet
        paneMap.getChildren().add(imageView); // Ajouter l'ImageView au Pane
        updatePosition();
    }

    public void updatePosition() {
        // Mettez à jour la position de l'image ici en fonction de la position de l'arme
        imageView.setTranslateX(Constante.POSITION_X_ARME); // Ajustez cela si vous ajoutez une méthode getX() dans Arme
        imageView.setTranslateY(Constante.POSITION_Y_ARME); // Ajustez cela si vous ajoutez une méthode getY() dans Arme
    }

    public ImageView getImageView() {
        return imageView;
    }


}
