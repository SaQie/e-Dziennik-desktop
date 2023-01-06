package pl.edziennik.client.configuration.converter;

import java.util.Locale;

public class PropertiesLanguageConverter implements PropertiesValueConverter<Locale>{

    @Override
    public Locale convertValue(String value) {
        switch (value){
            case "Polski" -> {
                return new Locale("pl", "PL");
            }
            case "English" -> {
                return new Locale("en", "EN");
            }
            default -> {
                return null;
            }
        }
    }
}
