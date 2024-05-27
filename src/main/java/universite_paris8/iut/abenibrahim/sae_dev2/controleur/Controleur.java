package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;


import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private Pane PaneMap;
    @FXML
    private TilePane tilePaneMap;
    private Environnement environnement;
    private Timeline gameLoop;
    private int temps;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        tilePaneMap.setPrefTileWidth(50);
        tilePaneMap.setPrefTileHeight(50);
        this.environnement=new Environnement();
        remplirSpriteMap();
        creerSpriteJoueur();
        creerSpriteEnnemi();
        initAnimation();
        gameLoop.play();
        environnement.getGuts().pvProperty().addListener((obs, oldValue, newValue) -> updatePvJoueurImage());
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
                    else if (temps%20==0){
                        System.out.println("un tour");
                        int newX = (int) (environnement.getEnnemi().getX()+ Math.random()*5);
                        int newY = (int) (environnement.getEnnemi().getY()+ Math.random()*5);
                        if (environnement.dansTerrain(newX,newY)){
                            environnement.getEnnemi().setX(newX);
                            environnement.getEnnemi().setY(newY);
                            environnement.getEnnemi().Attaquer();
                            System.out.println("pv : " + environnement.getGuts().getPv());
                        }
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


    public void remplirSpriteMap() {
        for (int i = 0; i < this.environnement.getMap().getTab().length; i++) {
            for (int j = 0; j < this.environnement.getMap().getTab()[i].length; j++) {
                if (this.environnement.getMap().getTab()[i][j] == 1) {
                    Image herbe = new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/herbe.jpeg");
                    ImageView herbeMap = new ImageView(herbe);
                    herbeMap.setFitHeight(50);
                    herbeMap.setFitWidth(50);
                    this.tilePaneMap.getChildren().add(herbeMap);
                }
                else if (this.environnement.getMap().getTab()[i][j] == 2){
                    Image mur = new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/obstacleMur.jpeg");
                    ImageView murCollision = new ImageView(mur);
                    murCollision.setFitHeight(50);
                    murCollision.setFitWidth(50);
                    this.tilePaneMap.getChildren().add(murCollision);
                }
            }
        }

    }


    public void creerSpriteJoueur(){
        Image g = new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/2347000-middle-removebg-preview.png");
        ImageView gSprite = new ImageView(g);
        gSprite.setFitWidth(50);
        gSprite.setFitHeight(50);
        this.PaneMap.getChildren().add(gSprite);
        ControleurTouche deplacementFleche = new ControleurTouche(this.environnement);
        PaneMap.addEventHandler(KeyEvent.KEY_PRESSED, deplacementFleche);
        gSprite.translateXProperty().bind(this.environnement.getGuts().XProprety());
        gSprite.translateYProperty().bind(this.environnement.getGuts().YProprety());
    }

    public void creerSpriteEnnemi(){
        Circle a = new Circle(10);
        a.setFill(Color.BLUEVIOLET);
        this.PaneMap.getChildren().add(a);
        a.translateXProperty().bind(environnement.getEnnemi().XProprety());
        a.translateYProperty().bind(environnement.getEnnemi().YProprety());
    }

    private void updatePvJoueurImage() {
        ImageView pvImageView = new ImageView();
        this.PaneMap.getChildren().add(pvImageView);
        pvImageView.setFitHeight(50);
        pvImageView.setFitWidth(150);
        int pv = environnement.getGuts().getPv();
        if (pv > 42) {
            // Charger l'image "pleine vie"
            pvImageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/pleinevie-removebg-preview.png"));
        } else if (pv > 28) {
            // Charger l'image "vie moyenne"
            pvImageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/viemoyenne-removebg-preview.png"));
        } else {
            // Charger l'image "faible vie"
            pvImageView.setImage(new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/faiblevie-removebg-preview.png"));
        }
    }
}