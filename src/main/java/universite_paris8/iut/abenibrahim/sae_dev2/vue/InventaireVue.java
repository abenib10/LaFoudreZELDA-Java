package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.*;

import java.util.ArrayList;
import java.util.List;

public class InventaireVue{

    private Pane paneMap;
    private TilePane tilePaneMap;
    @FXML
    public TilePane inventairePane;
    @FXML
    private HBox slot1;
    @FXML
    private Label titre;
    @FXML
    private TilePane premierPlanMap;
    @FXML
    private HBox slot2;

    @FXML
    private Label armeChoisie;
    @FXML
    private Label phrase;
    private Environnement environnement;
    private Arme selectedArme;

    private List<Arme> armes = new ArrayList<>();

    private List<ImageView> armeImages = new ArrayList<>();
    private List<HBox> slots;
    private ImageView selectedImageView;

    private ImageView gSprite;
    private ImageView ennemiSprite;


    private Epée epée;
    private Hache hache;

    public InventaireVue(Pane paneMap, TilePane tilePaneMap, Environnement environnement, TilePane inventaireP, HBox slot1, HBox slot2, Label titre, Label armeChoisie, Label phrase, List<HBox> slots, ImageView g, ImageView eSprite){
        this.paneMap = paneMap;
        this.tilePaneMap = tilePaneMap;
        this.environnement = environnement;
        this.inventairePane = inventaireP;
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.titre = titre;
        this.armeChoisie = armeChoisie;
        this.phrase = phrase;
        this.slots = slots;
        this.gSprite = g;
        this.ennemiSprite = eSprite;
        this.epée = new Epée();
        this.hache = new Hache();
        this.premierPlanMap = premierPlanMap;


    }

    public void ajouterArme(Arme arme){
        this.environnement.getArmeMap().add(arme);
    }

    public void afficherArmes() {
        for (Arme arme : this.environnement.getArmeMap()) {
            Image image = arme.getImage(); // Obtenir l'image de l'arme
            ImageView imageView = new ImageView(image); // Créer l'ImageView avec l'image
            paneMap.getChildren().add(imageView); // Ajouter l'ImageView au PaneMap
            imageView.setTranslateX(arme.getX()); // Ajuster la position X
            imageView.setTranslateY(arme.getY()); // Ajuster la position Y
            armes.add(arme);
            armeImages.add(imageView);
        }
    }

    public void  afficherInventaire() {

        inventairePane.setVisible(true);
        inventairePane.setLayoutX(this.environnement.getGuts().getX());
        inventairePane.setLayoutY(this.environnement.getGuts().getY());
        slot1.setLayoutX(this.environnement.getGuts().getX() + 50);
        slot1.setLayoutY(this.environnement.getGuts().getY() + 75);
        slot2.setLayoutY(this.environnement.getGuts().getY() + 75);
        slot2.setLayoutX(this.environnement.getGuts().getX() + 150);
        titre.setLayoutX(this.environnement.getGuts().getX() + 125);
        titre.setLayoutY(this.environnement.getGuts().getY());
        phrase.setLayoutX(this.environnement.getGuts().getX() + 50);
        phrase.setLayoutY(this.environnement.getGuts().getY() + 150);
        this.armeChoisie.setLayoutX(this.environnement.getGuts().getX() + 200);
        this.armeChoisie.setLayoutY(this.environnement.getGuts().getY() + 150);
        clearSlots();
        System.out.println("Taille de l'inventaire: " + environnement.getGuts().getListeArme().size());
        // Boucle à travers la liste des armes dans l'inventaire du joueur
        int indexSlot = 0;
        for (InventaireObjets item : environnement.getGuts().getListeArme()) {
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

        for (ImageView imageView : armeImages){
            imageView.setVisible(false);
        }

        paneMap.setVisible(true); // Masque le Pane contenant la carte du jeu
        gSprite.setVisible(false);
        tilePaneMap.setVisible(true);
        slot1.setVisible(true);
        slot2.setVisible(true);
        titre.setVisible(true);
        armeChoisie.setVisible(true);
        phrase.setVisible(true);
        ennemiSprite.setVisible(false);
        premierPlanMap.setVisible(false);


    }

    private void handleArmeSelection(Arme arme, ImageView imageView) {
        if (selectedImageView != null) {
            // Optionally, deselect the previously selected weapon
            // by resetting its visual state
            selectedImageView.setStyle("");
        }

        selectedArme = arme;
        this.environnement.getGuts().equiperArme(selectedArme);
        selectedImageView = imageView;
        // Indicate selection, e.g., by changing the border color
        imageView.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        System.out.println("Selected weapon nom " + arme.getNom());
        this.armeChoisie.textProperty().bind(selectedArme.nomProperty());
        this.armeChoisie.setLayoutX(this.environnement.getGuts().getX() + 200);
        this.armeChoisie.setLayoutY(this.environnement.getGuts().getY() + 150);

    }
    private void clearSlots() {
        for (HBox slot : slots) {
            slot.getChildren().clear();
            slot.setVisible(false);
        }
    }
    public void masquerInventaire() {
        inventairePane.setVisible(false);
        slot1.setVisible(false);
        slot2.setVisible(false);
        titre.setVisible(false);
        paneMap.setVisible(true); // Affiche le Pane contenant la carte du jeu
        tilePaneMap.setVisible(true);
        gSprite.setVisible(true);
        armeChoisie.setVisible(false);
        phrase.setVisible(false);
        ennemiSprite.setVisible(true);
        premierPlanMap.setVisible(true);


        for (ImageView imageView : armeImages){
            imageView.setVisible(true);
        }
        // Affiche le TilePane contenant la carte du jeu
        // Afficher/masquer d'autres éléments si nécessaire
    }
    public void supprimerArmeDeLaCarte(Arme arme) {
        int index = armes.indexOf(arme);
        if (index >= 0) {
            ImageView imageView = armeImages.remove(index);
            armes.remove(index);
            paneMap.getChildren().remove(imageView);
        }
    }

    public void armeMap(){
        this.ajouterArme(this.epée);
        hache.setX(400);
        hache.setY(400);
        Image image = new Image(getClass().getResource("/universite_paris8/iut/abenibrahim/sae_dev2/hache.png").toString());
        hache.setImage(image);
        this.ajouterArme(hache);
    }
}