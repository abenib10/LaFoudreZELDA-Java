package universite_paris8.iut.abenibrahim.sae_dev2.modele.acteur;

import universite_paris8.iut.abenibrahim.sae_dev2.modele.Constants;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;

public class ObjetPousser extends Acteur{
    private final int longueur =Constants.longueurObj;
    private final int largeur = Constants.largeurObj;

    public ObjetPousser(Environnement e , int x, int y){
        super(e,x,y,1,1);

    }

    @Override
    public void attaquer() {
    }

    @Override
    public void recoisDegat(int degat) {
    }
    public int getLongueur(){
        return longueur;
    }
    public int getLargeur(){
        return largeur;
    }
}