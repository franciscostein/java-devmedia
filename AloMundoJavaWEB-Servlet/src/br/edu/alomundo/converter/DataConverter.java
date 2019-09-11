package br.edu.alomundo.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverter implements Converter {

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public String converter(Object object) {

        Date data = (Date) object;

        return dateFormat.format(data);
    }

    public Object converter(String valor) {

        Date data = null;

        try {

            data = dateFormat.parse(valor);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        return data;
    }
}
