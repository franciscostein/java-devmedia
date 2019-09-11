package br.com.devmedia.dao;

import br.com.devmedia.entity.Person;

import java.util.List;

public class PersonDAO extends GenericDAO<Person> {

    public PersonDAO() {

        super(Person.class);
    }

    public List<Person> findByLastName(String lastName) {

        String jpql = "from Person p where p.lastName like ?";

        return find(jpql, lastName);
    }

    public Person findByFullName(String firstName, String lastName) {

        String jpql = "from Person p where p.firstName like ? and p.lastName like ?";

        return findOne(jpql, firstName, lastName);
    }

    public List<Person> findAgeInBetween(int min, int max) {

        String jpql = "from Person p where p.age between ? and ?";

        return find(jpql, min, max);
    }

    public Person findByCpf(String cpf) {

        String jpql = "select p from Person p, Document d where d.cpf like ? "
                + "and p.document.id = d.id";

        return findOne(jpql, cpf);
    }
}
