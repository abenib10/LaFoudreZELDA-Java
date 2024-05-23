package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private final int largeur = Constants.largeurMax;

    private Map map;

    private ObservableList<Acteur> acteurs;

    private Joueur guts;
    private Joueur ennemi;

    public Environnement() {
        this.map = new Map();
        this.acteurs = FXCollections.observableArrayList();
        this.guts = new Joueur(this,375, 375, 10, 10);
        this.ennemi = new Joueur(this,375,375,10,10);

    }

    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public Map getMap() {
        return map;
    }

    public Joueur getEnnemi() {
        return ennemi;
    }

    public void ajouter(Acteur a) {
        acteurs.add(a);
    }

    public boolean dansTerrain(int x, int y)
    {
        return x >= 0 && x < largeur && y >= 0 && y <largeur;
    }

    public Acteur getActeur(String id) {
        for (Acteur a : this.acteurs) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public Joueur getGuts() {
        return guts;
    }

    public boolean verifierCollisions(int x, int y) {
        for (int i = x; i <= x + 25; i += 5) {
            for (int j = y; j <= y + 25; j += 5) {
                if (map.getTab()[j / 50][i / 50] == 0) {
                    return false;
                }
            }
        }

        return true;
    }




}