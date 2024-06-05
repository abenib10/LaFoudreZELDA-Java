module universite_paris8.iut.abenibrahim.sae_dev2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires javafx.media;
    opens universite_paris8.iut.abenibrahim.sae_dev2 to javafx.fxml;
    exports universite_paris8.iut.abenibrahim.sae_dev2.modele;
    exports universite_paris8.iut.abenibrahim.sae_dev2.controleur;
    opens universite_paris8.iut.abenibrahim.sae_dev2.controleur to javafx.fxml;
    exports universite_paris8.iut.abenibrahim.sae_dev2;
    exports universite_paris8.iut.abenibrahim.sae_dev2.vue;
    opens universite_paris8.iut.abenibrahim.sae_dev2.vue to javafx.fxml;
}