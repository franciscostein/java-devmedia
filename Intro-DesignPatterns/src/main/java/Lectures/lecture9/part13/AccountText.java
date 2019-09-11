package Lectures.lecture9.part13;

import java.util.Iterator;

public class AccountText extends Account {

    private String createHeader(Customer customer) {
        return "Rent registered for " + customer.getName() + "\n";
    }

    public String finishSaleText(Customer customer) {
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
        return "\t" + rental.getMovie().getTitle() + "\t" + rental.getSubTotal() + "\n";
    }

    private String createFooter(Customer customer) {
        return "Total: R$ " + customer.getTotal() + "\n" +
                "Points gained: " + customer.getCustomerPoints();
    }
}
