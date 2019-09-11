package Lectures.lecture9.part6;

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
            double subTotal = getSubTotal(rental);
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

    private double getSubTotal(Rental rental) {
        double subTotal = 0;
        switch (rental.getMovie().getMovieType()) {
            case NORMAL:
                subTotal += 2;
                if (rental.getDaysRented() > 2) { //fined with the customer takes more than two days to return the movie
                    subTotal += (rental.getDaysRented() - 2) * 1.5;
                }
                break;
            case RELEASES:
                subTotal += rental.getDaysRented() * 3;  // $3 for which day of releases rent
                break;
            case CHILDREN:
                subTotal += 1.5;    //for 3 days
                if (rental.getDaysRented() > 3) {
                    subTotal += (rental.getDaysRented() - 3) * 1.5;
                }
        }
        return subTotal;
    }
}
