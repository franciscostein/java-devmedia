package br.com.devmedia.curso.config;

import br.com.devmedia.curso.web.conversor.TipoSexoConverter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration                                                  //ApplicationContextAware nos retorna um objeto do contexto da aplicação
public class SpringMvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {  //Classe que diz como queremos usar o SpringMVC

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean           //Através desse metodo dizemos como ele irá resolver as paginas
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");  //onde encontrar as views
        templateResolver.setSuffix(".html");     //Tipo de arquivo que trabalharemos
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);   //Usar false para desenvolvimento, por padrão é true, habilita cache no lado cliente, melhorando a experiencia
        return templateResolver;
    }

    @Bean       //Informa as configurações do templateResolver para o núcleo do Thymeleaf
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.setTemplateEngineMessageSource(messageSource());
        templateEngine.addDialect(new Java8TimeDialect());  //Informa que utilizaremos recursos dessa biblioteca
        return templateEngine;
    }

    @Bean       //Metodo que reune todas as configurações em cascata
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {  //Configuração para mapear arquivos de propriedades
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages"); //messages.properties, sempre sem a extenção
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override       //Metodo para adicionar o conversor de sexo ao Spring, extende da WebMvcConfigurerAdapter
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TipoSexoConverter()); //Sempre que encontrar o tipo sexo ele vai fazer a conversão
    }   //se tiver mais classes de conversão só adicionar aqui

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {     //Metodo para mapeamento do diretorio de recursos estaticos da aplicação, pois para o Spring tudo dentro de WEB-INF está protegido
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/resources/");
    }
}
