package Lectures.lecture8.part4;

public class TestLecture8Version2 {

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDaoJPA();

        Customer customer = new Customer();
        customer.setName("Wagner");
        customer.setCity("São Paulo");

        dao.persist(customer);
    }
}
