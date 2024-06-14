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
    @FXML
    private TilePane tilePaneMap2;

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

    @FXML
    private Label nbSoin;
    private GameOverVue gameOverVue;
    private List<Circle> projectilesSprites = new ArrayList<>();

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
    private PvVueEnnemi pvVueEnnemi;
    private List<ImageView> armeImages = new ArrayList<>();
    private List<HBox> slots;
    private ImageView ennemiSprite;
    private AnimatedEnnemiSprite animationTimer;
    private soinvue soinvue;
    private dialogueVue dialogueVue;
    @FXML
    private Label dialogueBox;
    @FXML
    private Label dialogueBox2;


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

        this.mapVue = new MapVue(this.environnement.getMap().getTab(),this.environnement.getMap().getTab2(),environnement.getMap().getTab3(), tilePaneMap, premierPlanMap,tilePaneMap2);
        this.mapVue.remplirMap();

        this.pvVue = new PvVue(this.paneMap);
        environnement.getGuts().pvProperty().addListener((obs, oldValue, newValue) -> pvVue.updatePvJoueurImage(this.environnement.getGuts().getPv()));

        Image g = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_1.png").toExternalForm());
        gutsSprite = new ImageView(g);

        Image e = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/ennemi-droite1-removebg-preview.png").toExternalForm());
        ennemiSprite = new ImageView(e);


        this.pvVueEnnemi = new PvVueEnnemi(this.paneMap);
        environnement.getEnnemi().pvProperty().addListener((obs, oldValue, newValue) -> pvVueEnnemi.updatePvEnnemieImage(this.environnement.getEnnemi().getPv()));
        this.soinvue = new soinvue(this.paneMap,this.nbSoin,this.environnement);
        this.soinvue.afficherSoinMap();
        soinvue.getsoinStackPane().layoutXProperty().bind(environnement.getGuts().XProprety().add(-400));
        soinvue.getsoinStackPane().layoutYProperty().bind(environnement.getGuts().YProprety().add(-100));
        soinvue.getnbsoinStackPane().layoutXProperty().bind(environnement.getGuts().XProprety().add(-325));
        soinvue.getnbsoinStackPane().layoutYProperty().bind(environnement.getGuts().YProprety().add(-95));

        slots = Arrays.asList(slot1, slot2);
        this.inventaireVue = new InventaireVue(this.paneMap, this.tilePaneMap, this.environnement, inventairePane, slot1, slot2, titre, armeChoisie, phrase, slots, gutsSprite, ennemiSprite,premierPlanMap);
        this.inventaireVue.armeMap();
        this.joueurVue = new JoueurVue(this.environnement.getGuts(), this.paneMap, inventaireVue);
        this.dialogueVue = new dialogueVue(dialogueBox,environnement,dialogueBox2);
        this.joueurVue.initialiserGuts(gutsSprite, paneMap);
        joueurVue.creerSpriteJoueur(this);

        this.ennemiVue = new EnnemiVue(environnement.getEnnemi().getX(), environnement.getEnnemi().getY(), environnement.getEnnemi().XProprety(), environnement.getEnnemi().YProprety(), this.paneMap, ennemiSprite);
        this.ennemiVue.creerSpriteEnnemi();
        this.ennemiVue.initialiserEnnemi(ennemiSprite, paneMap);


        this.animationTimer = new AnimatedEnnemiSprite(EnnemiVue.framesDroite, ennemiSprite);
        this.animationTimer.start();


        pvVue.getPvStackPane().layoutXProperty().bind(environnement.getGuts().XProprety().add(-400));
        pvVue.getPvStackPane().layoutYProperty().bind(environnement.getGuts().YProprety().add(-200));
        pvVueEnnemi.getPvStackPane().layoutXProperty().bind(environnement.getEnnemi().XProprety().add(-10));
        pvVueEnnemi.getPvStackPane().layoutYProperty().bind(environnement.getEnnemi().YProprety().add(-50));


        initAnimation();
        gameLoop.play();

        gameOverVue = new GameOverVue(this.paneMap);
        gameOverVue.getImageView().setVisible(false);

        this.projectilesSprites = new ArrayList<>();


        this.inventaireVue.afficherArmes();
        this.soinvue.ajouterSoinMap();

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
                        updateProjectiles();
                        System.out.println("PV JOUEUR : " + environnement.getGuts().getPv());
                        System.out.println("PV ENNEMI : " + environnement.getEnnemi().getPv());                        this.environnement.getEnnemi().attaquer();
                        temps++;
                        this.ennemiVue.animerEnnemi(this.animationTimer, this.environnement.getEnnemi().getDirection());
                        if (environnement.getGuts().estMort()) {
                            gameLoop.stop();
                            paneMap.getChildren().remove(gutsSprite);
                            gameOverVue.updatePosition(environnement.getGuts().getX(), environnement.getGuts().getY());
                            gameOverVue.getImageView().setVisible(true);
                        }

                        if (environnement.getEnnemi().estMort()){
                            paneMap.getChildren().remove(ennemiSprite);
                        }

                    }
                }
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.setCycleCount(Timeline.INDEFINITE);
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
    private boolean checkCollision(Projectile projectile, Acteur ennemi) {
        int projectileX = projectile.getX();
        int projectileY = projectile.getY();
        int ennemiX = ennemi.getX();
        int ennemiY = ennemi.getY();
        int collisionDistance = 10; // Ajustez cette valeur si nécessaire

        int distanceX = Math.abs(projectileX - ennemiX);
        int distanceY = Math.abs(projectileY - ennemiY);

        return (distanceX < collisionDistance && distanceY < collisionDistance);
    }

    private void updateProjectiles() {
        List<Projectile> projectiles = environnement.getGuts().getProjectiles();

        // Supprimez les cercles de projectiles dépassés
        for (int i = projectilesSprites.size() - 1; i >= projectiles.size(); i--) {
            paneMap.getChildren().remove(projectilesSprites.get(i));
            projectilesSprites.remove(i);
        }

        // Déplacez les projectiles et vérifiez les collisions
        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.deplacer();
            if (checkCollision(projectile, environnement.getEnnemi())) {
                environnement.getEnnemi().recoisDegat(projectile.getDegat());
                iterator.remove(); // Supprimez le projectile de la liste
                int index = projectiles.indexOf(projectile);
                if (index != -1 && index < projectilesSprites.size()) {
                    paneMap.getChildren().remove(projectilesSprites.get(index));
                    projectilesSprites.remove(index);
                }
            }
        }

        // Mettez à jour les positions des cercles de projectiles
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            Circle projectileCircle;
            if (i < projectilesSprites.size()) {
                projectileCircle = projectilesSprites.get(i);
            } else {
                projectileCircle = new Circle(5, Color.RED); // Taille et couleur du projectile
                projectilesSprites.add(projectileCircle);
                paneMap.getChildren().add(projectileCircle);
            }
            projectileCircle.setCenterX(projectile.getX());
            projectileCircle.setCenterY(projectile.getY());
        }

    }

}