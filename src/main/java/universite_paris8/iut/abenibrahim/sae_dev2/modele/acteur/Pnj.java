package universite_paris8.iut.abenibrahim.sae_dev2.modele.acteur;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pnj {
    private IntegerProperty x;
    private IntegerProperty y;

    private StringProperty Dialogue;

    public Pnj(){
        this.x = new SimpleIntegerProperty(350);
        this.y = new SimpleIntegerProperty(350);
        this.Dialogue = new SimpleStringProperty("Salut Aventurier !");
    }
    public int getX() {
        return x.getValue();
    }
    public int getY() {
        return y.getValue();
    }

    public void setX(int x) {
        this.x.setValue(x);
    }

    public void setY(int y) {
        this.y.setValue(y);
    }

    public void setDialogue(String dialogue) {
        this.Dialogue.set(dialogue);
    }

    public StringProperty DialogueProperty(){
        return Dialogue;
    }

    public String getDialogue() {
        return Dialogue.getValue();
    }

    public IntegerProperty getXproperty() {
        return x;
    }
    public IntegerProperty getYproperty() {
        return y;
    }
}
