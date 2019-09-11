package Lectures.lecture6.part2;

public class NaiveSingleton {

    private NaiveSingleton() {
    }

    public static NaiveSingleton getInstance() {
        return new NaiveSingleton();
    }
}
