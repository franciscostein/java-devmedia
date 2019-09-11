package Lectures.lecture9.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String finishSale() {
        double total = 0;
        int customerPoints;
        String result = "Rent registered for " + this.name + "\n";

        Iterator<Rental> rent = rentals.iterator();
        while (rent.hasNext()) {
            double subTotal = 0;
            Rental which = rent.next();

            switch (which.getMovie().getMovieType()) {
                case NORMAL:
                    subTotal += 2;
                    if (which.getDaysRented() > 2) { //fined with the customer takes more than two days to return the movie
                        subTotal += (which.getDaysRented() - 2) * 1.5;
                    }
            }
        }
        return result;
    }
}
