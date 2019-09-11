package Lectures.lecture9.part12;

import java.util.Iterator;

public class AccountText extends Account {

    public String finishSaleText(Customer customer) {
        String result = "Rent registered for " + customer.getName() + "\n";

        Iterator<Rental> rent = customer.getRentals().iterator();
        while (rent.hasNext()) {
            Rental rental = rent.next();
            result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getSubTotal() + "\n";
        }
        result += "Total: R$ " + customer.getTotal() + "\n";
        result += "Points gained: " + customer.getCustomerPoints();
        return result;
    }
}
