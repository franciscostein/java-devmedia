package Lectures.lecture9.part13;

import java.util.Iterator;

public class AccountHTML extends Account {

    private String createHeader(Customer customer) {
        return "<h1>Rent registered for " + customer.getName() + "</h1><p>\n";
    }

    public String finishSaleHTML(Customer customer) {
        String result = createHeader(customer);

        Iterator<Rental> rent = customer.getRentals().iterator();
        while (rent.hasNext()) {
            Rental rental = rent.next();
            result += createRentInfo(rental);
        }
        result += createFooter(customer);
        return result;
    }

    private String createRentInfo(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.getSubTotal() + "<br>\n";
    }

    private String createFooter(Customer customer) {
        return "<p>Total: R$ " + customer.getTotal() + "</p>\n" +
                "<p>Points gained: " + customer.getCustomerPoints() + "</p>";
    }
}
