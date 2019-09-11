package Lectures.lecture6.part4;

public class TestStaticSingleton {

    public static void main(String[] args) {
        StaticSingleton staticSingleton = StaticSingleton.instance;
        StaticSingleton staticSingleton1 = StaticSingleton.instance;

        System.out.println(staticSingleton == staticSingleton1);
    }
}
