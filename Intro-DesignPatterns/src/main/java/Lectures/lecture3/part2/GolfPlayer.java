package Lectures.lecture3.part2;

public class GolfPlayer extends Player {

    public GolfPlayer(String name) {
        super(name);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " tries to make at least a PAR in all holes");
    }

    @Override
    public void run() {
    }
}
