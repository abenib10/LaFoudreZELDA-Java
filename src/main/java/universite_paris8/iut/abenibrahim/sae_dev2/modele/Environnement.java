package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private final int largeur = Constants.largeurMax;
    private final int pv = Constants.pv;
    private final int x=Constants.positionX;
    private final int y =Constants.positionY;
    private final int vitesse = Constants.vitesse;

    private Map map;
    private Arme arme;

    private ObservableList<Acteur> acteurs;

    private Joueur guts;
    private Ennemi ennemi;

    public Environnement() {
        this.map = new Map();
        this.acteurs = FXCollections.observableArrayList();
        this.guts = new Joueur(this,x, y, vitesse, pv);
        this.ennemi = new Ennemi(this,x,y,vitesse,pv);
        acteurs.add(guts);
        acteurs.add(ennemi);
    }

    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public Map getMap() {
        return map;
    }

    public Ennemi getEnnemi() {
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
        for (int i = x; i <= x + 30; i += 5) {
            for (int j = y; j <= y + 30; j += 5) {
                switch (map.getTab()[j / 50][i / 50]){
                    case 0, 2 -> {
                        return false;
                    }
                }
            }
        }
        return true;
    }




}