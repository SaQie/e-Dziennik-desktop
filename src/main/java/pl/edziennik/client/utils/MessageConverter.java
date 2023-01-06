package pl.edziennik.client.utils;

import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesLanguageConverter;

import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class MessageConverter {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS, PropertiesLoader.readProperty("language", new PropertiesLanguageConverter()));

    private MessageConverter() {
    }

    public static String converSchoolLevelMessages(String name){
        switch (name){
            case "PRIMARY SCHOOL" -> {
                return resourceBundle.getString(PRIMARY_SCHOOL_NAME_KEY);
            }
            case "HIGH SCHOOL" -> {
                return resourceBundle.getString(HIGH_SCHOOL_NAME_KEY);
            }
            case "UNIVERSITY" -> {
                return resourceBundle.getString(UNIVERSITY_SCHOOL_NAME_KEY);
            }
            default -> {
                return null;
            }
        }
    }
}
