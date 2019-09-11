package br.com.devmedia.curso.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

                                //Extendendo essa classe fará ser a primeira inilializada no lugar do web.xml
public class SpringInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class[] {RootConfig.class}; //Quando iniciar será a plimeira configuração da aplicação a carregar
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class[] {SpringMvcConfig.class}; //Quando for trabalhar com servlet respeitará essas configs
    }

    @Override
    protected String[] getServletMappings() {

        return new String[] {"/"};  //Sempre que a aplicação encontrar uma barra saberá que está lidando com um servlet
    }
}
