package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.EnnemiVue;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.JoueurVue;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.MapVue;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.PvVue;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private Pane paneMap;
    @FXML
    private TilePane tilePaneMap;
    private Environnement environnement;
    private Timeline gameLoop;
    private int temps;
    static private ImageView gutsSprite;
    private MapVue mapVue;
    private PvVue pvVue;
    private JoueurVue joueurVue;
    private EnnemiVue ennemiVue;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        tilePaneMap.setPrefTileWidth(50);
        tilePaneMap.setPrefTileHeight(50);

        this.environnement=new Environnement();

        this.mapVue = new MapVue(this.environnement, tilePaneMap);
        this.mapVue.remplirMap();

        this.pvVue = new PvVue(this.environnement, this.paneMap);
        environnement.getGuts().pvProperty().addListener((obs, oldValue, newValue) -> pvVue.updatePvJoueurImage());

        this.joueurVue = new JoueurVue(this.environnement, this.paneMap);
        initialiserGuts();
        joueurVue.creerSpriteJoueur(this);

        this.ennemiVue = new EnnemiVue(this.environnement, this.paneMap);
        this.ennemiVue.creerSpriteEnnemi();

        initAnimation();
        gameLoop.play();



    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==10000){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if (temps%30==0){
                        System.out.println("un tour");
                            environnement.getEnnemi().suivreJoueur();
                            environnement.getEnnemi().attaquer();
                            System.out.println(this.environnement.getGuts().getPv());
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public void initialiserGuts(){
        Image g = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_1.png").toExternalForm());
        gutsSprite = new ImageView(g);
        gutsSprite.setFitHeight(50);
        gutsSprite.setFitWidth(50);
        paneMap.getChildren().add(gutsSprite);
    }

    static public void setGSprite(Image i){
        Controleur.gutsSprite.setImage(i);
    }

    public ImageView getGutsSprite() {
        return gutsSprite;
    }
}