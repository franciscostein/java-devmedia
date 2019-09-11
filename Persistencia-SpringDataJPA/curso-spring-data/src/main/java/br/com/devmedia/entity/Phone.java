package br.com.devmedia.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PHONES")
public class Phone implements Serializable {

    public enum TypePhone {
        RESIDENCIAL, CELULAR, COMERCIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PHONE")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_PHONE", nullable = false)
    private TypePhone type;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    public Phone() {
    }

    public Phone(TypePhone type, String number) {
        this.type = type;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypePhone getType() {
        return type;
    }

    public void setType(TypePhone type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        return id.equals(phone.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", type=" + type +
                ", number='" + number + '\'' +
                ", person=" + person +
                '}';
    }
}