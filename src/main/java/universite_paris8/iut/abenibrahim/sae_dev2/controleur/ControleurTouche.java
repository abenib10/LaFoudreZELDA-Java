package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Acteur;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Arme;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.AnimatedSprite;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Direction;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.InventaireVue;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.JoueurVue;

public class ControleurTouche implements EventHandler<KeyEvent> {
    private final AnimatedSprite animatedSprite;
    public Controleur ct;
    private Environnement e;
    private InventaireVue inventaireVue;

    public ControleurTouche(Environnement e, ImageView v, InventaireVue inventaireVue) {
        this.animatedSprite = new AnimatedSprite(e.getGuts().getX(), e.getGuts().getY(), JoueurVue.framesDroite, 0);
        this.animatedSprite.setImageView(v);
        this.animatedSprite.setFrameActuel(0);
        this.e = e;
        this.inventaireVue = inventaireVue;
    }

    @Override
    public void handle(KeyEvent event) {
        KeyCode k = event.getCode();
        Direction direction = null;
        switch (k){
            case W -> {
                if (inventaireVue.inventairePane.isVisible()) {
                    inventaireVue.masquerInventaire();
                }
            }
            case A -> {
                e.getGuts().attaquer();
            }
            case I -> {
                if (!inventaireVue.inventairePane.isVisible()) {
                    inventaireVue.afficherInventaire();
                }
            }
            case R -> {
                Arme ramassee = e.getGuts().ramasserarme();
                if (ramassee != null && ct != null) {
                    inventaireVue.supprimerArmeDeLaCarte(ramassee);
                }
            }
            case UP -> {
                direction = Direction.NORD;
                animatedSprite.definirFrames(JoueurVue.framesHaut);
            }
            case DOWN -> {
                direction = Direction.SUD;
                animatedSprite.definirFrames(JoueurVue.framesBas);
            }
            case LEFT -> {
                direction = Direction.OUEST;
                animatedSprite.definirFrames(JoueurVue.framesGauche);
            }
            case RIGHT -> {
                direction = Direction.EST;
                animatedSprite.definirFrames(JoueurVue.framesDroite);
            }
        }
        if (direction != null)
            if (!inventaireVue.inventairePane.isVisible()){
                this.e.getGuts().seDeplace(direction);
                ct.ajusterCameraSuiviJoueur();
            }
    }
    public void Actualiser(Controleur c){
        this.ct = c;
    }

    public AnimatedSprite getAnimatedSprite() {
        return animatedSprite;
    }
}
