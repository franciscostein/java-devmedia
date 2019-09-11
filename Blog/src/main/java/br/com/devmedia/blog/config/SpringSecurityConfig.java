package br.com.devmedia.blog.config;

import br.com.devmedia.blog.Service.UsuarioLogadoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioLogadoDetailService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/", "/Blog", "/Blog/", "/{permalink}", "/Blog/{permalink}",
                        "/search/**", "/autor/{id}/page/{page}", "/Blog/autor/{id}/page/{page}",
                        "/categoria/{link}/page/{page}", "/Blog/categoria/{link}/page/{page}", "/page/{page}", "/Blog/page/{page}",
                        "/avatar/load/{id}", "/auth/**", "/usuario/add", "/usuario/save").permitAll()

                /*.antMatchers("/autor/add").hasAuthority("AUTOR") Se quiser usar o bloqueio de links aqui, mas continuara aparecendo na tela*/

                .anyRequest().authenticated()

            .and()
                .formLogin()
                .loginPage("/auth/login")
                .failureUrl("/auth/login?error=true")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/auth/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            .and()
                .exceptionHandling().accessDeniedPage("/auth/denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
}
