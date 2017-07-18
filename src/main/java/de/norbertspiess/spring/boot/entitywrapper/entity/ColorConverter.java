package de.norbertspiess.spring.boot.entitywrapper.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ColorConverter implements AttributeConverter<Locale, String> {

    @Override
    public String convertToDatabaseColumn(Locale locale) {
        return locale.toString();
    }

    @Override
    public Locale convertToEntityAttribute(String localeString) {
        return Locale.valueOfShortcut(localeString);
    }

}