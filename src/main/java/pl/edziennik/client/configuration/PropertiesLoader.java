package pl.edziennik.client.configuration;

import pl.edziennik.client.configuration.converter.PropertiesValueConverter;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesLoader {

    private static final Logger LOGGER = Logger.getLogger(PropertiesLoader.class.getName());

    private static final Properties properties = new Properties();

    private PropertiesLoader() {

    }

    public static void loadDataFromFile() {
        try (InputStream resource = PropertiesLoader.class.getResourceAsStream("/pl/edziennik/client/application.properties");) {
            properties.load(resource);
            LOGGER.log(Level.INFO, "Properties loaded succesfully");
        } catch (IOException | NullPointerException e) {
            try (OutputStream outputStream = new FileOutputStream("src/main/resources/pl/edziennik/client/application.properties");) {
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
        return properties.getProperty(key) != null;
    }

    public static void clearProperties() {
        properties.setProperty("token", " ");
        properties.setProperty("refreshToken", " ");
        properties.setProperty("name", " ");
        properties.setProperty("id", " ");
        properties.setProperty("role", " ");
    }

}
