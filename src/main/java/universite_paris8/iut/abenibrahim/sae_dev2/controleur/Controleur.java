package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import java.awt.*;
import java.awt.event.KeyListener;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Joueur;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Map;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private Pane PaneMap;
    @FXML
    protected TilePane tilePaneMap;
    protected Environnement environnement;
    protected Timeline gameLoop;
    protected int temps;
    static private ImageView gSprite;

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
    }
    static public void setGSprite(Image i){
        Controleur.gSprite.setImage(i);

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
                    else if (temps%5==0){
                        System.out.println("un tour");
                        int newX = (int) (environnement.getEnnemi().getX()+ Math.random()*5);
                        int newY = (int) (environnement.getEnnemi().getY()+ Math.random()*5);
                        if (environnement.dansTerrain(newX,newY)){
                            environnement.getEnnemi().setX(newX);
                            environnement.getEnnemi().setY(newY);
                        }
                    }
                    else if (temps%7==0){
                        System.out.println("un tour");
                        int newX = (int) (environnement.getEnnemi().getX()-1);
                        int newY = (int) (environnement.getEnnemi().getY()-1);
                        if (environnement.dansTerrain(newX,newY)){
                            environnement.getEnnemi().setX(newX);
                            environnement.getEnnemi().setY(newY);
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
                    Image herbe = new Image("file:///home/etudiants/info/abenibrahim/Documents/BUT Info S2/SAE_DEV2/src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/herbe.jpeg");
                    ImageView herbeMap = new ImageView(herbe);
                    herbeMap.setFitHeight(50);
                    herbeMap.setFitWidth(50);
                    this.tilePaneMap.getChildren().add(herbeMap);
                } else if (this.environnement.getMap().getTab()[i][j] == 0){
                    Image arbre = new Image("file:///home/etudiants/info/abenibrahim/Documents/BUT Info S2/SAE_DEV2/src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/arbre.jpeg");
                    ImageView arbreCollision = new ImageView(arbre);
                    arbreCollision.setFitHeight(50);
                    arbreCollision.setFitWidth(50);
                    this.tilePaneMap.getChildren().add(arbreCollision);
                }
                else if (this.environnement.getMap().getTab()[i][j] == 2){
                    Image mur = new Image("file:///home/etudiants/info/abenibrahim/Documents/BUT Info S2/SAE_DEV2/src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/obstacleMur.jpeg");
                    ImageView murCollision = new ImageView(mur);
                    murCollision.setFitHeight(50);
                    murCollision.setFitWidth(50);
                    this.tilePaneMap.getChildren().add(murCollision);
                }
            }
        }

    }


    public void creerSpriteJoueur(){

        Image g = new Image(ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_1.png").toExternalForm());

        gSprite = new ImageView(g);
        gSprite.setFitHeight(50);
        gSprite.setFitWidth(50);

       PaneMap.getChildren().add(gSprite);
        ControleurTouche deplacementFleche = new ControleurTouche(this.environnement,gSprite);
        deplacementFleche.Actualiser(this);
        //System.out.println("Image 2: " + gSprite.getImage().getUrl());
        PaneMap.addEventHandler(KeyEvent.KEY_PRESSED, deplacementFleche);
        gSprite = deplacementFleche.getImageView();
        //System.out.println("Image 2: " + gSprite.getImage().getUrl());
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
}