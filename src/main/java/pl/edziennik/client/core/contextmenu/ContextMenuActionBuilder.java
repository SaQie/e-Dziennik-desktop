package pl.edziennik.client.core.contextmenu;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.core.TableViewSelection;
import pl.edziennik.client.exception.TableRowException;
import pl.edziennik.client.util.NodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for menu actions
 */
public class ContextMenuActionBuilder {

    public static Builder builder() {
        return new Builder();
    }

    private ContextMenuActionBuilder() {

    }

    public static class Builder {

        private final List<ContextMenuAction> actions = new ArrayList<>();

        /**
         * Add specific action to menu
         *
         * @param action
         * @return
         */
        public Builder addAction(ContextMenuAction action) {
            this.actions.add(action);
            return this;
        }

        /**
         * Add menu with provided actions to table view (PPM context menu)
         *
         * @param tableView
         * @param <T>
         */
        public <T extends TableViewSelection> void build(TableView<T> tableView, MenuButton menuButton) {
            if (actions.isEmpty()) {
                throw new RuntimeException("Use addAction method first to use this method");
            }
            ContextMenu contextMenu = new ContextMenu();

            for (ContextMenuAction action : actions) {

                MenuItem item = createMenuItemWithAction(action, tableView);
                contextMenu.getItems().add(item);

            }

            tableView.setContextMenu(contextMenu);

            for (ContextMenuAction action : actions) {

                if (action.isAssignToMenuButton()) {
                    MenuItem item = createMenuItemWithAction(action, tableView);
                    menuButton.getItems().add(item);
                }

            }
        }

    }

    private static <T extends TableViewSelection> MenuItem createMenuItemWithAction(ContextMenuAction action, TableView<T> tableView) {
        MenuItem item = new MenuItem(action.getActionName());
        ImageView imageView = new ImageView(action.getIcon());
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        item.setGraphic(imageView);
        item.setOnAction(event -> {
            if (ContextMenuActionExecutorMode.CURRENT_ROW.equals(action.getExecutorMode())) {
                Long rowId = NodeUtils.getSelectedTableItemIdentifier(tableView);
                if (rowId == null) {
                    throw new TableRowException(ResourceConst.TABLE_VIEW_ROW_NOT_SELECTED_MESSAGE_KEY.value());
                }
                action.executeOnCurrentRow(rowId);
            } else {
                action.execute();
            }
        });
        return item;
    }


}
