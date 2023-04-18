package pl.edziennik.client.common;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.skin.ContextMenuSkin;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import pl.edziennik.client.common.builder.CommonStageBuilder;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.eDziennikApplication;
import pl.edziennik.client.utils.ResourceUtil;

public class Styles {

    private static final ImageView CLEAR_ICON = new ImageView(eDziennikApplication.class.getResource(ResourceConst.CLEAR_ICON_ADDRESS.value()).toExternalForm());

    private Styles() {
    }

    public static String getToolTipValidationStyles() {
        return "-fx-text-fill: red";
    }

    public static CheckBox tableViewSelectionCheckBox() {
        CheckBox checkBox = new CheckBox();
        checkBox.setDisable(true);
        checkBox.setMaxHeight(20.0);
        checkBox.setMaxWidth(20.0);
        return checkBox;
    }

    public static ContextMenu clearDictionaryAction() {
        CLEAR_ICON.setFitHeight(20);
        CLEAR_ICON.setFitWidth(20);
        ContextMenu menu = new ContextMenu();
        MenuItem item = new MenuItem();
        item.setGraphic(CLEAR_ICON);
        menu.getItems().add(item);
        setContextMenuStyles(menu);
        setContextMenuItemStyles(item);
        return menu;
    }

    public static void setContextMenuStyles(ContextMenu menu) {
        menu.setStyle("-fx-background-color:#52438f; -fx-text-fill:#52438f;");
        MenuItem item = menu.getItems().get(0);
        menu.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, (e) -> {
            menu.setStyle("-fx-background-color: transparent; -fx-focus-color: transparent");
            item.setStyle("-fx-background-color: transparent; -fx-focus-color: transparent");
        });
    }

    public static void setContextMenuItemStyles(MenuItem menuItem) {
        menuItem.setStyle("-fx-background-color:#52438f; -fx-text-fill:#52438f");
    }

    public static Popup createPopup(final String messageKey) {
        final Popup popup = new Popup();
        ImageView informationIcon = new ImageView(eDziennikApplication.class.getResource(ResourceConst.INFORMATION_ICON_ADDRESS.value()).toExternalForm());
        informationIcon.setFitHeight(20);
        informationIcon.setFitWidth(20);
        popup.setAutoFix(true);
        HBox hBox = new HBox();
        hBox.setSpacing(15);
        Label label = new Label(ResourceUtil.getMessage(messageKey));
        hBox.getStylesheets().add(CommonStageBuilder.POPUP_STYLES_ADDRESS);
        hBox.getStyleClass().add("popup");
        hBox.getChildren().add(informationIcon);
        hBox.getChildren().add(label);
        popup.getContent().add(hBox);
        return popup;
    }
}
