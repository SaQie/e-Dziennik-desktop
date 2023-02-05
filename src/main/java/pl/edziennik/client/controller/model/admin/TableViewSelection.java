package pl.edziennik.client.controller.model.admin;

import javafx.scene.control.CheckBox;

public interface TableViewSelection {

    void setSelection();

    Long getId();

    boolean isSelected();

    CheckBox getSelect();

}