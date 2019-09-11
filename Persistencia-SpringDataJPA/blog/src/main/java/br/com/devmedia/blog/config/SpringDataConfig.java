package br.com.devmedia.blog.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("br.com.devmedia.blog.repository")
@EnableTransactionManagement
@PropertySource(value = "classpath:/application.properties")
public class SpringDataConfig {

    @Autowired
    private Environment environment;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {

        JpaTransactionManager manager = new JpaTransactionManager();

        manager.setEntityManagerFactory(factory);
        manager.setJpaDialect(new HibernateJpaDialect());

        return manager;
    }

    //No curso tava dando pau no showSql, por isso criou um metodo fora
    public HibernateJpaVendorAdapter jpaVendorAdapter() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setShowSql(environment.getProperty("hibernate.show.sql", Boolean.class));
        adapter.setGenerateDdl(environment.getProperty("hibernate.ddl", Boolean.class));

        return adapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        //No curso tava dando pau no showSql, por isso criou um metodo fora
        //O padrão é aqui
        /*HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);*/

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setPackagesToScan(environment.getProperty("hibernate.package.scan"));
        factoryBean.setDataSource(dataSource());
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUsername(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.pass"));
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClass"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));

        return dataSource;
    }
}
