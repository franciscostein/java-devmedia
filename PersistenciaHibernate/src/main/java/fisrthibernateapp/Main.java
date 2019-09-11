package fisrthibernateapp;

import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager em;

    public static void main(String[] args) {

        Main main = new Main();

        main.novaPessoaJuridica();
    }

    public static EntityManager criarEntityManager() {

        if (em == null) {

            entityManagerFactory = Persistence.createEntityManagerFactory("hibernateapp");

            em = entityManagerFactory.createEntityManager();
        }

        return em;
    }

    public Date getDataAtual() {

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();

        calendar.setTime(date);

        return calendar.getTime();
    }

    public void cadastrarProduto() {

        criarEntityManager();

        Users user = em.find(Users.class, 4);

        Products product = new Products();

        product.setId(6);
        product.setName("Grampo");
        product.setOwner(user);
        product.setStock(14);

        try {

            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void alterarProduto() {

        criarEntityManager();

        Products product = em.find(Products.class, 1);

        //product.setName(product.getName());
        //product.setOwner(product.getOwner());
        product.setStock(17);

        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void vender() {

        criarEntityManager();

        Invoice invoice = new Invoice();

        Users vendor = em.find(Users.class, 3);

        invoice.setId(2);
        invoice.setDateOfSale(getDataAtual());
        invoice.setVendor(vendor);
        invoice.setTypeOfInvoice(TypeOfInvoice.OLD);

        InvoiceItens invoiceItem = new InvoiceItens();

        Products product = em.find(Products.class, 2);

        invoiceItem.setId(2);
        invoiceItem.setInvoice(invoice);
        invoiceItem.setAmount(4);
        //invoiceItem.setProduct(em.find(Products.class, 1));
        invoiceItem.setProduct(product);
        invoice.addItem(invoiceItem);

        try {
            em.getTransaction().begin();
            em.persist(invoice);
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();

            e.printStackTrace();
            System.out.println(e.getMessage());

        } finally {
            em.close();
        }
    }

    public void criarUsuario() {

        criarEntityManager();

        Users usuario = new Users();

        usuario.setId(4);
        usuario.setName("Alice");

        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void alterarUsuario() {

        criarEntityManager();

        Users user = em.find(Users.class, 3);

        user.setName("Hans Müller");

        //Com query, serve pra DELETE e UPDATE
        //Ta dando erro, mas serve pra ter uma ideia de como fazer um update na mão, sem merge
        //Também ver quantos registros foram afetados
        /*int affected = em.createQuery("UPDATE Users set name = :name where id = :id")
                .setParameter("name", "Hans Müller")
                .setParameter("id", 3)
                .executeUpdate();

        System.out.println("Número de registros afetados: " + affected);*/
        //Serve para dar um refresh em objetos que ficaram na memoria e o Hibernate ainda não está mostrando
        //em.refresh(user);

        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void usuario() {

        criarEntityManager();

        Products produto = new Products();
        Users owner = new Users();

        owner.setId(2);
        owner.setName("Mark");

        //Users owner = em.find(Users.class, 1);

        produto.setId(2);
        produto.setName("Tapete");
        produto.setOwner(owner);
        produto.setStock(15);

        try {
            em.getTransaction().begin();
            //Ta usando cascade.all, então ele já cria em users
            //em.persist(owner);
            em.persist(produto);
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();

            e.printStackTrace();
            System.out.println(e.getMessage());

        } finally {
            em.close();
        }

        System.out.println("Produto cadastrado: " + produto.getName());
        System.out.println("Dono do produto: " + produto.getOwner().getName());
    }

    public void buscaUsuario() {

        criarEntityManager();

        List<Users> users = em.createQuery("SELECT USR FROM Users USR").getResultList();

        System.out.println("Número de usuários: " + users.size());

        //Distinct tras registros unicos, se tiver mais de um vendedor tras só uma vez ele (tipo group by)
        List<Users> usuariosComVendas = em.createQuery("select distinct(inv.vendor) from Invoice inv").getResultList();

        System.out.print("Usuários com vendas: ");

        if (usuariosComVendas != null) {
            usuariosComVendas.forEach(System.out::println);
        }

        //Com o get da pra pegar qualquer posição do vetor, 1 por exemplo seria o segundo
        System.out.println("Nome do primeiro usuário: " + usuariosComVendas.get(0).getName());


        //singleResult ou uniqueResult
        Users usuario = (Users) em.createQuery("select usr from Users usr where usr.id = :id")
                .setParameter("id", 2)
                .getSingleResult();

        System.out.println("Usuário com id 2: " + usuario.getName());
    }

    //Usa uma tabela de relacionamento a Users_Groups
    public void adicionarGrupoUsuario() {

        criarEntityManager();

        Users user = em.find(Users.class, 3);
        Groups group = em.find(Groups.class, 1);

        user.getGroups().add(group);
        group.getUsers().add(user);

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.persist(group);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        System.out.println("Usuario 3 tem: " + user.getGroups().size() + " grupos");
        System.out.println("O grupo 1 tem: " + group.getUsers().size() + " usuários");
    }

    public void adicionarGrupo() {

        criarEntityManager();

        Groups group = new Groups();

        group.setId(1);
        group.setName("Administrador");

        try {
            em.getTransaction().begin();
            em.persist(group);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //Pesquisar criteria queries, no padrão JPA 2.0 com EntityManager é bem diferente do curso
    /*public void buscaUsuarioComCriterio() {

        criarEntityManager();

        CriteriaBuilder criteria = em.getCriteriaBuilder();

        criteria.
    }*/

    public void buscaVendas() {

        criarEntityManager();

        List<InvoiceItens> invoiceItens = em.createQuery("select invi from InvoiceItens invi where invi.id = :id")
                .setParameter("id", 1)
                .getResultList();

        System.out.println("Vendas do item 1: " + invoiceItens.size());

        List<InvoiceItens> vendasPor = em.createQuery("select invi from InvoiceItens invi where invi.invoice.vendor.name like :nameOf")
                .setParameter("nameOf", "%Leandro%")
                .getResultList();

        System.out.println("Leandro realizou: " + vendasPor.size() + " vendas");
    }

    public void criarCompositeId() {

        criarEntityManager();

        MyIdForCompositeTest id = new MyIdForCompositeTest();
        CompositeIdTest compositeId = new CompositeIdTest();

        id.setId(1);
        id.setSeq(1);

        compositeId.setId(id);
        compositeId.setTestName("Test 1");

        try {
            em.getTransaction().begin();
            em.persist(compositeId);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void buscarCompositeId() {

        criarEntityManager();

        MyIdForCompositeTest id = new MyIdForCompositeTest();

        id.setId(1);
        id.setSeq(1);

        CompositeIdTest compositeIdTest = em.find(CompositeIdTest.class, id);

        //Com construtor
        CompositeIdTest compositeIdTest1 = em.find(CompositeIdTest.class, new MyIdForCompositeTest(1, 1));

        System.out.println("CompisteId name: " + compositeIdTest1.getTestName());
    }

    public void novoAnimal() {

        criarEntityManager();

        /*Dog dog = new Dog();

        dog.setId(1);
        dog.setName("Hades");
        dog.setPedigree("Siberian Husky");*/

        Pig pig = new Pig();

        pig.setId(2);
        pig.setName("Piggy Pig");
        pig.setDisease("neurocisticercose");

        try {
            em.getTransaction().begin();
            em.persist(pig);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void buscarCachorro() {

        criarEntityManager();

        List<Dog> doggos = em.createQuery("select dog from Dog dog").getResultList();

        System.out.println("Número de cachorros na tabela de animais: " + doggos.size());
    }

    public void buscarPorco() {

        criarEntityManager();

        List<Pig> pigs = em.createQuery("select pig from Pig pig").getResultList();

        System.out.println("Número de porcos na tabela de animais: " + pigs.size());
    }

    public void novoAmanteDeMusica() {

        criarEntityManager();

        MusicLover musicLover = new MusicLover();

        musicLover.setId(1);
        musicLover.setName("Leandro");
        musicLover.setAge(23);
        musicLover.setIdealMusic("Death Metal");

        try {
            em.getTransaction().begin();
            em.persist(musicLover);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void novoAmanteDeCinema() {

        criarEntityManager();

        CineLover cineLover = new CineLover();

        cineLover.setId(2);
        cineLover.setName("Isis");
        cineLover.setAge(30);
        cineLover.setBestMovie("The Fountain");

        try {
            em.getTransaction().begin();
            em.persist(cineLover);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void novaPessoaFisica() {

        criarEntityManager();

        NaturalPerson naturalPerson = new NaturalPerson();

        naturalPerson.setId(1);
        naturalPerson.setName("Leandro");
        naturalPerson.setCpf("425.370.338-09");

        try {
            em.getTransaction().begin();
            em.persist(naturalPerson);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void novaPessoaJuridica() {

        criarEntityManager();

        LegalPerson legalPerson = new LegalPerson();

        legalPerson.setId(1);
        legalPerson.setName("Leandro Serviços de Informática LTDA");
        legalPerson.setCnpj("59.559.174/0001-31");

        try {
            em.getTransaction().begin();
            em.persist(legalPerson);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
