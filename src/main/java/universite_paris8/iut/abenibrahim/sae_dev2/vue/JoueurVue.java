package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.abenibrahim.sae_dev2.controleur.Controleur;
import universite_paris8.iut.abenibrahim.sae_dev2.controleur.ControleurTouche;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;

public class JoueurVue {

    private Environnement environnement;
    private ImageView gutsSprite;
    private Pane paneMap;

    public static String[] framesGauche;
    public static String[] framesDroite;
    public static String[] framesHaut;
    public static String[] framesBas;


    public JoueurVue(Environnement environnement, Pane paneMap) {
        this.environnement = environnement;
        this.paneMap = paneMap;
        framesGauche = new String[]{ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_left_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_left_2.png").toExternalForm()};
        framesDroite = new String[]{ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_2.png").toExternalForm()};
        framesHaut = new String[]{ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_up_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_up_2.png").toExternalForm()};
        framesBas = new String[]{ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_down_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_down_2.png").toExternalForm()};
    }

    public void creerSpriteJoueur(Controleur c) {
        gutsSprite = c.getGutsSprite();
        ControleurTouche deplacementFleche = new ControleurTouche(this.environnement, gutsSprite);
        deplacementFleche.Actualiser(c);
        paneMap.addEventHandler(KeyEvent.KEY_PRESSED, deplacementFleche);
        gutsSprite = deplacementFleche.getAnimatedSprite().getImageView();
        gutsSprite.translateXProperty().bind(this.environnement.getGuts().XProprety());
        gutsSprite.translateYProperty().bind(this.environnement.getGuts().YProprety());
    }
}