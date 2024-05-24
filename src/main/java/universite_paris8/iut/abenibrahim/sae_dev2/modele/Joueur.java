package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur extends Acteur{

    public Joueur(Environnement e,int x,int y,int v, int pv,Arme arme){
        super(e,x,y,v,pv,arme);
    }

    public void Attaquer(Acteur a){
        if(a instanceof Ennemie){
            a.setPv(getPv()-arme.getPointAttaque());
        }
    }



}
