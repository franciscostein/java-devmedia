package br.com.devmedia.appfinal.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@ComponentScan("br.com.devmedia.appfinal")
@PropertySource("classpath:datasource.properties")
@EnableTransactionManagement
public class DataBaseConfig {

    private static Logger logger = Logger.getLogger(DataBaseConfig.class);

    @Value("${datasource.url}")
    private String url;

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.driverClassName}")
    private String driverClassName;

    private DataSource dataSource;

    //Necessario sem o Spring Boot, é o primeiro Bean que o Spring vai ler
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySource() {

        return new PropertySourcesPlaceholderConfigurer();
    }

    private DataSource jndi() {

        JndiTemplate jndiTemplate = new JndiTemplate();

        DataSource dataSource = null;

        try {

            dataSource = (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/appfinal");

        } catch (NamingException e) {

            logger.error("NamingException para java:comp/env/jdbc/appfinal", e);
        }

        return dataSource;
    }

    private EmbeddedDatabase embeddedDatabase() {

        EmbeddedDatabase embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/h2/schema.sql")
                .addScript("classpath:/h2/data.sql")
                .build();

        return embeddedDatabase;
    }

    @Bean
    public DataSource dataSource() {

        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl(url);
        basicDataSource.setDriverClassName(driverClassName);

        return this.dataSource = basicDataSource;

        //return this.dataSource = jndi();

        //return this.dataSource = embeddedDatabase();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {

        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        return new DataSourceTransactionManager(this.dataSource);
    }
}
