package Lectures.lecture3.part3;

public class GolfPlayer extends Player implements Lectures.lecture3.part3.Runnable {

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
