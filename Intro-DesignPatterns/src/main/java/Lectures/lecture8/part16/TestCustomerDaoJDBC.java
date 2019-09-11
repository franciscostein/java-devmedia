package Lectures.lecture8.part16;

import Lectures.lecture8.part16.entity.Credential;
import Lectures.lecture8.part16.entity.Customer;
import Lectures.lecture8.part16.integration.CustomerDAO;
import Lectures.lecture8.part16.integration.CustomerDaoJDBC;

public class TestCustomerDaoJDBC {

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDaoJDBC();

        Customer customer = new Customer();
        customer.setName("Karl");
        customer.setCity("Trier");

        Credential credential = new Credential();
        credential.setLogin("kommunism");
        credential.setPassword("marx");

        customer.setCredential(credential);

        credential = new Credential();
        credential.setLogin("wagner");
        credential.setPassword("1234");

        customer.setCredential(credential);

        Customer customer1 = new Customer();
        customer1.setName("Luíza");
        customer1.setCity("Belo Horizonte");

        credential = new Credential();
        credential.setLogin("luiza");
        credential.setPassword("1234");

        customer1.setCredential(credential);

        Customer customer2 = new Customer();
        customer2.setName("Mariana");
        customer2.setCity("Salvador");

        credential = new Credential();
        credential.setLogin("mariana");
        credential.setPassword("1234");

        customer2.setCredential(credential);

        Customer customer3 = new Customer();
        customer3.setName("Mariana");
        customer3.setCity("Florianopolis");

        credential = new Credential();
        credential.setLogin("mariana");
        credential.setPassword("12345");

        customer3.setCredential(credential);

        dao.persist(customer);
        dao.persist(customer1);
        dao.persist(customer2);
        dao.persist(customer3);

        /*List<Customer> customers = dao.findAll("Mariana");
        for (Customer customer4 : customers) {
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
        }*/
    }
}
