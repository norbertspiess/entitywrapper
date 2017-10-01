package de.norbertspiess.spring.boot.data.enumeration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocaleConverter implements AttributeConverter<Locale, String> {

    @Override
    public String convertToDatabaseColumn(Locale locale) {
        return locale.toString();
    }

    @Override
    public Locale convertToEntityAttribute(String localeString) {
        return Locale.valueOfShortcut(localeString);
    }

}