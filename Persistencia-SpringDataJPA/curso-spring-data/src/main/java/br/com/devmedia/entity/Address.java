package br.com.devmedia.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ADDRESSES")
@NamedQuery(
        name = "Address.buscaPorCidade",
        query = "select a from Address a where a.city like ?1"
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Address.buscaPorEndereco",
                query = "SELECT * FROM ADDRESSES WHERE CITY LIKE ?1 AND STREET LIKE ?2",
                resultClass = Address.class
        ),
        @NamedNativeQuery(
                name = "Address.functionConcatenaEndereco",
                query = "SELECT funcConcatAddress(?1)"
        )
})
public class Address implements Serializable {

    public enum TypeAddress {
        COMERCIAL, RESIDENCIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ADDRESS")
    private Long id;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_ADDRESS", nullable = false)
    private TypeAddress type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERSON_ADDRESSES",
            joinColumns = @JoinColumn(name = "ID_ADDRESS"),
            inverseJoinColumns = @JoinColumn(name = "ID_PERSON")
    )
    private List<Person> personList;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public TypeAddress getType() {
        return type;
    }

    public void setType(TypeAddress type) {
        this.type = type;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", type=" + type +
                ", personList=" + personList +
                '}';
    }
}