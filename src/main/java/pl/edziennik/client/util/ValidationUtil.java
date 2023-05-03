package pl.edziennik.client.util;

import javafx.css.PseudoClass;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.Styles;

import java.text.MessageFormat;
import java.util.regex.Pattern;

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

    public static void markComboBoxAsError(ComboBox<?> comboBox){
        comboBox.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"), false);
        comboBox.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), true);
    }

    public static void unmarkTextFieldAsError(TextField textField){
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), false);
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"), true);
        textField.setTooltip(new Tooltip());
        textField.setTooltip(null);
    }

    public static void unmarkComboBoxAsError(ComboBox<?> comboBox){
        comboBox.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), false);
        comboBox.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"), true);
        comboBox.setTooltip(new Tooltip());
        comboBox.setTooltip(null);
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

    public static void addErrorTooltipToComboBox(String errorMessage, ComboBox<?> comboBox){
        Tooltip errorToolTip = prepareValidationTooltip(errorMessage);
        comboBox.setTooltip(errorToolTip);
        ValidationUtil.markComboBoxAsError(comboBox);
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

    public static boolean isFieldHasLengthMinLimit(TextField field, int min){
        return field.getText().length() > min;
    }

    public static boolean isFieldHasNotLengthEqualTo(TextField field, int prefLength){
        return field.getText().length() != prefLength;
    }

    public static boolean isFieldNotValidToRegex(Pattern pattern, TextField field){
        return !pattern.matcher(field.getText()).matches();
    }

    public static boolean isComboBoxEmpty(ComboBox<?> comboBox){
        return comboBox.getValue() == null;
    }

}
