package universite_paris8.iut.abenibrahim.sae_dev2.modele;

public abstract class Arme {
        private int attaque;

        public Arme(int a){
            this.attaque=a;
        }
        public int getPointAttaque() {
            return attaque;
        }
}
