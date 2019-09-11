package Lectures.lecture4.part5;

public class Pistol implements Weapon {

    @Override
    public void load() {
        System.out.print("loading pistol");
    }

    @Override
    public void shoot() {
        System.out.print("Nobody survives a pistol shot");
    }

    @Override
    public void aim() {
        System.out.print("You are under my eyes");
    }
}
