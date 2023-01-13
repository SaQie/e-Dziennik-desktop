package pl.edziennik.client.utils;

import pl.edziennik.client.common.ResourceConst;

public class MessageConverter {

    private MessageConverter() {
    }

    public static String converSchoolLevelMessages(String name){
        switch (name){
            case "PRIMARY SCHOOL" -> {
                return ResourceUtil.getMessage(ResourceConst.PRIMARY_SCHOOL_NAME_KEY.value());
            }
            case "HIGH SCHOOL" -> {
                return ResourceUtil.getMessage(ResourceConst.HIGH_SCHOOL_NAME_KEY.value());
            }
            case "UNIVERSITY" -> {
                return ResourceUtil.getMessage(ResourceConst.UNIVERSITY_SCHOOL_NAME_KEY.value());
            }
            default -> {
                return null;
            }
        }
    }
}
