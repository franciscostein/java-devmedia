package br.com.devmedia.springmvc.model.dao;

import br.com.devmedia.springmvc.model.domain.Carro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarroDao {

    private static List<Carro> carros;

    public CarroDao() {
        criarCarros();
    }

    private void criarCarros() {
        if (carros == null) {
            carros = new ArrayList<Carro>();

            carros.add(new Carro("Focus", "Ford", 2016, "Laranja", "HTE-3158"));
            carros.add(new Carro("Linea", "Fiat", 2014, "Prata", "JUU-7810"));
            carros.add(new Carro("Jetta", "Volkswagen", 2015, "Dourado", "MYW-3253"));
            carros.add(new Carro("Cruze", "Chevrolet", 2017, "Amarelo", "HRG-4681"));
            carros.add(new Carro("Vantage Roadster 4.7 V8 420cv", "Aston Martin", 2011, "Cinza", "MBD-9348"));
            carros.add(new Carro("XF 5.0 32V V8 385cv Aut.", "Jaguar", 2010, "Prata", "IPL-9713"));
        }
    }

    public List<Carro> getCarros() {
        return carros;
    }

}
