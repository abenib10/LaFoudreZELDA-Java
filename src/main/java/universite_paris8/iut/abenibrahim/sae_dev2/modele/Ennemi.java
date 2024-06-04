package universite_paris8.iut.abenibrahim.sae_dev2.modele;

public class Ennemi extends Acteur{
    private Arme Epée;
    public Ennemi(Environnement e, int x, int y, int v, int pv) {
        super(e, x, y, v, pv);
        this.Epée=new Epée();
    }

    @Override
    public int type() {
        return 0;
    }

    public void Attaquer(){
       // Positions du joueur et de l'ennemi
        int playerX, playerY, enemyX, enemyY;

// Distance maximale d'attaque de l'ennemi
        int distanceAttaque = 50;

// Obtenir les positions actuelles du joueur et de l'ennemi
        playerX = environnement.getGuts().getX();
        playerY = environnement.getGuts().getY();
        enemyX = environnement.getEnnemi().getX();
        enemyY = environnement.getEnnemi().getY();

// Calculer la distance entre le joueur et l'ennemi
        int distanceX = Math.abs(playerX - enemyX);
        int distanceY = Math.abs(playerY - enemyY);
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

// Vérifier si l'ennemi est à portée d'attaque
        if (distance <= distanceAttaque) {
            // Déclencher l'attaque de l'ennemi
            environnement.getGuts().recoisDegat(this.Epée.getPointAttaque());
            // Autres actions liées à l'attaque (animation, son, etc.)
        }
    }

    public void recoisDegat(int degat) {
        int newPv = getPv() - degat;
        setPv(newPv);
        System.out.println("mes pv : " + getPv());
    }

}
