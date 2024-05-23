package universite_paris8.iut.abenibrahim.sae_dev2.controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Acteur;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Direction;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
public class ControleurTouche implements EventHandler<KeyEvent> {


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
        }
        else  if (k == KeyCode.DOWN)
        {
            direction = Direction.SUD;
        }
        else if (k == KeyCode.LEFT)
        {
            direction = Direction.OUEST;
        }
        else  if (k == KeyCode.RIGHT)
        {
            direction = Direction.EST;
        }

        if (direction != null)
            this.e.getGuts().seDeplace(direction);
    }





}
