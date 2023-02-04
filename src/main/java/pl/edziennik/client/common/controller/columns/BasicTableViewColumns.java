package pl.edziennik.client.common.controller.columns;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.edziennik.client.controller.model.admin.SchoolListModel;

class BasicTableViewColumns {


    protected static <T> TableColumn<T, Number> getSelectColumn(final boolean isDefaultVisible){
        TableColumn<T, Number> selectColumn = new TableColumn<>("");
        selectColumn.setMaxWidth(45.0);
        selectColumn.setMinWidth(45.0);
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        selectColumn.setVisible(isDefaultVisible);
        return selectColumn;
    }

}
