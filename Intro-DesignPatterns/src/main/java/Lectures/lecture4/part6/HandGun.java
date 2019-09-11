package Lectures.lecture4.part6;

public class HandGun implements Weapon {

    @Override
    public void load() {
        System.out.print("loading handgun");
    }

    @Override
    public void shoot() {
        System.out.print("Can you resist this shot?");
    }

    @Override
    public void aim() {
        System.out.print("I can see you ;-D");
    }
}
