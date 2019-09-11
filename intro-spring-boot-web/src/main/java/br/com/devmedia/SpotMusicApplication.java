package br.com.devmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpotMusicApplication {

    public static void main(String[] args) {    //Basta isso para iniciar, o SpringBoot cuida do resto
        SpringApplication.run(SpotMusicApplication.class, args);
    }
}
