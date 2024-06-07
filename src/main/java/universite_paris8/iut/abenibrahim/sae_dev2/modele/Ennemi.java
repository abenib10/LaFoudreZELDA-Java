package universite_paris8.iut.abenibrahim.sae_dev2.modele;

public class Ennemi extends Acteur {
    private Arme epée;
    private Direction direction;

    public Ennemi(Environnement e, int x, int y, int v, int pv) {
        super(e, x, y, v, pv);
        this.epée = new Epée();
        this.direction = Direction.EST;
    }

    @Override
    public void attaquer() {
        int distanceAttaque = 50;
        Joueur joueur = environnement.getGuts();
        int distanceX = Math.abs(joueur.getX() - this.getX());
        int distanceY = Math.abs(joueur.getY() - this.getY());
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distance <= distanceAttaque) {
            joueur.recoisDegat(this.epée.getPointAttaque());
        }
    }

    @Override
    public void recoisDegat(int degat) {
        int newPv = getPv() - degat;
        setPv(newPv);
    }

    public void suivreJoueur() {
        Joueur joueur = environnement.getGuts();
        int xDepart = this.getX() / 50;
        int yDepart = this.getY() / 50;
        int xCible = joueur.getX() / 50;
        int yCible = joueur.getY() / 50;

        Noeud noeudCible = BFS.bfs(environnement.getMap().getTab(), xDepart, yDepart, xCible, yCible);

        if (noeudCible != null) {
            Noeud noeudCourant = noeudCible;
            while (noeudCourant.parent != null) {
                int nouvelleX = noeudCourant.x * 50;
                int nouvelleY = noeudCourant.y * 50;

                if (environnement.verifierCollisions(nouvelleX, nouvelleY)) {
                    int deltaX = (nouvelleX - this.getX()) / 3;
                    int deltaY = (nouvelleY - this.getY()) / 3;
                    this.setX(this.getX() + deltaX);
                    this.setY(this.getY() + deltaY);
                    if (deltaX > 0) {
                        direction = Direction.EST;
                    } else if (deltaX < 0) {
                        direction = Direction.OUEST;
                    } else if (deltaY > 0) {
                        direction = Direction.SUD;
                    } else {
                        direction = Direction.NORD;
                    }
                    break;
                }


                noeudCourant = noeudCourant.parent;
            }
        }
    }

    public Direction getDirection() {
        return direction;
    }
}