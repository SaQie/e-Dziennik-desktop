package pl.edziennik.client.utils;

import pl.edziennik.client.rest.dto.parent.SimpleParentDto;

public class ModelUtils {

    private ModelUtils() {

    }

    public static String getValueOrEmpty(Object obj) {
        if (obj instanceof SimpleParentDto) {
            return ((SimpleParentDto) obj).getFullName();
        }
        return " ";
    }

}
