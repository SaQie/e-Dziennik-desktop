package pl.edziennik.client.common;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.scene.control.Dialog;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.SneakyThrows;
import pl.edziennik.client.common.builder.CommonStageBuilder;
import pl.edziennik.client.core.DictionaryItemModel;
import pl.edziennik.client.rest.dto.DictionaryItemDto;
import pl.edziennik.client.rest.dto.Page;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DictionaryFactory {

    private static DictionaryFactory factory;
    private final ProgressFactory progressFactory;

    private DictionaryFactory() {
        this.progressFactory = ProgressFactory.getInstance();
    }

    private Map<Integer, List<DictionaryItemModel>> paginationCacheMap = new HashMap<>();

    public static DictionaryFactory getInstance() {
        if (factory == null) {
            factory = new DictionaryFactory();
        }
        return factory;
    }


    @SneakyThrows
    public <T extends DictionaryItemDto, E extends Task<Page<List<T>>>> Optional<Long> createAndGetDictionaryValue(Class<E> taskClass) {
        Long value = null;
        E task = taskClass.getConstructor().newInstance();
        progressFactory.createLittleProgressBar(task, (response) -> {

            Page<List<DictionaryItemModel>> pageableItems = mapToModel(response);

            Dialog<Long> dialog = CommonStageBuilder.dictionaryBuilder()
                    .build();

            BorderPane content = (BorderPane) dialog.getDialogPane().getContent();
            TableView<DictionaryItemModel> tableView = (TableView<DictionaryItemModel>) content.getCenter();
            tableView.setItems(FXCollections.observableList(pageableItems.getEntities()));
            paginationCacheMap.put(pageableItems.getActualPage() - 1, pageableItems.getEntities());
            HBox hBox = (HBox) content.getBottom();
            Pagination pagination = (Pagination) hBox.getChildren().get(0);
            pagination.setPageCount(pageableItems.getPagesCount());

            pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
                boolean isCacheContainsData = paginationCacheMap.containsKey(newIndex.intValue());

                if (isCacheContainsData) {
                    List<DictionaryItemModel> dictionaryItemModels = paginationCacheMap.get(newIndex.intValue());
                    tableView.setItems(FXCollections.observableList(dictionaryItemModels));
                    return;
                }
                E taskInstance;
                try {

                    taskInstance = taskClass.getDeclaredConstructor(int.class).newInstance(newIndex.intValue());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                progressFactory.createLittleProgressBar(taskInstance, (responseAfterPageChange) -> {
                    Page<List<DictionaryItemModel>> pageableItemsAfterPageChange = mapToModel(responseAfterPageChange);
                    paginationCacheMap.put(pageableItemsAfterPageChange.getActualPage() - 1, pageableItemsAfterPageChange.getEntities());
                    tableView.setItems(FXCollections.observableList(pageableItemsAfterPageChange.getEntities()));
                });

            });

            dialog.showAndWait();
        });

        paginationCacheMap = new HashMap<>();
        return Optional.ofNullable(value);
    }


    private <T extends DictionaryItemDto> Page<List<DictionaryItemModel>> mapToModel(Page<List<T>> pageDto) {
        Page<List<DictionaryItemModel>> result = new Page<>();

        result.setActualPage(pageDto.getActualPage());
        result.setItemsTotalCount(pageDto.getItemsTotalCount());
        result.setPagesCount(pageDto.getPagesCount());
        result.setItemsOnPage(pageDto.getItemsOnPage());
        result.setEntities(pageDto.getEntities()
                .stream()
                .map(dto -> new DictionaryItemModel(dto.getId(), dto.getName()))
                .toList());

        return result;

    }

}