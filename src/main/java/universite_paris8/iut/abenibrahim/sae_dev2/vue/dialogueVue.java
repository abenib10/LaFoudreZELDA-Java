package universite_paris8.iut.abenibrahim.sae_dev2.vue;

import javafx.fxml.FXML;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import javafx.scene.control.Label;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.acteur.Pnj;

import java.util.List;

public class dialogueVue {
    @FXML
    public Label dialogueBox;
    @FXML
    public Label dialogueBox2;

    private Environnement environnement;

    public dialogueVue(Label dialogueBox, Environnement environnement,Label dialogueBox2){
        this.dialogueBox = dialogueBox;
        this.environnement = environnement;
        this.dialogueBox2 = dialogueBox2;
    }

    public void afficherDialoguePnj(){
        List<Pnj> pnjs = environnement.getPnjList();
        if (!pnjs.isEmpty()) {
            dialogueBox.setText(pnjs.get(0).getDialogue());
            dialogueBox.setVisible(true);
        }
        if (pnjs.size() > 1) {
            dialogueBox2.setText(pnjs.get(1).getDialogue());
            dialogueBox2.setVisible(true);
        }
    }

    public void masquerDialogue() {
        dialogueBox.setVisible(false);
        dialogueBox2.setVisible(false);
    }
}
