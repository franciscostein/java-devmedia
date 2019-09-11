package Lectures.lecture9.part14;

import java.util.Iterator;
                                    // Template Method
public abstract class Account {

    abstract String createHeader(Customer customer);

    abstract String createRentInfo(Rental rental);

    abstract String createFooter(Customer customer);

    public String finishSale(Customer customer) {
        String result = createHeader(customer);

        Iterator<Rental> rent = customer.getRentals().iterator();
        while (rent.hasNext()) {
            Rental rental = rent.next();
            result += createRentInfo(rental);
        }
        result += createFooter(customer);
        return result;
    }
}
