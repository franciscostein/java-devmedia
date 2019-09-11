package Lectures.lecture3.part5;

public class GolfPlayer extends Player {

    public GolfPlayer(String name, RunningBehavior behavior) {
        super(name, behavior);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " tries to make at least a PAR in all holes");
    }
}
