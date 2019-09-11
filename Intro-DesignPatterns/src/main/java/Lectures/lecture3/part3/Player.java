package Lectures.lecture3.part3;

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

    public abstract void defineTactics();
}
