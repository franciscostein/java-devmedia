package Lectures.lecture3.part5;

public class ChessPlayer extends Player {

    public ChessPlayer(String name, RunningBehavior behavior) {
        super(name, behavior);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " dominates the center of the board");
    }
}
