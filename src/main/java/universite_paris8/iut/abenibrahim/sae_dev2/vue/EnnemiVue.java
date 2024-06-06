package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.abenibrahim.sae_dev2.controleur.getClass()rt universite_paris8.iut.abenibrahim.sae_dev2.modele.Arme;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Direction;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;

public class EnnemiVue {

    private Environnement environnement;
    private Pane paneMap;
    public static String[] framesGauche;
    public static String[] framesDroite;
    public static String[] framesHaut;
    public static String[] framesBas;
    private AnimatedSprite animatedSprite;
    private ImageView ennemiSprite;

    public EnnemiVue(Environnement environnement, Pane paneMap, ImageView v){
        framesGauche = new String[]{getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-gauche1-removebg-preview.png").toExternalForm(), getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-gauche2-removebg-preview.png").toExternalForm()};
        framesDroite = new String[]{getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-droite1-removebg-preview.png").toExternalForm(), getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-droite2-removebg-preview.png").toExternalForm()};
        framesHaut = new String[]{getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-haut1-removebg-preview.png").toExternalForm(), getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-haut2-removebg-preview.png").toExternalForm()};
        framesBas = new String[]{getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-bas1-removebg-preview.png").toExternalForm(), getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-bas2-removebg-preview.png").toExternalForm()};
        this.animatedSprite = new AnimatedSprite(environnement.getEnnemi().getX(), environnement.getEnnemi().getY(), framesDroite, 0);
        this.animatedSprite.setImageView(v);
        this.animatedSprite.setFrameActuel(0);
        this.environnement = environnement;
        this.paneMap = paneMap;

    }
    public void creerSpriteEnnemi(){

        ennemiSprite = this.animatedSprite.getImageView();
        ennemiSprite.translateXProperty().bind(environnement.getEnnemi().XProprety());
        ennemiSprite.translateYProperty().bind(environnement.getEnnemi().YProprety());
    }
}
