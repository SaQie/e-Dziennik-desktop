package pl.edziennik.client.configuration.converter;

import pl.edziennik.client.exception.ViewException;

public class PropertiesStringToLongConverter implements PropertiesValueConverter<Long> {

    @Override
    public Long convertValue(String key) {
        try {
            return Long.valueOf(key);
        } catch (NumberFormatException e) {
            throw new ViewException("Exception while parsing String to Long in " + this.getClass().getSimpleName());
        }
    }
}
