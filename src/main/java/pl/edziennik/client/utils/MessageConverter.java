package pl.edziennik.client.utils;

import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesLanguageConverter;

import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class MessageConverter {

    private MessageConverter() {
    }

    public static String converSchoolLevelMessages(String name){
        switch (name){
            case "PRIMARY SCHOOL" -> {
                return ResourceUtil.getMessage(PRIMARY_SCHOOL_NAME_KEY);
            }
            case "HIGH SCHOOL" -> {
                return ResourceUtil.getMessage(HIGH_SCHOOL_NAME_KEY);
            }
            case "UNIVERSITY" -> {
                return ResourceUtil.getMessage(UNIVERSITY_SCHOOL_NAME_KEY);
            }
            default -> {
                return null;
            }
        }
    }
}
