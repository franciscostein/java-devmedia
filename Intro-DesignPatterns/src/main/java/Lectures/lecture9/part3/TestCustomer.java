package Lectures.lecture9.part3;

import org.junit.jupiter.api.Test;

public class TestCustomer {

    @Test
    public void rentMovie() {
        String expectedResultTemp = "Rent registered for Leandro Costa\n" +
                "\tDancer in the Dark 6.5\n" +
                "\tNymphomaniac Tiel I 9.0\n" +
                "\tNymphomaniac Tiel II 3.5\n" +
                "\tAu Revoir Les Enfants 3.0\n" +
                "\nPaths of Glory 3.0\n" +
                "Total: R$ 25.0\n" +
                "Points gained: 6\n";
    }
}
