package Lectures.lecture3.part5;

public class TennisPlayer extends Player {

    public TennisPlayer(String name, RunningBehavior behavior) {
        super(name, behavior);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " likes to beat the ball at the opponent's backhand");
    }
}
