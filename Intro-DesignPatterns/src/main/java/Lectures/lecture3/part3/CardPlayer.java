package Lectures.lecture3.part3;

public class CardPlayer extends Player implements Runnable {

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
