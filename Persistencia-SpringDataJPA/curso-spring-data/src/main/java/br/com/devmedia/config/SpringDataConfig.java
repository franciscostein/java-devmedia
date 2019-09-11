package br.com.devmedia.config;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
@EnableJpaRepositories(basePackages = "br.com.devmedia.repository")
@EnableTransactionManagement
//@PropertySource(value = "classpath:resources/application.properties")
public class SpringDataConfig {

    //@Value(value = "${jdbc.user")
    @Value(value = "root")
    private String username;

    //@Value(value = "${jdbc.pass")
    @Value(value = "masterkey")
    private String password;

    //@Value(value = "${jdbc.driver")
    @Value(value = "com.mysql.jdbc.Driver")
    private String driver;

    //@Value(value = "${jdbc.url")
    @Value(value = "jdbc:mysql://localhost:3306/rev_jpa_db")
    private String url;

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

        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);

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
        factoryBean.setPackagesToScan("br.com.devmedia.entity");
        factoryBean.setDataSource(dataSource());
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);

        return dataSource;
    }
}
