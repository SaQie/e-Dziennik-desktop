package pl.edziennik.client.configuration.converter;

import java.util.Locale;

public class PropertiesLanguageConverter implements PropertiesValueConverter<Locale>{


    public static final Locale LOCALE_EN = new Locale("en", "EN");
    public static final Locale LOCALE_PL = new Locale("pl", "PL");

    @Override
    public Locale convertValue(String value) {
        switch (value){
            case "Polski" -> {
                return LOCALE_PL;
            }
            case "English" -> {
                return LOCALE_EN;
            }
            default -> {
                return null;
            }
        }
    }
}
