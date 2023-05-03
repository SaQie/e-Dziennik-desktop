package pl.edziennik.client.util;

import pl.edziennik.client.rest.dto.Page;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ModelUtils {

    private ModelUtils() {

    }

    public static <T> Page<List<T>> convertToListPage(Page<T[]> page) {
        Page<List<T>> result = new Page<>();
        List<T> entitiesList = new ArrayList<>();

        if (page.getContent() != null) {
            if (page.getContent().getClass().isArray()) {
                for (int i = 0; i < Array.getLength(page.getContent()); i++) {
                    entitiesList.add((T) Array.get(page.getContent(), i));
                }
            } else {
                entitiesList.add(page.getContent()[0]);
            }
        }

        result.setContent(entitiesList);
        result.setItemsOnPage(page.getItemsOnPage());
        result.setItemsTotalCount(page.getItemsTotalCount());
        result.setActualPage(page.getActualPage());
        result.setPagesCount(page.getPagesCount());

        return result;
    }

}
