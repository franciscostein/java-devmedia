package Lectures.lecture9.part13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCustomer {

    @Test
    public void rentMovie() {
        String expectedResultTemp = "Rent registered for Leandro Costa\n" +
                "\tDancer in the Dark\t6.5\n" +
                "\tNymphomaniac Tiel I\t9.0\n" +
                "\tNymphomaniac Tiel II\t3.5\n" +
                "\tAu Revoir Les Enfants\t3.0\n" +
                "\tPaths of Glory\t3.0\n" +
                "Total: R$ 25.0\n" +
                "Points gained: 6\n";

        Customer customer = new Customer("Leandro Costa");
        // Normal movie R$ 2.00 of rent + R$ 4.50 of fine = total R$ 6.50
        Movie movie = new Movie("Dancer in the Dark", MovieType.NORMAL);
        Rental rental = new Rental(movie, 5);
        // Release rent which is R$ 3.00 + R$ 6.00 of fine, total R$ 9.00
        Movie movie1 = new Movie("Nymphomaniac Tiel I", MovieType.RELEASES);
        Rental rental1 = new Rental(movie1, 3);
        // Normal rent which is 2 + 1.5 of fine, total 3.5
        Movie movie2 = new Movie("Nymphomaniac Tiel II", MovieType.NORMAL);
        Rental rental2 = new Rental(movie2, 3);
        // Release rent which is 3, no fine, total 3
        Movie movie3 = new Movie("Au Revoir Les Enfants", MovieType.RELEASES);
        Rental rental3 = new Rental(movie3, 1);
        // Childre's rent which is 1.5 + 1.5 of fine, total 3
        Movie movie4 = new Movie("Paths of Glory", MovieType.CHILDREN);
        Rental rental4 = new Rental(movie4, 4);

        customer.addRental(rental);
        customer.addRental(rental1);
        customer.addRental(rental2);
        customer.addRental(rental3);
        customer.addRental(rental4);

        assertEquals(expectedResultTemp.trim(), customer.finishSale().trim());
        System.out.println(expectedResultTemp);
    }

    @Test
    public void rentMovieHTML() {
        String expectedResultTemp = "<h1>Rent registered for Leandro Costa</h1><p>\n" +
                "\tDancer in the Dark\t6.5<br>\n" +
                "\tNymphomaniac Tiel I\t9.0<br>\n" +
                "\tNymphomaniac Tiel II\t3.5<br>\n" +
                "\tAu Revoir Les Enfants\t3.0<br>\n" +
                "\tPaths of Glory\t3.0<br>\n" +
                "<p>Total: R$ 25.0</p>\n" +
                "<p>Points gained: 6</p>\n";

        Customer customer = new Customer("Leandro Costa");
        // Normal movie R$ 2.00 of rent + R$ 4.50 of fine = total R$ 6.50
        Movie movie = new Movie("Dancer in the Dark", MovieType.NORMAL);
        Rental rental = new Rental(movie, 5);
        // Release rent which is R$ 3.00 + R$ 6.00 of fine, total R$ 9.00
        Movie movie1 = new Movie("Nymphomaniac Tiel I", MovieType.RELEASES);
        Rental rental1 = new Rental(movie1, 3);
        // Normal rent which is 2 + 1.5 of fine, total 3.5
        Movie movie2 = new Movie("Nymphomaniac Tiel II", MovieType.NORMAL);
        Rental rental2 = new Rental(movie2, 3);
        // Release rent which is 3, no fine, total 3
        Movie movie3 = new Movie("Au Revoir Les Enfants", MovieType.RELEASES);
        Rental rental3 = new Rental(movie3, 1);
        // Childre's rent which is 1.5 + 1.5 of fine, total 3
        Movie movie4 = new Movie("Paths of Glory", MovieType.CHILDREN);
        Rental rental4 = new Rental(movie4, 4);

        customer.addRental(rental);
        customer.addRental(rental1);
        customer.addRental(rental2);
        customer.addRental(rental3);
        customer.addRental(rental4);

        assertEquals(expectedResultTemp.trim(), customer.finishSaleHTML().trim());
        System.out.println(expectedResultTemp);
    }
}
