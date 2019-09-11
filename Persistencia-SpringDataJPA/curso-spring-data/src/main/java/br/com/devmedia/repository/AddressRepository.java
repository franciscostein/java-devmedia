package br.com.devmedia.repository;

import br.com.devmedia.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "select funcConcatAddress(?1)", nativeQuery = true)
    String functionNativeQueryConcatenaEndereco(Long id);

    //Usando function com native query, foi criado a function no BD
    String functionConcatenaEndereco(Long id);

    @Query(
            value = "SELECT * FROM ADDRESSES WHERE CITY LIKE ?1 AND STREET LIKE ?2",
            nativeQuery = true
    )
    Address buscaPorCidadeRua(String city, String street);

    //Usando @NamedNativeQueries (SQL nativo, não HQL)
    Address buscaPorEndereco(String city, String street);

    //Usando @NamedQuery na entidade
    List<Address> buscaPorCidade(String cidade);

    //Busca por cidade e ordena por tipo
    List<Address> findByCityOOrderByTypeDesc(String city);

    //Busca por cidade ou rua
    List<Address> findByCityStartingWithOrStreetEndingWith(String city, String street);

    //Busca por rua conforme paramentro coincida com qualquer parte de rua
    List<Address> findByStreetContaining(String street);

    //Busca por rua pelo termino da palavra
    List<Address> findByStreetEndingWith(String street);

    //Busca por cidade começando com
    List<Address> findByCityStartingWith(String city);
}
