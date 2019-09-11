package br.com.devmedia.appfinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("br.com.devmedia.appfinal")
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean(name = "jspViewResolver")
    public InternalResourceViewResolver internalResourceViewResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean           //O nome tem que ser messageSource, se não o Spring não acha
    public ResourceBundleMessageSource messageSource() {

        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();

        resource.setBasename("messages");

        return resource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/static/css/");

        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/static/js/");

        registry.addResourceHandler("/files/**", "/img/**", "images/**")
                .addResourceLocations("/WEB-INF/static/images/");
    }
}
