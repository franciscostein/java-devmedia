package br.com.devmedia;

import br.com.devmedia.config.SpringDataConfig;
import br.com.devmedia.entity.*;
import br.com.devmedia.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//@ImportResource(value = "spring-data.xml")//***MODO DE CONFIGURAÇÃO POR XML***
@Import({SpringDataConfig.class})
public class CursoSpringDataApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursoSpringDataApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        //testConfiguration();
        //testSave();
        //testUpdate();
        //testDelete();
        //testSavePeople();
        //testDeletePeople();
        //testFindAndSort();
        //testFindByIds();
        //testExists();
        //testPagination();

        //testByAge();
        //testByFirstNameLike();
        //testByAndOr();
        //testByBetween();
        //testByLastNameAndAgeBetween();
        //testByGreaterAndLess();
        //testByGreaterAndLessEqual();
        //testByFirstNameGreaterThan();
        //testByStartingAndEnding();
        //testByContaining();
        //testByAddressStartingAndEnding();
        //testByInAndNotIn();
        //testByOrderBy();
        //testIgnoreCase();
        //testByNotNullAndNull();
        //testPhonesByNumber();
        //testByAgeOrder();

        //findFirstName();
        //findFirstNameOrAge();
        //findFirstNameAndAge();
        //findByPersonByCPFEndsWith();
        //findPersonByAges();
        //findPersonByNames();
        //findDocumentByCpfStarts();
        //findAddressPorCidade();
        //findAddressPorEndereco();
        //testFunctionAddress();
        //testProcedureCPF();
        //updatePhones();
        //deletePhone();

        //findFirstLastName();
        //findTopAge();
        //findFirst3AndTop3();

        testUser();
    }

    private void testUser() {

        User user = new User();

        user.setUsername("leandro@gmail.com");
        user.setPassword("da9t3mng");

        if (user.isNew()) {

            userRepository.save(user);
        }

        User user1 = userRepository.findOne(1L);

        System.out.println(user1);
    }

    private void findFirst3AndTop3() {

        List<Person> people = personRepository.findFirst3ByOrderByLastNameAsc();

        people.forEach(System.out::println);

        List<Person> people2 = personRepository.findTop3ByOrderByAgeAsc();

        people2.forEach(System.out::println);
    }

    private void findTopAge() {

        Person person = personRepository.findTopByOrderByAgeAsc();

        System.out.println(person.toString());

        Person person2 = personRepository.findTopByOrderByAgeDesc();

        System.out.println(person2.toString());
    }

    private void findFirstLastName() {

        Person person = personRepository.findFirstByOrderByLastNameAsc();

        System.out.println(person.toString());

        Person person2 = personRepository.findFirstByOrderByLastNameDesc();

        System.out.println(person2.toString());
    }

    private void deletePhone() {

        int result = phoneRepository.deleteByPhoneNumber("32145687");

        System.out.println(result);
    }

    private void updatePhones() {

        int result1 = phoneRepository.setPhoneNumber("32312425", 1L);

        System.out.println(result1);

        int result2 = phoneRepository.setPhoneNumber(Phone.TypePhone.RESIDENCIAL, 1L);

        System.out.println(result2);
    }

    private void testProcedureCPF() {

        String cpf1 = documentRepository.replaceCPF(2L);

        System.out.println(cpf1);

        String cpf2 = documentRepository.procedureReplaceCPF(3L);

        System.out.println(cpf2);
    }

    private void testFunctionAddress() {

        String string1 = addressRepository.functionConcatenaEndereco(1L);

        System.out.println(string1);

        String string2 = addressRepository.functionNativeQueryConcatenaEndereco(2L);

        System.out.println(string2);
    }

    private void findAddressPorEndereco() {

        Address address1 = addressRepository.buscaPorEndereco("São Paulo", "Rua tres");

        System.out.println(address1.toString());

        Address address2 = addressRepository.buscaPorCidadeRua("Campians", "Rua dez");

        System.out.println(address2.toString());
    }

    private void findAddressPorCidade() {

        List<Address> addresses = addressRepository.buscaPorCidade("Campinas");

        addresses.forEach(System.out::println);
    }

    private void findDocumentByCpfStarts() {

        List<Document> documents = documentRepository.findByCpfStartsWith("425");

        documents.forEach(System.out::println);
    }

    private void findPersonByNames() {

        List<Person> personList = personRepository.findByFirstNames("Leandro", "João", "Clodoaldo", "Walmir");

        personList.forEach(System.out::println);
    }

    private void findPersonByAges() {

        List<Person> personList = personRepository.findByAgeBetween(20, 30);

        personList.forEach(System.out::println);
    }

    private void findByPersonByCPFEndsWith() {
        List<Person> personList = personRepository.findByDocumentCPFEndsWith("09");

        personList.forEach(System.out::println);
    }

    private void findFirstNameAndAge() {

        List<Person> personList = personRepository.findByFirstNameAndAge(29,"Aline");

        personList.forEach(System.out::println);
    }

    private void findFirstNameOrAge() {

        List<Person> personList = personRepository.findByFirstNameOrAge("Aline", 25);

        personList.forEach(System.out::println);
    }

    private void findFirstName() {

        List<Person> personList = personRepository.findByFirstName("Aline");

        personList.forEach(System.out::println);
    }

    private void testByAgeOrder() {

        List<Person> people = personRepository.findByAgeLessThanOrderByFirstNameAscLastNameAsc(25);

        people.forEach(System.out::println);
    }

    private void testPhonesByNumber() {

        List<Person> people = personRepository.findByPhonesNumberStartingWith("324");

        people.forEach(System.out::println);
    }

    private void testByNotNullAndNull() {

        List<Person> people1 = personRepository.findByDocumentIsNull();

        people1.forEach(System.out::println);

        List<Person> people2 = personRepository.findByDocumentIsNotNull();

        people2.forEach(System.out::println);
    }

    private void testIgnoreCase() {

        List<Person> people = personRepository.findByFirstNameIgnoreCase("leandro");

        people.forEach(System.out::println);
    }

    private void testByOrderBy() {

        List<Address> addresses = addressRepository.findByCityOOrderByTypeDesc("Campinas");

        addresses.forEach(System.out::println);
    }

    private void testByInAndNotIn() {

        List<Person> people1 = personRepository.findByAgeIn(32, 30, 23, 25);

        people1.forEach(System.out::println);

        List<Person> people2 = personRepository.findByAgeNotIn(32, 30, 23, 25);

        people1.forEach(System.out::println);
    }

    private void testByAddressStartingAndEnding() {

        List<Address> addresses = addressRepository.findByCityStartingWithOrStreetEndingWith("São", "13");

        addresses.forEach(System.out::println);
    }

    private void testByContaining() {

        List<Address> addresses = addressRepository.findByStreetContaining("Tessari");

        addresses.forEach(System.out::println);
    }

    private void testByStartingAndEnding() {

        List<Address> addresses1 = addressRepository.findByCityStartingWith("Cam");

        addresses1.forEach(System.out::println);

        List<Address> addresses2 = addressRepository.findByStreetEndingWith("38");

        addresses2.forEach(System.out::println);
    }

    private void testByFirstNameGreaterThan() {

        List<Person> people = personRepository.findByFirstNameGreaterThan("L");

        people.forEach(System.out::println);
    }

    private void testByGreaterAndLessEqual() {

        List<Person> people1 = personRepository.findByAgeGreaterThanEqual(25);

        people1.forEach(System.out::println);

        List<Person> people2 = personRepository.findByAgeLessThanEqual(30);

        people2.forEach(System.out::println);
    }

    private void testByGreaterAndLess() {

        List<Person> people1 = personRepository.findByAgeGreaterThan(25);

        people1.forEach(System.out::println);

        List<Person> people2 = personRepository.findByAgeLessThan(30);

        people2.forEach(System.out::println);
    }

    private void testByLastNameAndAgeBetween() {

        List<Person> people = personRepository.findByLastNameAndAgeBetween("Costa", 15, 35);

        people.forEach(System.out::println);
    }

    private void testByBetween() {

        List<Person> people = personRepository.findByAgeBetween(20, 30);

        people.forEach(System.out::println);
    }

    private void testByAndOr() {

        Person person = personRepository.findByFirstNameAndLastName("Leandro", "Costa");

        System.out.println(person.toString());

        List<Person> people = personRepository.findByAgeOrFirstName(32, "Isis");

        people.forEach(System.out::println);
    }

    private void testByFirstNameLike() {

        List<Person> people1 = personRepository.findByFirstNameLike("Leandro");

        people1.forEach(System.out::println);

        List<Person> people2 = personRepository.findByFirstNameNotLike("Aline");

        people2.forEach(System.out::println);
    }

    private void testByAge() {

        List<Person> people1 = personRepository.findByAge(33);

        people1.forEach(System.out::println);

        List<Person> people2 = personRepository.findByAgeNot(30);

        people2.forEach(System.out::println);
    }

    private void testPagination() {

        Page<Person> personPage =
                personRepository.findAll(new PageRequest(0, 3));

        personPage.getContent().forEach(System.out::println);

        personPage = personRepository.findAll(new PageRequest(1, 3));
        personPage.getContent().forEach(System.out::println);

        personPage = personRepository.findAll(new PageRequest(2, 3));
        personPage.getContent().forEach(System.out::println);
    }

    private void testExists() {

        boolean p1 = personRepository.exists(4L);

        if (p1 != false) {

            System.out.println("P1 exists");
        }
    }

    private void testFindByIds() {

        List<Person> people = personRepository.findAll(Arrays.asList(1L, 3L, 10L));

        people.forEach(System.out::println);
    }

    private void testFindAndSort() {

        Sort.Order orderAsc = new Sort.Order(Sort.Direction.ASC, "lastName");
        Sort.Order orderDesc = new Sort.Order(Sort.Direction.DESC, "firstName");

        //Primeiro e segundo critérios, em caso de empate
        Sort sort = new Sort(orderAsc, orderDesc);

        List<Person> people = personRepository.findAll(sort);

        people.forEach(System.out::println);
    }

    private void testDeletePeople() {

        Person person1 = personRepository.findOne(35L);
        Person person2 = personRepository.findOne(36L);
        Person person3 = personRepository.findOne(37L);

        personRepository.delete(Arrays.asList(person1, person2, person3));

        Person person4 = personRepository.findOne(38L);
        Person person5 = personRepository.findOne(39L);
        Person person6 = personRepository.findOne(40L);

        //deleteInBatch é melhor porque usa uma única transação
        personRepository.deleteInBatch(Arrays.asList(person4, person5, person6));
    }

    private void testSavePeople() {

        Person person1 = new Person();

        person1.setFirstName("Alisson");
        person1.setLastName("Tyler");
        person1.setAge(31);
        person1.setDocument(new Document("741.852.963-25", 407658234));

        Person person2 = new Person();

        person2.setFirstName("Frederich");
        person2.setLastName("Müller");
        person2.setAge(47);
        person2.setDocument(new Document("741.893.963-25", 407128234));

        Person person3 = new Person();

        person3.setFirstName("Robert");
        person3.setLastName("Kubicca");
        person3.setAge(37);
        person3.setDocument(new Document("721.852.963-25", 413658234));

        Person person4 = new Person();

        person4.setFirstName("Patrick");
        person4.setLastName("Smith");
        person4.setAge(41);
        person4.setDocument(new Document("741.852.963-15", 507658234));

        Person person5 = new Person();

        person5.setFirstName("Geralt");
        person5.setLastName("of Rivia");
        person5.setAge(136);
        person5.setDocument(new Document("700.852.963-25", 400008234));

        Person person6 = new Person();

        person6.setFirstName("Alice");
        person6.setLastName("Jackson");
        person6.setAge(69);
        person6.setDocument(new Document("718.852.963-25", 427658234));

        //Modo convencional
        /*List<Person> people = Arrays.asList(person1, person2, person3, person4, person5, person6);

        for (Person person : people) {

            personRepository.save(person);
        }*/
        //Modo normal

        //Modo Iterable do Spring
        List<Person> people = personRepository.save(Arrays.asList(person1, person2, person3, person4, person5, person6));

        people.forEach(System.out::println);
    }

    private void testDelete() {

        personRepository.delete(11L);

        Person person = personRepository.findOne(10L);

        personRepository.delete(person);

        List<Person> people = personRepository.findAll();

        people.forEach(System.out::println);
    }

    private void testUpdate() {

        Person person = personRepository.findOne(11L);

        System.out.println(person.toString());

        person.setLastName("Dias");

        personRepository.save(person);

        Person p2 = personRepository.findOne(11L);

        System.out.println(p2.toString());
    }

    private void testSave() {

        Person person = new Person();

        person.setFirstName("João");
        person.setLastName("Ribeiros");
        person.setAge(32);
        person.setDocument(new Document("547.698.247-54", 123456789));

        Address address = new Address();

        address.setCity("Manaus - AM");
        address.setStreet("Rua das Valquirias, 38");
        address.setType(Address.TypeAddress.RESIDENCIAL);

        person.setAddresses(Arrays.asList(address));
        person.addPhone(new Phone(Phone.TypePhone.RESIDENCIAL, "312-5687"));

        personRepository.save(person);

        Person p2 = personRepository.findOne(person.getId());

        System.out.println(p2.toString());
    }

    private void testConfiguration() {

        long total = personRepository.count();

        System.out.println("Total of people: " + total);

        List<Person> people = personRepository.findAll();

        people.forEach(System.out::println);
    }
}
