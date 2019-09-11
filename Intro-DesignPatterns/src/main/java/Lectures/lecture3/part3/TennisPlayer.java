package Lectures.lecture3.part3;

public class TennisPlayer extends Player implements Runnable {

    public TennisPlayer(String name) {
        super(name);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " likes to beat the ball at the opponent's backhand");
    }

    @Override
    public void run() {
        System.out.println(name + " need to run a lot during the game");
    }
}
