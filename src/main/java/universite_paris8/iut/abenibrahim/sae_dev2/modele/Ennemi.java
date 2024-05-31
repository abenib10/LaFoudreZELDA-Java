package universite_paris8.iut.abenibrahim.sae_dev2.modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Ennemi extends Acteur {

    public Ennemi(Environnement e, int x, int y, int v, int pv) {
        super(e, x, y, v, pv);
    }


    private boolean estValide(int x, int y, boolean[][] visite) {
        int maxX = environnement.getMap().getTab()[0].length;
        int maxY = environnement.getMap().getTab().length;
        return x >= 0 && x < maxX && y >= 0 && y < maxY && environnement.getMap().getTab()[y][x] != 2 && !visite[y][x];
    }

    private Queue<Coordonnees> bfs(Coordonnees depart, Coordonnees cible) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int maxX = environnement.getMap().getTab()[0].length;
        int maxY = environnement.getMap().getTab().length;
        boolean[][] visite = new boolean[maxY][maxX];
        Queue<Coordonnees> queue = new LinkedList<>();
        Queue<Coordonnees> chemin = new LinkedList<>();

        queue.add(depart);
        visite[depart.y][depart.x] = true;

        while (!queue.isEmpty()) {
            Coordonnees actuel = queue.poll();
            chemin.add(actuel);

            if (actuel.x == cible.x && actuel.y == cible.y) {
                System.out.println("Chemin trouvé :");
                for (Coordonnees c : chemin) {
                    System.out.println("Coordonnée : " + c.x + ", " + c.y);
                }
                return chemin;
            }

            for (int[] dir : directions) {
                int nouveauX = actuel.x + dir[0];
                int nouveauY = actuel.y + dir[1];

                if (estValide(nouveauX, nouveauY, visite)) {
                    visite[nouveauY][nouveauX] = true;
                    queue.add(new Coordonnees(nouveauX, nouveauY));
                }
            }
        }

        System.out.println("Aucun chemin trouvé.");
        return new LinkedList<>();
    }

    public void attaquer() {
        int distanceAttaque = 20;
        Joueur joueur = environnement.getGuts();
        int distanceX = Math.abs(joueur.getX() - this.getX());
        int distanceY = Math.abs(joueur.getY() - this.getY());
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distance <= distanceAttaque) {
            joueur.recoisDegat(this.arme.getPointAttaque());
        } else {
            Coordonnees positionEnnemi = new Coordonnees(this.getX() / 50, this.getY() / 50);
            Coordonnees positionJoueur = new Coordonnees(joueur.getX() / 50, joueur.getY() / 50);

            if (estValide(positionJoueur.x, positionJoueur.y, new boolean[environnement.getMap().getTab().length][environnement.getMap().getTab()[0].length])) {
                Queue<Coordonnees> chemin = bfs(positionEnnemi, positionJoueur);

                if (!chemin.isEmpty()) {
                    Coordonnees prochainMouvement = chemin.poll();

                    if (prochainMouvement.x == positionEnnemi.x && prochainMouvement.y == positionEnnemi.y && !chemin.isEmpty()) {
                        prochainMouvement = chemin.poll();
                    }

                    this.setX(prochainMouvement.x * 50);
                    this.setY(prochainMouvement.y * 50);
                    System.out.println("Position Ennemi: " + this.getX() + ", " + this.getY());
                }
            }
        }
    }
}