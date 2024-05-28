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
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Arme;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Joueur;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Map;

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
    static private ImageView gSprite;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        tilePaneMap.setPrefTileWidth(50);
        tilePaneMap.setPrefTileHeight(50);
        this.environnement=new Environnement();
        remplirMap();
        creerSpriteJoueur();
        afficherArmes();
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

                        }
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


    public void remplirMap(){
        for (int i = 0; i < this.environnement.getMap().getTab().length; i++) {
            for (int j = 0; j < this.environnement.getMap().getTab()[i].length; j++) {
                switch (this.environnement.getMap().getTab()[i][j]) {
                    case 0 -> {
                        Image mur = new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/eau.png");
                        ImageView murCollision = new ImageView(mur);
                        murCollision.setFitHeight(50);
                        murCollision.setFitWidth(50);
                        this.tilePaneMap.getChildren().add(murCollision);
                    }
                    case 1 -> {
                        Image herbe = new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/herbe.png");
                        ImageView herbeMap = new ImageView(herbe);
                        herbeMap.setFitHeight(50);
                        herbeMap.setFitWidth(50);
                        this.tilePaneMap.getChildren().add(herbeMap);
                    }
                    case 2 -> {
                        Image i1 = new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/3.png");
                        ImageView i1Map = new ImageView(i1);
                        i1Map.setFitHeight(50);
                        i1Map.setFitWidth(50);
                        this.tilePaneMap.getChildren().add(i1Map);
                    }
                }
            }
        }
    }

    static public void setGSprite(Image i){
        Controleur.gSprite.setImage(i);

    }

    public void creerSpriteJoueur(){
        Image g = new Image(ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_1.png").toExternalForm());
        gSprite = new ImageView(g);
        gSprite.setFitHeight(50);
        gSprite.setFitWidth(50);
        PaneMap.getChildren().add(gSprite);
        ControleurTouche deplacementFleche = new ControleurTouche(this.environnement,gSprite);
        deplacementFleche.Actualiser(this);
        PaneMap.addEventHandler(KeyEvent.KEY_PRESSED, deplacementFleche);
        gSprite = deplacementFleche.getImageView();
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
    public void afficherArmes() {
        for (Arme arme : this.environnement.getArmeMap()) {
            ImageView imageView = arme.getImageView();
            PaneMap.getChildren().add(imageView);
            imageView.setTranslateX(arme.getX()); // Ajuster la position X
            imageView.setTranslateY(arme.getY()); // Ajuster la position Y
        }
    }
}