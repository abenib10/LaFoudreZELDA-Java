package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

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
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private Pane paneMap;
    @FXML
    private TilePane tilePaneMap;
    private Environnement environnement;
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
    private Timeline gameLoop;
    private int temps;
    static private ImageView gutsSprite;
    private MapVue mapVue;
    private PvVue pvVue;
    private JoueurVue joueurVue;
    private EnnemiVue ennemiVue;
    private InventaireVue inventaireVue;

    private List<HBox> slots;

    private ImageView ennemiSprite;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        tilePaneMap.setPrefTileWidth(50);
        tilePaneMap.setPrefTileHeight(50);

        this.environnement=new Environnement();

        this.mapVue = new MapVue(this.environnement, tilePaneMap);
        this.mapVue.remplirMap();

        this.pvVue = new PvVue(this.environnement, this.paneMap);
        environnement.getGuts().pvProperty().addListener((obs, oldValue, newValue) -> pvVue.updatePvJoueurImage());
        initialiserGuts();
        initialiserEnnemi();

        slots = Arrays.asList(slot1, slot2);
        this.inventaireVue = new InventaireVue(this.paneMap, this.tilePaneMap, this.environnement, inventairePane, slot1, slot2, titre, armeChoisie, phrase, slots, gutsSprite);
        this.inventaireVue.armeMap();
        this.joueurVue = new JoueurVue(this.environnement, this.paneMap, inventaireVue);

        joueurVue.creerSpriteJoueur(this);

        this.ennemiVue = new EnnemiVue(this.environnement, this.paneMap, ennemiSprite);
        this.ennemiVue.creerSpriteEnnemi();

        initAnimation();
        gameLoop.play();


        this.inventaireVue.afficherArmes();



    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.100),
                (ev ->{
                    if(temps==10000){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else {
                        this.environnement.getEnnemi().suivreJoueur();
                        System.out.println(environnement.getGuts().getPv());
                        this.environnement.getEnnemi().attaquer();
                        temps++;
                    }
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

        double offsetX = -joueurX + (paneMap.getWidth() / 2); // Ajustement pour le centrage horizontal
        double offsetY = -joueurY + (paneMap.getHeight() / 2); // Ajustement pour le centrage vertical

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