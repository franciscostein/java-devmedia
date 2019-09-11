package Lectures.lecture3.part3;

public class ChessPlayer extends Player implements Runnable {

    public ChessPlayer(String name) {
        super(name);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " dominates the center of the board");
    }

    @Override
    public void run() {
        System.out.println(name + " thinks, doesn't run");
    }
}
