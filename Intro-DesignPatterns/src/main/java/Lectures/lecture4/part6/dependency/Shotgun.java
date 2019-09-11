package Lectures.lecture4.part6.dependency;

//      Classe de terceiro
public class Shotgun {

    public Shotgun() {
    }

    public void loadGun() {
        System.out.print("loading shotgun");
    }

    public void shootToKill() {
        System.out.print("you're dead!");
    }

    public void targetEnemy() {
        System.out.print("using scope 2x");
    }
}
