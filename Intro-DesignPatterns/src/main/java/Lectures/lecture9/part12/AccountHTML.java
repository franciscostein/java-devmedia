package Lectures.lecture9.part12;

import java.util.Iterator;

public class AccountHTML extends Account {

    public String createHeader(Customer customer) {
        return "<h1>Rent registered for " + customer.getName() + "</h1><p>\n";
    }

    public String finishSaleHTML(Customer customer) {
        String result = createHeader(customer);

        Iterator<Rental> rent = customer.getRentals().iterator();
        while (rent.hasNext()) {
            Rental rental = rent.next();
            result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getSubTotal() + "<br>\n";
        }
        result += "<p>Total: R$ " + customer.getTotal() + "</p>\n";
        result += "<p>Points gained: " + customer.getCustomerPoints() + "</p>";
        return result;
    }
}
