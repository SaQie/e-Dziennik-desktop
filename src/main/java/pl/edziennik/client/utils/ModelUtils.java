package pl.edziennik.client.utils;

import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.parent.ParentDto;
import pl.edziennik.client.rest.dto.parent.SimpleParentDto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ModelUtils {

    private ModelUtils() {

    }

    public static String getValueOrEmpty(Object obj) {
        if (obj instanceof SimpleParentDto) {
            return ((SimpleParentDto) obj).getFullName();
        }
        if (obj instanceof ParentDto) {
            return ((ParentDto) obj).getPhoneNumber();
        }
        return " ";
    }


    public static <T> Page<List<T>> convertToListPage(Page<T[]> page) {
        Page<List<T>> result = new Page<>();
        List<T> entitiesList = new ArrayList<>();

        if (page.getEntities() != null) {
            if (page.getEntities().getClass().isArray()) {
                for (int i = 0; i < Array.getLength(page.getEntities()); i++) {
                    entitiesList.add((T) Array.get(page.getEntities(), i));
                }
            } else {
                entitiesList.add(page.getEntities()[0]);
            }
        }

        result.setEntities(entitiesList);
        result.setItemsOnPage(page.getItemsOnPage());
        result.setItemsTotalCount(page.getItemsTotalCount());
        result.setActualPage(page.getActualPage());
        result.setPagesCount(page.getPagesCount());

        return result;
    }

}
