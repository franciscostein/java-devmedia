package Lectures.lecture8.part5;

import java.util.List;

public class TestLecture8Version2 {

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDaoJPA();

        Customer customer = new Customer();
        customer.setName("Wagner");
        customer.setCity("São Paulo");

        Customer customer1 = new Customer();
        customer1.setName("Luíza");
        customer1.setCity("Belo Horizonte");

        Customer customer2 = new Customer();
        customer2.setName("Mariana");
        customer2.setCity("Salvador");

        Customer customer3 = new Customer();
        customer3.setName("Mariana");
        customer3.setCity("Florianopolis");

        dao.persist(customer);
        dao.persist(customer1);
        dao.persist(customer2);
        dao.persist(customer3);

        List<Customer> customers = dao.findAll("Mariana");
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
    }
}
