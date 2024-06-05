package universite_paris8.iut.abenibrahim.sae_dev2.modele;

class Noeud {
    int x, y;
    Noeud parent;

    Noeud(int x, int y, Noeud parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }
}
