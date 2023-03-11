package demo.devsu.converters;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    //convierte desde objetos tipo java a objetos tipo sql
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        //si es null, devuelve null sino devuelve el objeto convertido
        return (localDate == null ? null : Date.valueOf(localDate));
    }

    //convierte desde la base de datos a objetos java
    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        //si es null devuelve null sino devuelve el date
        return (date == null ? null : date.toLocalDate());
    }

}