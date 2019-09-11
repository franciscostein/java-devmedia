package Lectures.lecture9.part14;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String finishSaleText() {
        return new AccountText().finishSale(this);
    }

    public String finishSaleHTML() {
        return new AccountHTML().finishSale(this);
    }

    public double getTotal() {
        double total = 0;
        Iterator<Rental> rent = rentals.iterator();
        while (rent.hasNext()) {
            Rental rental = rent.next();
            total += rental.getSubTotal();
        }
        return total;
    }

    public int getCustomerPoints() {
        int customerPoints = 0;
        Iterator<Rental> rent = rentals.iterator();
        while (rent.hasNext()) {
            Rental rental = rent.next();
            customerPoints += rental.getCustomerPoints();
        }
        return customerPoints;
    }
}
