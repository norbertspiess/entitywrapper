package de.norbertspiess.spring.boot.entitywrapper.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Locale {
    ENGLISH("EN"),
    GERMAN("DE");

    private String en;

    Locale(String en) {
        this.en = en;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.en;
    }

    public static Locale valueOfShortcut(String shortCut) {
        for (Locale locale : values()) {
            if (locale.toString().equals(shortCut)) {
                return locale;
            }
        }
        throw new IllegalArgumentException("unknown");
    }


}
