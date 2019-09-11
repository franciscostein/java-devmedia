package Lectures.lecture5.part4;

public class FacadeClient {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.register("Astrid", 123);
        facade.shopping(18, 123);
        facade.finishShopping(123);
    }
}
