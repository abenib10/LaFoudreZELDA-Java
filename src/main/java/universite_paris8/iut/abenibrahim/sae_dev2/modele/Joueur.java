package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;

public class Joueur extends Acteur{
    private ObservableList<inventaireObjet> listeArme;
    private Arme armeEquipee;
    public Joueur(Environnement e,int x,int y,int v, int pv){
        super(e,x,y,v,pv);
        this.listeArme= FXCollections.observableArrayList();
        this.armeEquipee = null;
    }

    public void ajouterArme( inventaireObjet arme) {
        this.listeArme.add(arme);
    }


    public ObservableList<inventaireObjet> getListeArme() {
        return listeArme;
    }

    public void recoisDegat(int degat) {
        int newPv = getPv() - degat;
        setPv(newPv);
    }

    public Arme ramasserarme(){
        int postionJoueurX, positionJoueurY, positionArmeX, positionArmeY;

        int distanceRamassage = 40;
        postionJoueurX = getX();
        positionJoueurY = getY();
        for (Arme arme : environnement.getArmeMap()) {
             positionArmeX = arme.getX();
             positionArmeY = arme.getY();

             int distanceX = Math.abs(postionJoueurX - positionArmeX);
             int distanceY = Math.abs(positionJoueurY - positionArmeY);
             double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

             if (distance <= distanceRamassage) {
                ajouterArme(new inventaireObjet(arme.getImage(), arme));
                environnement.getArmeMap().remove(arme);
                return arme;
             }
        }
        return null;


    }



}
