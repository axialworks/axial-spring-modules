package com.axial.modules.commons.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonLangConstants {

    public static final Map<String, Locale> SUPPORTED_LANGUAGES = Map.ofEntries(
            Map.entry("tr", new Locale.Builder().setLanguage("tr").build()),
            Map.entry("en", new Locale.Builder().setLanguage("en").build())
    );

    private static final Locale DEFAULT_LANG = SUPPORTED_LANGUAGES.get("en");

    public static final Locale getLocaleByLangCode(String langCode) {
        return SUPPORTED_LANGUAGES.getOrDefault(langCode, DEFAULT_LANG);
    }

}
