package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private Epée epée;

    private Hache hache;

    public Environnement() {
        this.map = new Map();
        this.acteurs = FXCollections.observableArrayList();
        this.armeMap = new ArrayList<>();
        this.guts = new Joueur(this,x, y, vitesse, pv);
        this.ennemi = new Ennemi(this,x,y,vitesse,pv);
        acteurs.add(guts);
        acteurs.add(ennemi);
    }
    public ArrayList<Arme> getArmeMap() {
        return armeMap;
    }
    public void ajouterArme(Arme arme){
        this.armeMap.add(arme);
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
                if (map.getTab()[j / 50][i / 50] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public void armeMap(){
        this.epée = new Epée();
        this.ajouterArme(this.epée);
        this.hache = new Hache();
        hache.setX(400);
        hache.setY(400);
        Image image = new javafx.scene.image.Image("file:src/main/resources/universite_paris8/iut/abenibrahim/sae_dev2/hache.png");
        hache.setImage(image);
        this.ajouterArme(hache);
    }

    public void unTour(){
       int newX, newY;

       for(int i=0;i<=this.getActeurs().size() -1;i++){
           Acteur a = this.getActeurs().get(i);
           if(a instanceof Ennemi){
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