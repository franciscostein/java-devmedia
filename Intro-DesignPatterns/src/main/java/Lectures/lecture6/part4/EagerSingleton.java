package Lectures.lecture6.part4;

//  Multithread safe
public class EagerSingleton {

    private static EagerSingleton instance = new EagerSingleton(); //Eager

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
