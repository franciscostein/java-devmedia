package Lectures.lecture9.part7;

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
            Rental rental = rent.next();
            double subTotal = rental.getSubTotal();
            customerPoints++;

            if (rental.getMovie().getMovieType() == MovieType.RELEASES && rental.getDaysRented() > 1) {
                customerPoints++;
            }
            result += "\t" + rental.getMovie().getTitle() + "\t" + subTotal + "\n";
            total += subTotal;
        }
        result += "Total: R$ " + total + "\n";
        result += "Points gained: " + customerPoints;
        return result;
    }
}
