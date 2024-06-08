package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.abenibrahim.sae_dev2.Main;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Direction;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.SaveData;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.io.*;


public class Controleur implements Initializable {
    @FXML
    private Pane paneMap;
    private static final double ZOOM_FACTOR = 1.2;
    @FXML
    private TilePane tilePaneMap;
    private Environnement environnement;
    @FXML
    private TilePane premierPlanMap;

    @FXML
    private TilePane murMap;

    @FXML
    private TilePane inventairePane;
    @FXML
    private HBox slot1;
    @FXML
    private Label titre;

    @FXML
    private HBox slot2;

    @FXML
    private Label armeChoisie;
    @FXML
    private Label phrase;

    public void saveGame() {
        try {
            SaveData saveData = new SaveData(environnement);
            FileOutputStream fos = new FileOutputStream("savegame.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(saveData);
            oos.close();
            System.out.println("Partie sauvegardée avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Timeline gameLoop;
    private int temps;
    static private ImageView gutsSprite;
    private MapVue mapVue;
    private PvVue pvVue;
    private JoueurVue joueurVue;
    private EnnemiVue ennemiVue;
    private InventaireVue inventaireVue;

    private List<ImageView> armeImages = new ArrayList<>();
    private List<HBox> slots;
    private ImageView ennemiSprite;
    private AnimatedEnnemiSprite animationTimer;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        paneMap.setScaleX(ZOOM_FACTOR);
        paneMap.setScaleY(ZOOM_FACTOR);

        tilePaneMap.setPrefTileWidth(50);
        tilePaneMap.setPrefTileHeight(50);

        this.environnement=new Environnement();
        Environnement env = Main.getEnvironnement();
        if (env == null) {
            this.environnement = new Environnement();
        } else {
            this.environnement = env;
        }

        this.mapVue = new MapVue(this.environnement.getMap().getTab(),this.environnement.getMap().getTab2(), tilePaneMap, premierPlanMap);
        this.mapVue.remplirMap();

        this.pvVue = new PvVue(this.paneMap);
        environnement.getGuts().pvProperty().addListener((obs, oldValue, newValue) -> pvVue.updatePvJoueurImage(this.environnement.getGuts().getPv()));

        initialiserGuts();
        initialiserEnnemi();

        slots = Arrays.asList(slot1, slot2);
        this.inventaireVue = new InventaireVue(this.paneMap, this.tilePaneMap, this.environnement, inventairePane, slot1, slot2, titre, armeChoisie, phrase, slots, gutsSprite, ennemiSprite,premierPlanMap);
        this.inventaireVue.armeMap();
        this.joueurVue = new JoueurVue(this.environnement.getGuts(), this.paneMap, inventaireVue);

        joueurVue.creerSpriteJoueur(this);

        this.ennemiVue = new EnnemiVue(environnement.getEnnemi().getX(), environnement.getEnnemi().getY(), environnement.getEnnemi().XProprety(), environnement.getEnnemi().YProprety(), this.paneMap, ennemiSprite);
        this.ennemiVue.creerSpriteEnnemi();

        this.animationTimer = new AnimatedEnnemiSprite(EnnemiVue.framesDroite, ennemiSprite);
        this.animationTimer.start();


        pvVue.getPvStackPane().layoutXProperty().bind(environnement.getGuts().XProprety().add(-400));
        pvVue.getPvStackPane().layoutYProperty().bind(environnement.getGuts().YProprety().add(-200));

        initAnimation();
        gameLoop.play();


        this.inventaireVue.afficherArmes();
    }

    private void initAnimation() {
        temps = 0;
        gameLoop = new Timeline();
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.200),
                ev -> {
                    if (temps == 10000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    } else {
                        this.environnement.unTour();
                        System.out.println(environnement.getGuts().getPv());
                        this.environnement.getEnnemi().attaquer();
                        temps++;
                        Direction direction = this.environnement.getEnnemi().getDirection();
                        if (direction == Direction.OUEST) {
                            System.out.println(direction);
                            this.animationTimer.updateFrames(EnnemiVue.framesGauche);                        }
                        else if (direction == Direction.EST) {
                            System.out.println(direction);
                            this.animationTimer.updateFrames(EnnemiVue.framesDroite);                        }
                        else if (direction == Direction.NORD) {
                            System.out.println(direction);
                            this.animationTimer.updateFrames(EnnemiVue.framesHaut);                        }
                        else if (direction == Direction.SUD) {
                            System.out.println(direction);
                            this.animationTimer.updateFrames(EnnemiVue.framesBas);                        }
                    }
                }
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.setCycleCount(Timeline.INDEFINITE);
    }


    public void initialiserGuts(){
        Image g = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_1.png").toExternalForm());
        gutsSprite = new ImageView(g);
        gutsSprite.setFitHeight(50);
        gutsSprite.setFitWidth(50);
        paneMap.getChildren().add(gutsSprite);
    }

    public void initialiserEnnemi(){
        Image g = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-droite1-removebg-preview.png").toExternalForm());
        ennemiSprite = new ImageView(g);
        ennemiSprite.setFitHeight(50);
        ennemiSprite.setFitWidth(50);
        paneMap.getChildren().add(ennemiSprite);
    }

    public void ajusterCameraSuiviJoueur() {
        double joueurX = environnement.getGuts().getX();
        double joueurY = environnement.getGuts().getY();

        // Ajuster pour le zoom
        double offsetX = -joueurX * ZOOM_FACTOR + (paneMap.getWidth() / 2) - (25 * ZOOM_FACTOR); // Ajustement pour le centrage horizontal
        double offsetY = -joueurY * ZOOM_FACTOR + (paneMap.getHeight() / 2) - (25 * ZOOM_FACTOR); // Ajustement pour le centrage vertical

        paneMap.setLayoutX(offsetX);
        paneMap.setLayoutY(offsetY);
    }

    static public void setGSprite(Image i){
        Controleur.gutsSprite.setImage(i);
    }

    public ImageView getGutsSprite() {
        return gutsSprite;
    }
}