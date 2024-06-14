package universite_paris8.iut.abenibrahim.sae_dev2.modele.acteur;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pnj {
    private int x;
    private int y;

    private StringProperty Dialogue;

    public Pnj(){
        this.x = 350;
        this.y = 350;
        this.Dialogue = new SimpleStringProperty("Salut Aventurier !");
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
}
