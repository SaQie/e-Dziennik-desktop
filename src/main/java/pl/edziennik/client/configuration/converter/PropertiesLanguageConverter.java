package pl.edziennik.client.configuration.converter;

import java.util.Locale;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class PropertiesLanguageConverter implements PropertiesValueConverter<Locale>{

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
