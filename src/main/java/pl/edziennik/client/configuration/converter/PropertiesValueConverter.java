package pl.edziennik.client.configuration.converter;

public interface PropertiesValueConverter<T> {

    T convertValue(String key);

}
