package Lectures.lecture4.part5;

public class Rifle implements Weapon {

    @Override
    public void load() {
        System.out.print("loading my rifle");
    }

    @Override
    public void shoot() {
        System.out.print("Shooting to kill, motherfucker!");
    }

    @Override
    public void aim() {
        System.out.print("Zoooom");
    }
}
