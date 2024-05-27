package universite_paris8.iut.abenibrahim.sae_dev2.modele;

public class Joueur extends Acteur{

    public Joueur(Environnement e,int x,int y,int v, int pv){
        super(e,x,y,v,pv);
    }


    public void recoisDegat(int degat) {
        int newPv = getPv() - degat;
        setPv(newPv);
    }


}
