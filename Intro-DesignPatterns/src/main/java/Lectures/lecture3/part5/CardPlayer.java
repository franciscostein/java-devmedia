package Lectures.lecture3.part5;

public class CardPlayer extends Player {

    public CardPlayer(String name, RunningBehavior behavior) {
        super(name, behavior);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " stays calm even in the worst situation");
    }
}
