package Lectures.lecture3.part2;

public class CardPlayer extends Player {

    public CardPlayer(String name) {
        super(name);
    }

    @Override
    public void defineTactics() {
        System.out.println(name + " stays calm even in the worst situation");
    }

    @Override
    public void run() {
        System.out.println(name + " thinks, doesn't run");
    }
}
