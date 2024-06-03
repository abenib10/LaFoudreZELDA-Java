package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;




import javafx.scene.input.KeyEvent;

import javafx.scene.layout.*;

import javafx.util.Duration;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.*;

import java.net.URL;
import java.util.*;

public class Controleur implements Initializable {
    @FXML
    private Pane PaneMap;
    @FXML
    private TilePane tilePaneMap;
    @FXML
    private TilePane inventairePane;
    @FXML
    private HBox slot1;
    @FXML
    private Label titre;

    @FXML
    private HBox slot2;
    private Environnement environnement;
    private Timeline gameLoop;
    private int temps;
    static private ImageView gSprite;
    private Arme selectedArme;
    private List<Arme> armes = new ArrayList<>();
    private List<ImageView> armeImages = new ArrayList<>();

    private List<HBox> slots;
    private ImageView selectedImageView;





    @Override
    public void initialize(URL location, ResourceBundle resources){
        tilePaneMap.setPrefTileWidth(50);
        tilePaneMap.setPrefTileHeight(50);
        this.environnement=new Environnement();
        this.environnement.armeMap();
        slots = Arrays.asList(slot1,slot2);
        remplirMap();
        creerSpriteJoueur();
        afficherArmes();
        //creerSpriteEnnemi();
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
        ControleurTouche deplacementFleche = new ControleurTouche(this.environnement, gSprite, inventairePane);
        deplacementFleche.Actualiser(this);
        Scene scene = PaneMap.getScene();
        if (scene != null) {
            ((Scene) scene).addEventHandler(KeyEvent.KEY_PRESSED, deplacementFleche);
        } else {
            PaneMap.sceneProperty().addListener((obs, oldScene, newScene) -> {
                if (newScene != null) {
                    newScene.addEventHandler(KeyEvent.KEY_PRESSED, deplacementFleche);
                }
            });
        }
        gSprite = deplacementFleche.getImageView();
        gSprite.translateXProperty().bind(this.environnement.getGuts().XProprety());
        gSprite.translateYProperty().bind(this.environnement.getGuts().YProprety());
    }



   /* public void creerSpriteEnnemi(){
        Circle a = new Circle(10);
        a.setFill(Color.BLUEVIOLET);
        this.PaneMap.getChildren().add(a);
        a.translateXProperty().bind(environnement.getEnnemi().XProprety());
        a.translateYProperty().bind(environnement.getEnnemi().YProprety());
    }*/

    public void afficherArmes() {
        for (Arme arme : this.environnement.getArmeMap()) {
            Image image = arme.getImage(); // Obtenir l'image de l'arme
            ImageView imageView = new ImageView(image); // Créer l'ImageView avec l'image
            PaneMap.getChildren().add(imageView); // Ajouter l'ImageView au PaneMap
            imageView.setTranslateX(arme.getX()); // Ajuster la position X
            imageView.setTranslateY(arme.getY()); // Ajuster la position Y
            armes.add(arme); // Ajouter l'arme à la liste
            armeImages.add(imageView); // Ajouter l'ImageView à la liste
        }
    }
    void  afficherInventaire() {
        inventairePane.setVisible(true); // Rend le ListView inventairePane visible
        clearSlots();
        System.out.println("Taille de l'inventaire: " + environnement.getGuts().getListeArme().size());
        // Boucle à travers la liste des armes dans l'inventaire du joueur
        int indexSlot = 0;
        for (inventaireObjet item : environnement.getGuts().getListeArme()) {
            System.out.println("image");
            ImageView imageView = new ImageView(item.getImage());
            System.out.println(imageView.getImage().getUrl());// Crée une ImageView avec l'image de l'arme
            imageView.setFitWidth(50); // Définit la largeur de l'ImageView à 50 pixels
            imageView.setFitHeight(50); // Définit la hauteur de l'ImageView à 50 pixels

            // Add click event handler
            imageView.setOnMouseClicked(event -> handleArmeSelection(item.getArme(), imageView));

            if(indexSlot <= slots.size()){
                slots.get(indexSlot).getChildren().add(imageView);
                slots.get(indexSlot).setVisible(true);
                indexSlot++;
            }

        }

        PaneMap.setVisible(true); // Masque le Pane contenant la carte du jeu
        gSprite.setVisible(false);
        tilePaneMap.setVisible(true);
        slot1.setVisible(true);
        slot2.setVisible(true);
        titre.setVisible(true);
    }

    private void handleArmeSelection(Arme arme, ImageView imageView) {
        if (selectedImageView != null) {
            // Optionally, deselect the previously selected weapon
            // by resetting its visual state
            selectedImageView.setStyle("");
        }

        selectedArme = arme;
        selectedImageView = imageView;
        // Indicate selection, e.g., by changing the border color
        imageView.setStyle("-fx-border-color: red; -fx-border-width: 2px;");


        System.out.println("Selected weapon ID: " + arme.getId());
    }
    private void clearSlots() {
        for (HBox slot : slots) {
            slot.getChildren().clear();
            slot.setVisible(false);
        }
    }
    void masquerInventaire() {
        inventairePane.setVisible(false);
        slot1.setVisible(false);
        slot2.setVisible(false);
        titre.setVisible(false);
        PaneMap.setVisible(true); // Affiche le Pane contenant la carte du jeu
        tilePaneMap.setVisible(true);
        gSprite.setVisible(true);
        // Affiche le TilePane contenant la carte du jeu
        // Afficher/masquer d'autres éléments si nécessaire
    }
    public void supprimerArmeDeLaCarte(Arme arme) {
        int index = armes.indexOf(arme);
        if (index >= 0) {
            ImageView imageView = armeImages.remove(index);
            armes.remove(index);
            PaneMap.getChildren().remove(imageView);
        }
    }

}