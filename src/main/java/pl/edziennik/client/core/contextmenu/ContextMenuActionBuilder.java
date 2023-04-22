package pl.edziennik.client.core.contextmenu;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import pl.edziennik.client.core.TableViewSelection;

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
        public <T extends TableViewSelection> void build(TableView<T> tableView) {
            if (actions.isEmpty()) {
                throw new RuntimeException("Use addAction method first to use this method");
            }
            ContextMenu contextMenu = new ContextMenu();
            for (ContextMenuAction action : actions) {

                MenuItem item = new MenuItem(action.getActionName());
                item.setOnAction(event -> {
                    Long rowId = tableView.getSelectionModel().getSelectedItem().getId();
                    action.execute(rowId);
                });

                contextMenu.getItems().add(item);
            }

            tableView.setContextMenu(contextMenu);
        }

    }


}
