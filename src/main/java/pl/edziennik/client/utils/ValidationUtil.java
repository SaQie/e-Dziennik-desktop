package pl.edziennik.client.utils;

import javafx.css.PseudoClass;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.common.Styles;

import java.text.MessageFormat;

public class ValidationUtil {

    private static ValidationUtil validationUtil;

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
        textField.setTooltip(new Tooltip());
        textField.setTooltip(null);
    }

    public static void clearMark(TextField textField){
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), false);
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"), false);
        textField.setTooltip(new Tooltip());
        textField.setTooltip(null);
    }


    public static Tooltip prepareValidationTooltip(String errorMessage){
        Image icon = new Image(ValidationUtil.class.getResource(ResourceConst.ERROR_ICON_ADDRESS.value()).toExternalForm(), 20, 20, false, false);
        ImageView errorIcon = new ImageView(icon);
        Tooltip tooltip = new Tooltip();
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setGraphic(errorIcon);
        tooltip.setText(ResourceUtil.getMessage(errorMessage));
        tooltip.setShowDelay(Duration.millis(0.1));
        tooltip.setHideDelay(Duration.millis(0.1));
        tooltip.setStyle(Styles.getToolTipValidationStyles());
        return tooltip;
    }

    public static Tooltip prepareValidationTooltip(String errorMessage, Object... objects){
        Image icon = new Image(ValidationUtil.class.getResource(ResourceConst.ERROR_ICON_ADDRESS.value()).toExternalForm(), 20, 20, false, false);
        ImageView errorIcon = new ImageView(icon);
        Tooltip tooltip = new Tooltip();
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setGraphic(errorIcon);
        String pattern = ResourceUtil.getMessage(errorMessage);
        tooltip.setText(MessageFormat.format(pattern, objects));
        tooltip.setShowDelay(Duration.millis(0.1));
        tooltip.setHideDelay(Duration.millis(0.1));
        tooltip.setStyle(Styles.getToolTipValidationStyles());
        return tooltip;
    }

    public static void addErrorTooltipToField(String errorMessage, TextField field){
        Tooltip errorToolTip = prepareValidationTooltip(errorMessage);
        field.setTooltip(errorToolTip);
        ValidationUtil.markTextFieldAsError(field);
    }



    public static void clearFields(TextField... textFields){
        for (TextField textField : textFields) {
            textField.clear();
            clearMark(textField);

        }
    }

    public static boolean isEmpty(TextField field){
        return field.getText() == null || field.getText().isEmpty() || field.getText().isBlank();
    }

    public static boolean isFieldHasLengthLimit(TextField field, int max){
        return field.getText().length() != max;
    }

    public static boolean isFieldNotValidToRegex(String regex, TextField field){
        return !field.getText().matches(regex);
    }

}
