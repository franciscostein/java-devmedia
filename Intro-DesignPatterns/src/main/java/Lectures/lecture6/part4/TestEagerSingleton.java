package Lectures.lecture6.part4;

public class TestEagerSingleton {

    public static void main(String[] args) {
        EagerSingleton eagerSingleton = EagerSingleton.getInstance();
        EagerSingleton eagerSingleton1 = EagerSingleton.getInstance();

        System.out.println(eagerSingleton == eagerSingleton1);
    }
}
