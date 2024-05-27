package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.Sprite;

public class AnimatedSprite extends Sprite {
    protected Image[] images;
    protected int frameActuel;
    protected int nombreFrames;
    protected int delaiFrame;
    protected int compteurDelaiFrame;
    protected String[][][][] cheminsImage;

    public AnimatedSprite(double x, double y, String[] cheminsImages, int delaiFrame) {
        super(x, y, cheminsImages[0]);
        chargerImage(cheminsImages);
        this.delaiFrame = delaiFrame;
        this.compteurDelaiFrame = 0;
        this.frameActuel = 0;
        this.nombreFrames = images.length;
        this.imageView = new ImageView(images[0]);
        this.imageView.setX(x);
        this.imageView.setY(y);
    }

    protected void chargerImage(String[] cheminsImages){
        images = new Image[cheminsImages.length];
        imageView = new ImageView();

        for(int i=0;i<cheminsImages.length;i++){
            images[i] = new Image(cheminsImages[i]);
        }
        imageView.setImage(images[0]);
        largeur = images[0].getWidth();
        hauteur = images[0].getHeight();
    }

    public ImageView getImageView(){
        return imageView;
    }


}