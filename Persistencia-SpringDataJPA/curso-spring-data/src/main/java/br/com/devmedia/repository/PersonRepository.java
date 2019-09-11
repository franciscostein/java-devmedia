package br.com.devmedia.repository;

import br.com.devmedia.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findTop3ByOrderByAgeAsc();

    List<Person> findFirst3ByOrderByLastNameAsc();

    Person findTopByOrderByAgeAsc();

    Person findTopByOrderByAgeDesc();

    Person findFirstByOrderByLastNameAsc();

    Person findFirstByOrderByLastNameDesc();

    @Query("select p from Person p where p.firstName in :names order by p.age asc ")
    List<Person> findByFirstNames(@Param("names") String... firstNames);

    @Query("select p from Person p where p.age >= :min and p.age <= :max")
    List<Person> findByAgeBetween(@Param("min") Integer start, @Param("max") Integer end);

    @Query("select p from Person p where p.document.cpf like %?1")
    List<Person> findByDocumentCPFEndsWith(String value);

    @Query("select p from Person p where p.firstName like ?2 and p.age = ?1")
    List<Person> findByFirstNameAndAge(Integer age, String firstName);

    @Query("select p from Person p where p.firstName like ?1 or p.age = ?2")
    List<Person> findByFirstNameOrAge(String firstName, Integer age);

    @Query("select p from Person p where p.firstName like ?1")
    List<Person> findByFirstName(String firstName);

    //Busca por idade maior que o parametro e ordena primeiro pelo nome depois sobrenome
    List<Person> findByAgeLessThanOrderByFirstNameAscLastNameAsc(Integer age);

    //Busca por numero de telefone da pessoa
    List<Person> findByPhonesNumberStartingWith(String number);

    //Busca por pessoas que tenham documento
    List<Person> findByDocumentIsNotNull();

    //Busca pessoas sem documentos
    List<Person> findByDocumentIsNull();;

    //Busca por nome ignorando letra maiuscula e minusculas
    List<Person> findByFirstNameIgnoreCase(String firstName);

    //Busca as idades diferentes da lista passada
    List<Person> findByAgeNotIn(Integer... ages);

    //Busca idade por uma lista de parametros
    List<Person> findByAgeIn(Integer... ages);

    //Busca nome maior que o valor informado
    List<Person> findByFirstNameGreaterThan(String firstName);

    //Busca por idade igual e menor que o paramtro
    List<Person> findByAgeLessThanEqual(Integer age);

    //Busca por idade igual e maior que o parametro
    List<Person> findByAgeGreaterThanEqual(Integer age);

    //Busca por idade menor que o paramtro
    List<Person> findByAgeLessThan(Integer age);

    //Busca por idade maior que o parametro
    List<Person> findByAgeGreaterThan(Integer age);

    //Busca por último nome e entre as idades
    List<Person> findByLastNameAndAgeBetween(String lastName, int min, int max);

    //Busca por idade, inclui a idade minima e maxima
    List<Person> findByAgeBetween(int min, int max);

    //Busca por idade ou primeiro nome por parametros
    List<Person> findByAgeOrFirstName(Integer age, String firstName);

    //Busca a pessoa por primeiro e segundo nome por parametros
    Person findByFirstNameAndLastName(String firstName, String lastName);

    //Busca por firstName igual ao parametro
    List<Person> findByFirstNameLike(String firstName);

    //Busca por nomes diferentes
    List<Person> findByFirstNameNotLike(String firstName);

    //Buscar por idade igual ao parametro fornecido
    List<Person> findByAge(Integer age);

    //Busca por idade diferente do parametro fornecido
    List<Person> findByAgeNot(Integer age);
}
