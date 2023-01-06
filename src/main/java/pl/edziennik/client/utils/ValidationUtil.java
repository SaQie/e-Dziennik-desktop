package pl.edziennik.client.utils;

import javafx.beans.binding.Bindings;
import javafx.css.PseudoClass;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import pl.edziennik.client.common.Styles;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class ValidationUtil {

    private static ValidationUtil validationUtil;

    private final ResourceBundle resourceBundles = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS);

    private ValidationUtil() {
    }

    public static ValidationUtil getInstance(){
        if (validationUtil == null){
            validationUtil = new ValidationUtil();
        }
        return validationUtil;
    }

    public static void markTextFieldAsError(TextField textField){
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"), false);
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), true);
    }

    public static void unmarkTextFieldAsError(TextField textField){
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), false);
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"), true);
    }

    public static void clearMark(TextField textField){
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), false);
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"), false);
        textField.setTooltip(null);
    }


    public Tooltip prepareValidationTooltip(String errorMessage){
        Image icon = new Image(getClass().getResource(ERROR_ICON_ADDRESS).toExternalForm(), 20, 20, false, false);
        ImageView errorIcon = new ImageView(icon);
        Tooltip tooltip = new Tooltip();
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setGraphic(errorIcon);
        tooltip.setText(resourceBundles.getString(errorMessage));
        tooltip.setShowDelay(Duration.millis(0.1));
        tooltip.setHideDelay(Duration.millis(0.1));
        tooltip.setStyle(Styles.getToolTipValidationStyles());
        return tooltip;
    }

    public Tooltip prepareValidationTooltip(String errorMessage, Object... objects){
        Image icon = new Image(getClass().getResource(ERROR_ICON_ADDRESS).toExternalForm(), 20, 20, false, false);
        ImageView errorIcon = new ImageView(icon);
        Tooltip tooltip = new Tooltip();
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setGraphic(errorIcon);
        String pattern = resourceBundles.getString(errorMessage);
        tooltip.setText(MessageFormat.format(pattern, objects));
        tooltip.setShowDelay(Duration.millis(0.1));
        tooltip.setHideDelay(Duration.millis(0.1));
        tooltip.setStyle(Styles.getToolTipValidationStyles());
        return tooltip;
    }

    public void enableButtonIfFieldsHasNoErrors(Button button, TextField... textFields){
        button.setDisable(true);

        for (TextField input : textFields) {
            input.tooltipProperty().addListener(field -> {
                boolean hasErrors = Arrays.stream(textFields)
                        .allMatch(e -> (!e.getText().isEmpty() || !e.getText().isBlank()) && e.getTooltip() == null);
                button.setDisable(!hasErrors);
            });
        }
    }

    public void clearFields(TextField... textFields){
        for (TextField textField : textFields) {
            textField.clear();
            clearMark(textField);

        }
    }

}
