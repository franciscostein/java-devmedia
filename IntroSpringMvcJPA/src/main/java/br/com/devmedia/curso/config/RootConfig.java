package br.com.devmedia.curso.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("br.com.devmedia.curso") //Irá scannear todos os pacotes dentro desse caminho para recursos do Spring
@EnableWebMvc
public class RootConfig {
}
