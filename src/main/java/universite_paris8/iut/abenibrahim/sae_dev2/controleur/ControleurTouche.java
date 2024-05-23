package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Acteur;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Direction;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
public class ControleurTouche implements EventHandler<KeyEvent> {
    private String[] framesGauche = {"boy_left_1.png", "boy_left_2.png"};
    private String[] framesDroite = {"boy_right_1.png", "boy_right_2.png"};
    private String[] framesHaut = {"boy_up_1.png", "boy_up_2.png"};
    private String[] framesBas = {"boy_down_1.png", "boy_down_2.png"};

    //chemin des images differentes frame

    private void definirFrames(String[] frames) {
        chargerImages(frames);
        nombreFrames = frames.length;
        frameActuel = 0;
    }

    private Environnement e;

    public ControleurTouche(Environnement e){
       this.e=e;
    }

    @Override
    public void handle(KeyEvent event)
    {
        KeyCode k = event.getCode();
        Direction direction = null;

        if (k == KeyCode.UP)
        {
            direction = Direction.NORD;
            definirFrames(framesHaut);

        }
        //
        else  if (k == KeyCode.DOWN)
        {
            direction = Direction.SUD;
            definirFrames(framesBas);

        }
        else if (k == KeyCode.LEFT)
        {
            direction = Direction.OUEST;
            definirFrames(framesGauche);

        }
        else  if (k == KeyCode.RIGHT)
        {
            direction = Direction.EST;
            definirFrames(framesDroite);

        }

        if (direction != null)
            this.e.getGuts().seDeplace(direction);
    }
    /*
    public String[]getFramesGauche(){
        return this.framesGauche;
    }
    public String[]getFramesDroite(){
        return this.framesDroite;
    }
    public String[]getFramesHaut(){
        return this.framesHaut;
    }
    public String[]getFramesBas(){
        return this.framesBas;
    }

*/



}
