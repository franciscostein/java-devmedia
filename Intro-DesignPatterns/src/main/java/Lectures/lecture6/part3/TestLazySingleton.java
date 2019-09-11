package Lectures.lecture6.part3;

public class TestLazySingleton {

    public static void main(String[] args) {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        LazySingleton lazySingleton1 = LazySingleton.getInstance();

        System.out.println(lazySingleton == lazySingleton1);
    }
}
