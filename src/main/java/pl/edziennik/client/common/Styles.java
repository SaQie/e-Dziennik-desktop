package pl.edziennik.client.common;

import javafx.scene.control.CheckBox;

public class Styles {

    private Styles() {
    }

    public static String getToolTipValidationStyles(){
        return "-fx-text-fill: red";
    }

    public static CheckBox tableViewSelectionCheckBox(){
        CheckBox checkBox = new CheckBox();
        checkBox.setMaxHeight(20.0);
        checkBox.setMaxWidth(20.0);
        return checkBox;
    }
}
