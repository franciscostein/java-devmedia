package Lectures.lecture3.part4;

public class FootballPlayer extends Player {

    public FootballPlayer(String name, RunningBehavior behavior) {
        super(name, behavior);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " likes teamwork");
    }
}
