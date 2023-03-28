package pl.edziennik.client.common;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.eDziennikApplication;

public class Styles {

    private static final ImageView CLEAR_ICON = new ImageView(eDziennikApplication.class.getResource(ResourceConst.CLEAR_ICON_ADDRESS.value()).toExternalForm());
    public static final String CONTEXT_MENU_STYLES = eDziennikApplication.class.getResource(ResourceConst.CONTEXT_MENU_STYLES.value()).toExternalForm();

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

    public static ContextMenu clearDictionaryAction(){
        CLEAR_ICON.setFitHeight(15);
        CLEAR_ICON.setFitWidth(15);
        ContextMenu menu = new ContextMenu();
        MenuItem item = new MenuItem();
        item.setGraphic(CLEAR_ICON);
        menu.getItems().add(item);
        menu.getStyleClass().add(CONTEXT_MENU_STYLES);
        menu.getScene().getStylesheets().add(CONTEXT_MENU_STYLES);
        menu.getStyleableParent().getStyleClass().add(CONTEXT_MENU_STYLES);
        item.getStyleClass().add(CONTEXT_MENU_STYLES);
        item.getParentPopup().getStyleClass().add(CONTEXT_MENU_STYLES);
        return menu;
    }
}
