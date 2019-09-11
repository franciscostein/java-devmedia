package Lectures.lecture3.part2;

public class TennisPlayer extends Player {

    public TennisPlayer(String name) {
        super(name);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " likes to beat the ball at the opponent's backhand");
    }
}
