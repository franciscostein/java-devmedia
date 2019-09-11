package Lectures.lecture9.part14;

import java.util.Iterator;

public class AccountText extends Account {

    @Override
    protected String createHeader(Customer customer) {
        return "Rent registered for " + customer.getName() + "\n";
    }

    @Override
    protected String createRentInfo(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.getSubTotal() + "\n";
    }

    @Override
    protected String createFooter(Customer customer) {
        return "Total: R$ " + customer.getTotal() + "\n" +
                "Points gained: " + customer.getCustomerPoints();
    }
}
