package Lectures.lecture3.part2;

public abstract class Player {

    String name;

    public Player(String name) {
        this.name = name;
    }

    public void train() {
        System.out.println(name + " trains hard");
    }

    public void compete() {
        System.out.println(name + " is very competitive");
    }

    public void run() {
        System.out.println(name + " needs to run a lot during the game");
    }

    public abstract void defineTactics();
}
