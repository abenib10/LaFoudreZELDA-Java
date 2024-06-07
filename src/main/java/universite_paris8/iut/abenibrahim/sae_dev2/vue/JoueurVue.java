package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.abenibrahim.sae_dev2.controleur.Controleur;
import universite_paris8.iut.abenibrahim.sae_dev2.controleur.ControleurTouche;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Direction;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Joueur;

public class JoueurVue {

    private Joueur joueur;
    private ImageView gutsSprite;
    private Pane paneMap;
    private InventaireVue inventaireVue;

    public static String[] framesGauche;
    public static String[] framesDroite;
    public static String[] framesHaut;
    public static String[] framesBas;



    public JoueurVue(Joueur joueur, Pane paneMap, InventaireVue inventaireVue) {
        this.joueur = joueur;
        this.paneMap = paneMap;
        this.inventaireVue = inventaireVue;
        framesGauche = new String[]{ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_left_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_left_2.png").toExternalForm()};
        framesDroite = new String[]{ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_2.png").toExternalForm()};
        framesHaut = new String[]{ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_up_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_up_2.png").toExternalForm()};
        framesBas = new String[]{ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_down_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_down_2.png").toExternalForm()};
    }

    public void creerSpriteJoueur(Controleur c) {
        gutsSprite = c.getGutsSprite();
        ControleurTouche deplacementFleche = new ControleurTouche(this.joueur, gutsSprite, inventaireVue);
        deplacementFleche.Actualiser(c);
        Scene scene = paneMap.getScene();
        if (scene != null) {
            scene.addEventHandler(KeyEvent.KEY_PRESSED, deplacementFleche);
        } else {
            paneMap.sceneProperty().addListener((obs, oldScene, newScene) -> {
                if (newScene != null) {
                    newScene.addEventHandler(KeyEvent.KEY_PRESSED, deplacementFleche);
                }
            });
        }
        gutsSprite = deplacementFleche.getAnimatedSprite().getImageView();
        gutsSprite.translateXProperty().bind(this.joueur.XProprety());
        gutsSprite.translateYProperty().bind(this.joueur.YProprety());
    }

    public void mettreAJourFramesJoueur(Direction direction) {
        AnimatedSprite animatedSprite = new AnimatedSprite(joueur.getX(), joueur.getY(), framesDroite, 0);
        switch (direction) {
            case OUEST -> animatedSprite.definirFrames(framesGauche);
            case EST -> animatedSprite.definirFrames(framesDroite);
            case NORD -> animatedSprite.definirFrames(framesHaut);
            case SUD -> animatedSprite.definirFrames(framesBas);
        }
        gutsSprite.setImage(animatedSprite.getImageView().getImage());
    }

}