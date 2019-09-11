package Lectures.lecture6.part3;

public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
    }

    //  Synchronized resolve o problema com multithread, mas deixa até 100x mais lento
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
