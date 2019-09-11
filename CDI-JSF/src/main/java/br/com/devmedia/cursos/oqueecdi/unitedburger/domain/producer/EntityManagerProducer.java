package br.com.devmedia.cursos.oqueecdi.unitedburger.domain.producer;

import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.qualifier.ParceriasQualifier;
import br.com.devmedia.cursos.oqueecdi.unitedburger.domain.qualifier.UnitedBurgerQualifier;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {

    @Produces   // JPA precisa ser marcado com Produces pois são objetos complexos para ela criar, objetos simples não precisam
    @ApplicationScoped  // ApplicationScoped mostra pra CDI que só vai existir um EntityManagerFactory na aplicação
    @UnitedBurgerQualifier
    public EntityManagerFactory getUnitedBurgerEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("unitedburger");
    }

    @Produces   // CDI não se importa com o nome do metodo, e sim os tipos de paramentros e retornos
    @RequestScoped  // Um por requisição
    @UnitedBurgerQualifier
    public EntityManager getUnitedBurgerEntityManager(@UnitedBurgerQualifier EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    @Produces
    @ApplicationScoped
    @ParceriasQualifier
    public EntityManagerFactory getParceriasEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("parcerias");
    }

    @Produces
    @RequestScoped
    @ParceriasQualifier
    public EntityManager getParceriasEntityManager(@ParceriasQualifier EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    // Libera da memoria os objetos de tipo complexo EntityManager depois que já foram usados
    public void fecharEntityManager(@Disposes @Any EntityManager manager) {
        String url = (String) manager.getEntityManagerFactory().getProperties().get("javax.persistence.jdbc.url");
        // Imprime a conexão para testar
        System.out.println(url);

        if (manager.isOpen()) {
            manager.close();
        }
    }
}
