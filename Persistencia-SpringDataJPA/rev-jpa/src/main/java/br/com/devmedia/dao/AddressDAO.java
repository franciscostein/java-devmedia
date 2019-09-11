package br.com.devmedia.dao;

import br.com.devmedia.entity.Address;

import java.util.List;

public class AddressDAO extends GenericDAO<Address> {

    public AddressDAO() {
        super(Address.class);
    }

    public List<Address> findByCity(String city) {

        String jpql = "from Address a where a.city like ?";

        return find(jpql, city);
    }
}