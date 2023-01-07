package pl.edziennik.client.utils;

import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesLanguageConverter;

import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.MESSAGES_RESOURCES_ADDRESS;

public class ResourceUtil {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS, PropertiesLoader.readProperty("language", new PropertiesLanguageConverter()));

    public static String getMessage(String resourceKey){
        return resourceBundle.getString(resourceKey);
    }

}
