package Lectures.lecture3.part1;

public class FootballPlayer extends Player {

    public FootballPlayer(String name) {
        super(name);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " likes teamwork");
    }
}
