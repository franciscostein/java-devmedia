package br.com.devmedia.blog.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    //USA ESSE CONVERSOR PORUQE NA JPA DO CURSO NÃO TRABALHA COM LOCALDATE

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {

        return Date.valueOf(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {

        return date.toLocalDate();
    }
}
