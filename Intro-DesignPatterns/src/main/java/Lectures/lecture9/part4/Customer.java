package Lectures.lecture9.part4;

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
        int customerPoints = 0;
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
                    break;
                case RELEASES:
                    subTotal += which.getDaysRented() * 3;  // $3 for which day of releases rent
                    break;
                case CHILDREN:
                    subTotal += 1.5;    //for 3 days
                    if (which.getDaysRented() > 3) {
                        subTotal += (which.getDaysRented() - 3) * 1.5;
                    }
            }
            customerPoints++;
            if (which.getMovie().getMovieType() == MovieType.RELEASES && which.getDaysRented() > 1) {
                customerPoints++;
            }
            result += "\t" + which.getMovie().getTitle() + "\t" + subTotal + "\n";
            total += subTotal;
        }
        result += "Total: R$ " + total + "\n";
        result += "Points gained: " + customerPoints;
        return result;
    }
}
