package ru.darin.springcourse;

import java.util.List;

public class TranslatorResponse {

    private List<Translation>translations;

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }
}
