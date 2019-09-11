package bean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaResourceBean {

    //Padrão singletown, static garante que só vai ser criado um na aplicação, recomendação da JPA
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {

        if (entityManagerFactory == null) {

            entityManagerFactory = Persistence.createEntityManagerFactory("lembrete");
        }

        return entityManagerFactory;
    }
}