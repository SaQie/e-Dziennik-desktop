package pl.edziennik.client.configuration.converter;

import pl.edziennik.client.common.ResourceConst;

public class PropertiesBackendLangugageConverter implements PropertiesValueConverter<String>{


    @Override
    public String convertValue(String value) {
        switch (value){
            case "Polski" -> {
                return ResourceConst.LOCALE_PL_STRING.value();
            }
            case "English" -> {
                return ResourceConst.LOCALE_EN_STRING.value();
            }
            default -> {
                return ResourceConst.LOCALE_PL_STRING.value();
            }
        }
    }
}
