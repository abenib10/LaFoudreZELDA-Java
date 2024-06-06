package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Acteur{
    private ObservableList<InventaireObjets> listeArme;
    private Arme armeEquipee;
    public Joueur(Environnement e,int x,int y,int v, int pv){
        super(e,x,y,v,pv);
        this.listeArme= FXCollections.observableArrayList();
        this.armeEquipee = null;
    }

    @Override
    public void attaquer(){
        int playerX, playerY, enemyX, enemyY;

        int distanceAttaque = 50;

        playerX = environnement.getGuts().getX();
        playerY = environnement.getGuts().getY();
        enemyX = environnement.getEnnemi().getX();
        enemyY = environnement.getEnnemi().getY();

        int distanceX = Math.abs( enemyX - playerX);
        int distanceY = Math.abs( enemyY - playerY);
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        if (distance <= distanceAttaque) {
            environnement.getEnnemi().recoisDegat(this.armeEquipee.getPointAttaque());
        }
    }

    public void equiperArme(Arme arme){
        this.armeEquipee = arme;
    }
    public void ajouterArme(InventaireObjets arme) {
        this.listeArme.add(arme);
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
                ajouterArme(new InventaireObjets(arme.getImage(), arme));
                environnement.getArmeMap().remove(arme);
                return arme;
            }
        }
        return null;
    }


    public ObservableList<InventaireObjets> getListeArme() {
        return listeArme;
    }

    public void recoisDegat(int degat) {
        int newPv = getPv() - degat;
        setPv(newPv);
    }


}
