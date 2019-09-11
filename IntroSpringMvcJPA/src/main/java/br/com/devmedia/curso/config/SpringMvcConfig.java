package br.com.devmedia.curso.config;

import br.com.devmedia.curso.web.conversor.TipoSexoConverter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {  //Classe que diz como queremos usar o SpringMVC

    @Bean           //Através desse metodo dizemos como ele irá resolver as paginas
    public InternalResourceViewResolver viewResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");  //onde encontrar as views
        resolver.setSuffix(".jsp");     //Tipo de arquivo que trabalharemos
        resolver.setViewClass(JstlView.class);    //Qual tipo de recursos serão usados nas páginas

        return resolver;
    }

    @Override       //Metodo para adicionar o conversor de sexo ao Spring, extende da WebMvcConfigurerAdapter
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TipoSexoConverter()); //Sempre que encontrar o tipo sexo ele vai fazer a conversão
    }   //se tiver mais classes de conversão só adicionar aqui

    @Bean
    public MessageSource messageSource() {  //Configuração para mapear arquivos de propriedades
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages"); //messages.properties, sempre sem a extenção
        return source;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {     //Metodo para mapeamento do diretorio de recursos estaticos da aplicação, pois para o Spring tudo dentro de WEB-INF está protegido
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/resources/bootstrap/");
    }
}
