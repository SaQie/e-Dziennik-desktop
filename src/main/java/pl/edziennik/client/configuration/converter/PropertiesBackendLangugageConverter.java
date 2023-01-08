package pl.edziennik.client.configuration.converter;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class PropertiesBackendLangugageConverter implements PropertiesValueConverter<String>{


    @Override
    public String convertValue(String value) {
        switch (value){
            case "Polski" -> {
                return LOCALE_PL_STRING;
            }
            case "English" -> {
                return LOCALE_EN_STRING;
            }
            default -> {
                return LOCALE_PL_STRING;
            }
        }
    }
}
