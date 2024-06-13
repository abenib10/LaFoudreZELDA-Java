package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.*;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.AnimatedSprite;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.InventaireVue;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.JoueurVue;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.soinvue;

public class ControleurTouche implements EventHandler<KeyEvent> {
    private final AnimatedSprite animatedSprite;
    public Controleur ct;
    private InventaireVue inventaireVue;
    private Joueur joueur;
    private soinvue soinVue;
    public ControleurTouche(Joueur joueur, ImageView v, InventaireVue inventaireVue,soinvue soinVue) {
        this.animatedSprite = new AnimatedSprite(joueur.getX(), joueur.getY(), JoueurVue.framesDroite, 0);
        this.animatedSprite.setImageView(v);
        this.animatedSprite.setFrameActuel(0);
        this.inventaireVue = inventaireVue;
        this.soinVue = soinVue;
        this.joueur = joueur;
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
            case S -> {
                if (event.isControlDown()) {
                    ct.saveGame();
                }
            }
            case C -> {
                if(this.joueur.peutSeSoigner()){
                    this.joueur.seSoigner();
                }
            }
            case A -> {
                this.joueur.attaquer();
            }
            case I -> {
                if (!inventaireVue.inventairePane.isVisible()) {
                    inventaireVue.afficherInventaire();
                }
            }
            case R -> {
                Arme ramassee = this.joueur.ramasserarme();
                Soin soin = this.joueur.ramasserSoin();
                if (ramassee != null && ct != null) {
                    inventaireVue.supprimerArmeDeLaCarte(ramassee);
                }
                if (soin != null && ct != null) {
                    soinVue.supprimerSoinDeLaCarte(soin);
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
                this.joueur.seDeplace(direction);
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
