package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Arme {
        protected int attaque;
        private int x;
        private int y;

        public Arme(int a){
            this.attaque=a;
            this.x=350;
            this.y=350;
        }
        public int getPointAttaque() {
            return attaque;
        }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public ImageView getImageView() {
        Image image = new Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/epée-removebg-preview.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        return imageView;
    }

}
