package pl.edziennik.client.controller.model.admin;

import javafx.scene.control.CheckBox;
import pl.edziennik.client.exception.ViewException;

public interface TableViewSelection {

    void setSelection();

    Long getId();

    default Long getUserId() {
        throw new ViewException("Method not supported without overriding");
    };

    boolean isSelected();

    CheckBox getSelect();

}