package universite_paris8.iut.abenibrahim.sae_dev2.modele;


import javafx.scene.image.Image;

public class armeMap {
    private Environnement e;

    private Epée epée;

    private Hache hache;

    public armeMap(){
        this.epée = new Epée();
        this.e.ajouterArme(this.epée);
        this.hache = new Hache();
        hache.setX(400);
        hache.setY(400);
        Image image = new javafx.scene.image.Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/hache.png");
        hache.setImage(image);
        this.e.ajouterArme(hache);
    }

}



