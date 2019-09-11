package Lectures.lecture9.part14;

import java.util.Iterator;

public class AccountHTML extends Account {

    @Override
    protected String createHeader(Customer customer) {
        return "<h1>Rent registered for " + customer.getName() + "</h1><p>\n";
    }

    @Override
    protected String createRentInfo(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.getSubTotal() + "<br>\n";
    }

    @Override
    protected String createFooter(Customer customer) {
        return "<p>Total: R$ " + customer.getTotal() + "</p>\n" +
                "<p>Points gained: " + customer.getCustomerPoints() + "</p>";
    }
}
