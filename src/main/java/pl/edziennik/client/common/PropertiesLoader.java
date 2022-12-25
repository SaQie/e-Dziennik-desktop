package pl.edziennik.client.common;

import java.util.Properties;

public class PropertiesLoader {

    public static Properties properties;

    public static void writeProperty(String key, String value){
        properties.setProperty(key, value);
    }

    public static String readProperty(String key){
        String property = properties.getProperty(key);
        if (property == null){
            throw new IllegalArgumentException("test");
        }
        return property;
    }

    public static void clearProperties(){
        if (properties.getProperty("token") != null){
            properties.clear();
        }
    }

}
