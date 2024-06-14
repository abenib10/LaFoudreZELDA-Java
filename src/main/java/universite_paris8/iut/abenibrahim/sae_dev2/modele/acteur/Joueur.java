package universite_paris8.iut.abenibrahim.sae_dev2.modele.acteur;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.Environnement;
import universite_paris8.iut.abenibrahim.sae_dev2.modele.InventaireObjets;
import universite_paris8.iut.abenibrahim.sae_dev2.objet.Arme;
import universite_paris8.iut.abenibrahim.sae_dev2.objet.Soin;
public class Joueur extends Acteur {
    private ObservableList<InventaireObjets> listeArme;
    private Arme armeEquipee;
    private IntegerProperty nbSoin;
    public Joueur(Environnement e, int x, int y, int v, int pv){
        super(e,x,y,v,pv);
        this.listeArme= FXCollections.observableArrayList();
        this.armeEquipee = null;
        this.nbSoin= new SimpleIntegerProperty(20);
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

    public Soin ramasserSoin(){
        int postionJoueurX, positionJoueurY, positionSoinX, positionSoinY;
        int distanceRamassage = 40;
        postionJoueurX = getX();
        positionJoueurY = getY();
        for (Soin soin : environnement.getSoinMap()) {
            positionSoinX = soin.getX();
            positionSoinY = soin.getY();

            int distanceX = Math.abs(postionJoueurX - positionSoinX);
            int distanceY = Math.abs(positionJoueurY - positionSoinY);
            double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            if (distance <= distanceRamassage) {
                this.nbSoin.setValue(this.nbSoin.getValue() + 1);
                environnement.getSoinMap().remove(soin);
                return soin;
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
    public void seSoigner(){
        this.setPv(this.getPv() + 25);
        this.nbSoin.set(this.nbSoin.getValue()-1);
    }
    public IntegerProperty nbSoinProperty() {
        return nbSoin;
    }
    public boolean peutSeSoigner(){
        return this.nbSoin.get() > 0;
    }

    public boolean peutParler(){
        int joueurX, joueurY, pnjX, pnjY;
        int distanceMinParler = 50;
        joueurX = this.getX();
        joueurY = this.getY();

        for(Pnj pnj : environnement.getPnjList()){
            pnjX = pnj.getX();
            pnjY = pnj.getY();
            int distanceX = Math.abs(joueurX - pnjX);
            int distanceY = Math.abs(joueurY - pnjY);
            double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            return distance <= distanceMinParler;
        }
        return false;
    }
}
