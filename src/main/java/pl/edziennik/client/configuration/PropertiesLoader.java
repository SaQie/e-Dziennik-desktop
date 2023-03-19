package pl.edziennik.client.configuration;

import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.configuration.converter.PropertiesValueConverter;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pl.edziennik.client.common.ResourceConst.PROPERTIES_LOADER_SERVER_ADDRESS_KEY;

public class PropertiesLoader {

    private static final Logger LOGGER = Logger.getLogger(PropertiesLoader.class.getName());

    private static final Properties properties = new Properties();

    private PropertiesLoader() {

    }

    public static void loadDataFromFile() {
        try (InputStream resource = PropertiesLoader.class.getResourceAsStream(ResourceConst.APPLICATION_PROPERTIES_ADDRESS.value());) {
            properties.load(resource);
            LOGGER.log(Level.INFO, "Properties loaded succesfully");
        } catch (IOException | NullPointerException e) {
            try (OutputStream outputStream = new FileOutputStream(ResourceConst.APPLICATION_PROPERTIES_STREAM_ADDRESS.value());) {
                properties.setProperty("serverAddress", " ");
                properties.setProperty("language", "Polski");
                properties.store(outputStream, null);
                LOGGER.log(Level.INFO, "New properties file created");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    public static void writeConfigurationDataToFile(String language, String serverAddress) {
        try (OutputStream outputStream = new FileOutputStream("src/main/resources/pl/edziennik/client/application.properties");) {
            properties.setProperty("serverAddress", serverAddress);
            properties.setProperty("language", language);
            properties.store(outputStream, null);
            LOGGER.log(Level.INFO, "Properties data succesfully saved");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    public static void writeProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public static String readProperty(String key) {
        return properties.getProperty(key);
    }

    public static <T> T readProperty(String key, PropertiesValueConverter<T> converter) {
        String value = readProperty(key);
        return converter.convertValue(value);
    }

    public static boolean isExist(String key) {
        String value = properties.getProperty(key);
        return value != null &&
                !value.isEmpty() &&
                !value.isBlank();
    }

    public static boolean isLoaded(){
        return readProperty(PROPERTIES_LOADER_SERVER_ADDRESS_KEY.value()) != null;
    }

    public static void clearProperties() {
        properties.setProperty(ResourceConst.PROPERTIES_LOADER_TOKEN_KEY.value(), " ");
        properties.setProperty(ResourceConst.PROPERTIES_LOADER_REFRESH_TOKEN_KEY.value(), " ");
        properties.setProperty(ResourceConst.PROPERTIES_LOADER_NAME_KEY.value(), " ");
        properties.setProperty(ResourceConst.PROPERTIES_LOADER_ID_KEY.value(), " ");
        properties.setProperty(ResourceConst.PROPERTIES_LOADER_ROLE_KEY.value(), " ");
    }

}
