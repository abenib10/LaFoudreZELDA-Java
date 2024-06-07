package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.beans.property.IntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Direction;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;

public class EnnemiVue {
    private Pane paneMap;
    public static String[] framesGauche;
    public static String[] framesDroite;
    public static String[] framesHaut;
    public static String[] framesBas;
    private AnimatedSprite animatedSprite;
    private ImageView ennemiSprite;
    private IntegerProperty ennemiXProperty;
    private IntegerProperty ennemiYProperty;

    public EnnemiVue(int ennemiX, int ennemiY, IntegerProperty ennemiXProperty, IntegerProperty ennemiYProperty, Pane paneMap, ImageView v){
        framesGauche = new String[]{getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-gauche1-removebg-preview.png").toExternalForm(), getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-gauche2-removebg-preview.png").toExternalForm()};
        framesDroite = new String[]{getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-droite1-removebg-preview.png").toExternalForm(), getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-droite2-removebg-preview.png").toExternalForm()};
        framesHaut = new String[]{getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-haut1-removebg-preview.png").toExternalForm(), getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-haut2-removebg-preview.png").toExternalForm()};
        framesBas = new String[]{getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-bas1-removebg-preview.png").toExternalForm(), getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-bas2-removebg-preview.png").toExternalForm()};
        this.animatedSprite = new AnimatedSprite(ennemiX, ennemiY, framesDroite, 0);
        this.animatedSprite.setImageView(v);
        this.animatedSprite.setFrameActuel(0);
        this.paneMap = paneMap;
        this.ennemiXProperty = ennemiXProperty;
        this.ennemiYProperty = ennemiYProperty;
    }


    public void creerSpriteEnnemi(){
        ennemiSprite = this.animatedSprite.getImageView();
        ennemiSprite.translateXProperty().bind(this.ennemiXProperty);
        ennemiSprite.translateYProperty().bind(this.ennemiYProperty);
    }

    public void mettreAJourFramesEnnemi(Direction direction) {
        switch (direction) {
            case OUEST -> animatedSprite.definirFrames(framesGauche);
            case EST -> animatedSprite.definirFrames(framesDroite);
            case NORD -> animatedSprite.definirFrames(framesHaut);
            case SUD -> animatedSprite.definirFrames(framesBas);
        }
    }

}
