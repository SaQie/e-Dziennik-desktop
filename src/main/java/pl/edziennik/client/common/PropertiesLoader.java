package pl.edziennik.client.common;

import java.util.Properties;

public class PropertiesLoader {

    private static final Properties properties = new Properties();


    public static void writeProperty(String key, String value){
        properties.setProperty(key, value);
    }

    public static String readProperty(String key){
        return properties.getProperty(key);
    }

    public static boolean isExist(String key){
        return properties.getProperty(key) != null;
    }

    public static void clearProperties(){
        properties.clear();
    }

}
