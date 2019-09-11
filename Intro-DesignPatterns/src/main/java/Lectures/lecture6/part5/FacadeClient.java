package Lectures.lecture6.part5;

public class FacadeClient {

    public static void main(String[] args) {
        Facade facade = Facade.getInstance();
        Facade facade1 = Facade.getInstance();
        facade.register("Astrid", 123);
        facade.shopping(18, 123);
        facade.finishShopping(123);

        System.out.println(facade == facade1);
    }
}
