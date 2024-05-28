package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Acteur;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.AnimatedSprite;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Direction;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
public class ControleurTouche  extends AnimatedSprite implements EventHandler<KeyEvent> {
    static private final String[] framesGauche = {ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_left_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_left_2.png").toExternalForm()};
    static private final String[] framesDroite = {ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_right_2.png").toExternalForm()};
    static private final String[] framesHaut = {ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_up_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_up_2.png").toExternalForm()};
    static private final String[] framesBas = {ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_down_1.png").toExternalForm(), ControleurTouche.class.getResource("/universite_paris8/iut/abenibrahim/sae_dev2/boy_down_2.png").toExternalForm()};
    public Controleur ct;
    //chemin des images differentes frame


    private Environnement e;

    public ControleurTouche(Environnement e, ImageView v) {
        super(e.getGuts().getX(), e.getGuts().getY(), framesDroite, 0);
        imageView = v;
        frameActuel = 0;
        this.e = e;
    }

    @Override
    public void handle(KeyEvent event) {
        KeyCode k = event.getCode();
        Direction direction = null;
        switch (k){
            case UP -> {
                direction = Direction.NORD;
                definirFrames(framesHaut);
            }
            case DOWN -> {
                direction = Direction.SUD;
                definirFrames(framesBas);
            }
            case LEFT -> {
                direction = Direction.OUEST;
                definirFrames(framesGauche);
            }
            case RIGHT -> {
                direction = Direction.EST;
                definirFrames(framesDroite);
            }
        }
        if (direction != null)
            this.e.getGuts().seDeplace(direction);

    }

    public void mettreAJour() {
        if (compteurDelaiFrame >= delaiFrame) {
            frameActuel = (frameActuel + 1) % nombreFrames;
            //+1 avoir la prohaine image
            //% Quand on est a la fin dutableau revenir au debut
            imageView.setImage(images[frameActuel]);
            Controleur.setGSprite(images[frameActuel]);

            compteurDelaiFrame = 0;
        } else {
            compteurDelaiFrame++;
            System.out.println("AAAA");
            //compte le nb de fois ou on appuie sur la flèche
        }
    }

    private void definirFrames(String[] frames) {

        chargerImage(frames);
        nombreFrames = frames.length;
        mettreAJour();
    }

    public void Actualiser(Controleur c){
        this.ct = c;
    }

}
