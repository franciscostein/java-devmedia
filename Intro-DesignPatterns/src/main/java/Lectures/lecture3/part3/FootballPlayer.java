package Lectures.lecture3.part3;

public class FootballPlayer extends Player implements Runnable {

    public FootballPlayer(String name) {
        super(name);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " likes teamwork");
    }

    @Override
    public void run() {
        System.out.println(name + " need to run a lot during the game");
    }
}
