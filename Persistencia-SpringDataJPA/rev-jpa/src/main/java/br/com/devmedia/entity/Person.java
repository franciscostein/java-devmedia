package br.com.devmedia.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PEOPLE",
        /*uniqueConstraints = {@UniqueConstraint(columnNames = "FIRST_NAME, LAST_NAME", name = "IDX_1")},*/
        /*indexes só funciona da JPA 2.1 pra cima, não permite um nome completo identico*/
        indexes = {@Index(columnList = "FIRST_NAME, LAST_NAME", name = "IDX_PERSON_NAME", unique = true)})
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERSON")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 60)
    private String lastName;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    //FetchType por padrão JPA toda vez que terminar em One é Eager, ex OneToOne, ManyToOne, do contrario é Lazy
    //O padrão já é Eager, não precisaria preencher, só tá de exemplo
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "DOCUMENT_ID")
    private Document document;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Phone> phones;

    @ManyToMany(cascade = CascadeType.ALL/*, fetch = FetchType.EAGER*/)
    @JoinTable(
            name = "PERSON_ADDRESSES",
            joinColumns = @JoinColumn(name = "ID_PERSON"),
            inverseJoinColumns = @JoinColumn(name = "ID_ADDRESS")
    )
    private List<Address> addresses;

    //Metodo que já adiciona um telefone com o id da pessoa
    public void addPhone(Phone phone) {

        if (phones == null) {

            phones = new ArrayList<Phone>();
        }

        phone.setPerson(this);
        phones.add(phone);
    }

    public void delPhone(Phone phone) {

        if (phones != null) {

            phones.remove(phone);
        }
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", document=" + document +
                '}';
    }
}