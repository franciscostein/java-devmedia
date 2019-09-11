package Lectures.lecture9.part9;

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
        String result = "Rent registered for " + this.name + "\n";

        Iterator<Rental> rent = rentals.iterator();
        while (rent.hasNext()) {
            Rental rental = rent.next();
            result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getSubTotal() + "\n";
        }
        result += "Total: R$ " + getTotal() + "\n";
        result += "Points gained: " + getCustomerPoints();
        return result;
    }

    private double getTotal() {
        double total = 0;
        Iterator<Rental> rent = rentals.iterator();
        while (rent.hasNext()) {
            Rental rental = rent.next();
            total += rental.getSubTotal();
        }
        return total;
    }

    private int getCustomerPoints() {
        int customerPoints = 0;
        Iterator<Rental> rent = rentals.iterator();
        while (rent.hasNext()) {
            Rental rental = rent.next();
            customerPoints += rental.getCustomerPoints();
        }
        return customerPoints;
    }
}
