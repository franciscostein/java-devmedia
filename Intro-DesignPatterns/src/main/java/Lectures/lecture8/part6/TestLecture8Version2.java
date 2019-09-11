package Lectures.lecture8.part6;

import java.util.List;

public class TestLecture8Version2 {

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDaoJPA();

        Lectures.lecture8.part6.Customer customer = new Lectures.lecture8.part6.Customer();
        customer.setName("Wagner");
        customer.setCity("São Paulo");

        Lectures.lecture8.part6.Customer customer1 = new Lectures.lecture8.part6.Customer();
        customer1.setName("Luíza");
        customer1.setCity("Belo Horizonte");

        Lectures.lecture8.part6.Customer customer2 = new Lectures.lecture8.part6.Customer();
        customer2.setName("Mariana");
        customer2.setCity("Salvador");

        Lectures.lecture8.part6.Customer customer3 = new Lectures.lecture8.part6.Customer();
        customer3.setName("Mariana");
        customer3.setCity("Florianopolis");

        dao.persist(customer);
        dao.persist(customer1);
        dao.persist(customer2);
        dao.persist(customer3);

        List<Lectures.lecture8.part6.Customer> customers = dao.findAll("Mariana");
        for (Lectures.lecture8.part6.Customer customer4 : customers) {
            if (customer4.getCity().equals("Salvador")) {
                customer4.setName("Mariane");
                dao.update(customer4);
            }
        }

        customers = dao.findAll("Mariana");
        for (Customer customer4 : customers) {
            if (customer4.getCity().equals("Salvador")) {
                customer4.setName("Mariane");
                dao.remove(customer4);
            }
        }

        customers = dao.findAll("Astrogildo");
        if (customers.isEmpty()) {
            System.out.println("Customer not found");
        }

        customers = dao.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customer found");
        } else {
            customers.forEach(System.out::println);
        }
    }
}
