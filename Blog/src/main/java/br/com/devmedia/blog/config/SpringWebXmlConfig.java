package br.com.devmedia.blog.config;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class SpringWebXmlConfig implements WebApplicationInitializer {


    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();

        servletContext.addListener(new ContextLoaderListener(webApplicationContext));

        webApplicationContext.register(SpringMvcConfig.class);
        webApplicationContext.setServletContext(servletContext);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dispacher", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");

        //Mantem a sessão aberta enquanto ele carrega as categorias
        //Filtro para lidar com as exceções de sessão (LAZY)
        FilterRegistration.Dynamic inViewSession = servletContext.addFilter("Spring OpenEntityManagerInViewFilter", new OpenEntityManagerInViewFilter());
        inViewSession.setAsyncSupported(Boolean.TRUE);
        inViewSession.addMappingForUrlPatterns(null, true, "/*");

        //SpringSecurity
        FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        securityFilter.setAsyncSupported(true);
        securityFilter.addMappingForUrlPatterns(null, true, "/*");
    }
}
