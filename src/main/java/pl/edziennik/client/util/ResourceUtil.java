package pl.edziennik.client.util;

import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesLanguageConverter;

import java.util.ResourceBundle;

public class ResourceUtil {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceConst.MESSAGES_RESOURCES_ADDRESS.value(), PropertiesLoader.readProperty("language", new PropertiesLanguageConverter()));

    public static String getMessage(String resourceKey){
        return resourceBundle.getString(resourceKey);
    }

}
