package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Acteur{
    private ObservableList<inventaireObjet> listeArme;
    private Arme armeEquipee;
    public Joueur(Environnement e,int x,int y,int v, int pv){
        super(e,x,y,v,pv);
        this.listeArme= FXCollections.observableArrayList();
        this.armeEquipee = null;
    }

    public void equiperArme(Arme arme){
        this.armeEquipee = arme;
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

    @Override
    public int type(){
        return 1;
    }

    @Override
    public void Attaquer(){
        // Positions du joueur et de l'ennemi
        int playerX, playerY, enemyX, enemyY;

// Distance maximale d'attaque du joueur
        int distanceAttaque = 50;

// Obtenir les positions actuelles du joueur et de l'ennemi
        playerX = environnement.getGuts().getX();
        playerY = environnement.getGuts().getY();
        enemyX = environnement.getEnnemi().getX();
        enemyY = environnement.getEnnemi().getY();

// Calculer la distance entre le joueur et l'ennemi
        int distanceX = Math.abs( enemyX - playerX);
        int distanceY = Math.abs( enemyY - playerY);
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
// Vérifier si l'ennemi est à portée d'attaque
        if (distance <= distanceAttaque) {
            // Déclencher l'attaque de l'ennemi
            environnement.getEnnemi().recoisDegat(this.armeEquipee.getPointAttaque());
            // Autres actions liées à l'attaque (animation, son, etc.)
        }
    }



}
