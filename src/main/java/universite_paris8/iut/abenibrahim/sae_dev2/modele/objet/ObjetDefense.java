package universite_paris8.iut.abenibrahim.sae_dev2.modele.objet;

import javafx.scene.image.Image;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.ImageObjet;

public class ObjetDefense {
    private int defDonner;

    public ObjetDefense() {
        this.defDonner = Constante.POINT_DONNER_AVEC_OBJETDEF;
    }
    // un autre constructeur paramétrer.
    public ObjetDefense(int defDonner) {
        this.defDonner = defDonner;
    }
    public int getDefDonner() {
        return defDonner;
    }
}
