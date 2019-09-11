package br.com.devmedia.blog.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    //USA ESSE CONVERSOR PORUQE NA JPA DO CURSO NÃO TRABALHA COM LOCALDATETIME

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {

        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {

        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
