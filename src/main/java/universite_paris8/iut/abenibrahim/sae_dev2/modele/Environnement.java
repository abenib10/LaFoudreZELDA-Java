package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.collections.FXCollections;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.acteur.Ennemi;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.acteur.Acteur;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.acteur.Joueur;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.acteur.Pnj;
import universite_paris8.iut.abenibrahim.sae_dev2.objet.Arme;
import universite_paris8.iut.abenibrahim.sae_dev2.objet.Soin;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

public class Environnement {
    private ArrayList<Arme> armeMap;
    private List<Pnj> pnjList;
    private ArrayList<Soin> soinMap;
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
        this.soinMap = new ArrayList<>();
        this.pnjList = new ArrayList<>();
        initialiserPnj();
        acteurs.add(guts);
        acteurs.add(ennemi);
    }
    public ArrayList<Arme> getArmeMap() {
        return armeMap;
    }
    public ArrayList<Soin> getSoinMap() {
        return soinMap;
    }
    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public List<Pnj> getPnjList() {
        return pnjList;
    }

    public void initialiserPnj(){
        Pnj pnj = new Pnj();
        Pnj pnj1 = new Pnj();
        pnj1.setX(150);
        pnj1.setY(500);
        pnj1.setDialogue("Bonjour aventurier !! ");
        getPnjList().add(pnj);
        getPnjList().add(pnj1);
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