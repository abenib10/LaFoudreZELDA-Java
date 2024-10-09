package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.objet.ObjetDefense;

import java.util.ArrayList;
import java.util.List;

public class objetDefVue {
    private List<ObjetDefense> objetDefenses;
    private List<ImageView> defenseImage;
    private Pane paneMap;
    private Environnement environnement;
    private ObjetDefense objetDefense;
    public objetDefVue(Environnement environnement, Pane paneMap) {
        this.environnement = environnement;
        this.paneMap = paneMap;
        this.objetDefenses = new ArrayList<>();
        this.defenseImage = new ArrayList<>();
        this.objetDefense = new ObjetDefense();

    }
    public void ajouterObjetDefenseDansMap() {
        for(ObjetDefense objetDefense : environnement.getObjetDefenseList()) {
            Image image = objetDefense.getImage();
            ImageView imageView = new ImageView(image);
            paneMap.getChildren().add(imageView);
            imageView.setTranslateX(objetDefense.getX()); // Ajuster la position X
            imageView.setTranslateY(objetDefense.getY());
            objetDefenses.add(objetDefense);
            defenseImage.add(imageView);
        }
    }
    public void supprimerObjetDefDeLaCarte(ObjetDefense objetDefense) {
        int index = objetDefenses.indexOf(objetDefense);
        if (index >= 0) {
            ImageView imageView = defenseImage.get(index);
            objetDefenses.remove(objetDefense);
            defenseImage.remove(imageView);
            paneMap.getChildren().remove(imageView);
        }
    }

    public void ajouterObjetDefenseEnv(ObjetDefense objetDefense) {
        this.environnement.getObjetDefenseList().add(objetDefense);
    }
    public void afficherSoinMap(){
        ajouterObjetDefenseEnv(objetDefense);
    }

}
