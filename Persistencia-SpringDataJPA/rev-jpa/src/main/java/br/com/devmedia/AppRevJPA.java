package br.com.devmedia;

import br.com.devmedia.dao.AddressDAO;
import br.com.devmedia.dao.DocumentDAO;
import br.com.devmedia.dao.PersonDAO;
import br.com.devmedia.dao.PhoneDAO;
import br.com.devmedia.entity.Address;
import br.com.devmedia.entity.Document;
import br.com.devmedia.entity.Person;
import br.com.devmedia.entity.Phone;

import java.util.Arrays;
import java.util.List;

public class AppRevJPA {

    public static void main( String[] args ) {

        /*Scanner scanner = new Scanner(System.in);

        System.out.println( "Selecione uma opção" );
        System.out.println( "1 - Cadastrar pessoa" );
        System.out.println( "2 - Procurar pessoa por sobrenome" );
        Integer option = scanner.nextInt();

        if (option == 1) {

            System.out.println("Primeiro nome: ");
            String firstName = scanner.next();

            System.out.println("Sobrenome nome: ");
            String lastName = scanner.next();

            System.out.println("Idade: ");
            Integer age = scanner.nextInt();

            insertPerson(firstName, lastName, age);

        } else if (option == 2) {

            System.out.println("Digite o sobrenome: ");
            String lastName = scanner.next();

            findByLastName(lastName);
        }*/

        //updatePerson();
        //findByLastName("Costa");
        //insertDocument();
        //updateDocument();
        //findPersonByCpf();
        //insertPhone();
        //insertPhoneByPerson();
        //updatePhone();
        //updatePhoneByPerson();
        //deleteOnCascade();
        //updatePhoneByPerson();
        insertAddressByPerson();
        //insertPersonByAddress();
        //findByCity();
    }

    private static void findByCity() {

        List<Address> addresses = new AddressDAO().findByCity("Campinas - SP");

        for (Address address : addresses) {

            System.out.println(address.toString());
        }
    }

    private static void insertPersonByAddress() {

        Person person = new PersonDAO().findById(7L);

        Address address = new Address();
        address.setStreet("Rua Alvares Neto, 42");
        address.setCity("Campinas - SP");
        address.setType(Address.TypeAddress.COMERCIAL);
        address.setPersonList(Arrays.asList(person));

        AddressDAO dao = new AddressDAO();

        dao.save(address);

        System.out.println(dao.findById(address.getId()));
    }

    private static void insertAddressByPerson() {

        Address address1 = new Address();
        Address address2 = new Address();

        address1.setType(Address.TypeAddress.COMERCIAL);
        address1.setStreet("Rua das Arauracarias, 21");
        address1.setCity("São Paulo - SP");

        address2.setType(Address.TypeAddress.COMERCIAL);
        address2.setStreet("Travessa 24, 77");
        address2.setCity("Rio de Janeiro - RJ");

        Person person = new Person();

        person.setFirstName("Chiara");
        person.setLastName("Almeida");
        person.setAge(50);
        person.setDocument(new Document("125.144.911-66", 482413111));
        person.addPhone(new Phone(Phone.TypePhone.CELULAR, "(91) 98411-4881"));
        person.addPhone(new Phone(Phone.TypePhone.RESIDENCIAL, "(91) 3199-6557"));
        person.setAddresses(Arrays.asList(address1, address2));

        new PersonDAO().save(person);

        System.out.println(person.toString());
    }

    private static void deleteOnCascade() {

        PhoneDAO dao = new PhoneDAO();

        Phone phone = dao.findById(2L);

        phone.getPerson().delPhone(phone);

        dao.delete(phone);
    }

    private static void updatePhoneByPerson() {

        Person person = new PersonDAO().findById(1L);

        for (Phone phone : person.getPhones()) {

            System.out.println("1 - " + phone.toString());

            if (Phone.TypePhone.RESIDENCIAL == phone.getType()) {

                phone.setType(Phone.TypePhone.COMERCIAL);
            }
        }

        new PersonDAO().update(person);

        for (Phone phone : person.getPhones()) {

            System.out.println("2 - " + phone.toString());
        }
    }

    private static void updatePhone() {

        Person person = new PersonDAO().findById(3L);

        PhoneDAO dao = new PhoneDAO();

        Phone phone = dao.findById(3L);

        phone.setPerson(person);

        dao.update(phone);

        phone = dao.findById(phone.getId());

        System.out.println(phone.toString());
    }

    private static void insertPhoneByPerson() {

        Phone phone1 = new Phone(Phone.TypePhone.CELULAR, "(61) 99522-3273");
        Phone phone2 = new Phone(Phone.TypePhone.RESIDENCIAL, "(61) 2898-8551");

        Person person = new Person();
        person.setFirstName("Isaac");
        person.setLastName("Gomes");
        person.setAge(35);
        person.setDocument(new Document("402.949.154-51", 321995363));

        //phone1.setPerson(person);
        //phone2.setPerson(person);

        //person.setPhones(Arrays.asList(phone1, phone2));

        person.addPhone(phone1);
        person.addPhone(phone2);

        new PersonDAO().save(person);
    }

    private static void insertPhone() {

        Person person = new Person();

        person.setFirstName("Cauã");
        person.setLastName("Alves");
        person.setAge(37);
        person.setDocument(new Document("747.902.308-13", 102105741));

        Phone phone = new Phone(Phone.TypePhone.CELULAR, "(15) 99515-9706");

        phone.setPerson(person);

        PhoneDAO dao = new PhoneDAO();

        dao.save(phone);

        phone = dao.findById(phone.getId());

        System.out.println(phone.toString());
    }

    private static void insertPerson(String firstName, String lastName, Integer age) {

        Person p1 = new Person();

        p1.setFirstName(firstName);
        p1.setLastName(lastName);
        p1.setAge(age);

        new PersonDAO().save(p1);

        System.out.println(p1.toString());
    }

    private static void findByLastName(String lastName) {

        List<Person> people = new PersonDAO().findByLastName(lastName);

        for (Person person : people) {

            System.out.println(person.toString());
        }
    }

    private static void updatePerson() {

        Person p1 = new PersonDAO().findById(1L);

        p1.setAge(23);

        new PersonDAO().update(p1);

        System.out.println(p1.toString());
    }

    private static void deletePerson(Long id) {

        new PersonDAO().delete(id);
    }

    private static void insertDocument() {

        Person person = new Person();

        person.setFirstName("Mario");
        person.setLastName("Aguiar");
        person.setAge(33);
        person.setDocument(new Document("012.345.678-90", 123456789));

        new PersonDAO().save(person);

        System.out.println(person.toString());
    }

    private static void updateDocument() {

        Document doc = new DocumentDAO().findById(1L);

        doc.setCpf("012.345.678-09");

        new DocumentDAO().update(doc);

        System.out.println(new DocumentDAO().findById(1L).toString());
    }

    private static void findPersonByCpf() {

        Person person = new PersonDAO().findByCpf("012.345.678-09");

        System.out.println(person.toString());
    }
}