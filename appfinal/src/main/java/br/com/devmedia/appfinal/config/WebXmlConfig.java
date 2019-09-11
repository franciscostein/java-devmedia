package br.com.devmedia.appfinal.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//Usando modo programatico no lugar do web.xml
public class WebXmlConfig implements WebApplicationInitializer{

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();

        webContext.register(MvcConfig.class);
        webContext.setServletContext(servletContext);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);

        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        ServletRegistration.Dynamic reDynamic = servletContext.addServlet("dispacher", dispatcherServlet);

        reDynamic.setLoadOnStartup(1);
        reDynamic.addMapping("/");
    }
}
