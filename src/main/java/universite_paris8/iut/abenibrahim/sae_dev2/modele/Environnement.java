package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import universite_paris8.iut.abenibrahim.sae_dev2.vue.JoueurVue;

import java.util.ArrayList;

public class Environnement {
    private ArrayList<Arme> armeMap;
    private final int largeur = Constants.largeurMax;
    private final int pv = Constants.pv;
    private final int x=Constants.positionX;
    private final int y =Constants.positionY;
    private final int vitesse = Constants.vitesse;

    private Map map;
    private ObservableList<Acteur> acteurs;

    private Joueur guts;
    private Ennemi ennemi;

    public Environnement() {
        this.map = new Map();
        this.acteurs = FXCollections.observableArrayList();
        this.guts = new Joueur(this,x, y, vitesse, pv);
        this.ennemi = new Ennemi(this,10,10,vitesse,pv);
        this.armeMap = new ArrayList<>();
        acteurs.add(guts);
        acteurs.add(ennemi);
    }

    public ArrayList<Arme> getArmeMap() {
        return armeMap;
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

    public boolean dansTerrain(int x, int y)
    {
        return x >= 0 && x < largeur && y >= 0 && y <largeur;
    }

    public Joueur getGuts() {
        return guts;
    }

    public boolean verifierCollisions(int x, int y) {
        for (int i = x; i <= x + 30; i += 5) {
            for (int j = y; j <= y + 30; j += 5) {
                switch (map.getTab()[j / 100][i / 100]){
                    case  2 -> {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public void unTour(){
        int newX, newY;

        for(int i=0;i<=this.getActeurs().size() -1;i++){
            Acteur a = this.getActeurs().get(i);
                if(a instanceof Ennemi){
                if (ennemi.detecterJoueur()) {
                    ennemi.setDistanceDetection(500);
                    ennemi.suivreJoueur();
                }
                newX = a.getX()+5;
                newY = a.getY()+5;
                if(this.dansTerrain(newX,newY) && this.verifierCollisions(newX,newY)){
                    a.setX(newX);
                    a.setY(newY);
                }
            }
        }
        for(int i=acteurs.size()-1; i>=0;i--){
            Acteur a = acteurs.get(i);
            if (a instanceof Ennemi){
                if(!a.estVivant()){
                    System.out.println("mort de : " + a);
                    acteurs.remove(i);
                }
            }
        }
    }
}