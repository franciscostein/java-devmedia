package Lectures.lecture5.part2;

//                          Facade Pattern, Lei de Demétrio ou "não fale com estranhos"
public class TestProduct {

    public static void main(String[] args) {
        Product product = new Product(1, "Mousepad", 13.50);
        System.out.println(product);
    }
}
