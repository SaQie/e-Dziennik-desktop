package pl.edziennik.client.validator.auth;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import pl.edziennik.client.utils.ValidationUtil;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class AuthValidator {

    private static final int USERNAME_MIN_LENGTH = 4;
    private static final int USERNAME_MAX_LENGTH = 15;

    private static final int PASSWORD_MIN_LENGTH = 4;
    private static final int PASSWORD_MAX_LENGTH = 30;

    private final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";

    private final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private ValidationUtil validationUtil;

    public AuthValidator() {
        this.validationUtil = ValidationUtil.getInstance();
    }

    public void createUsernameValidatorListener(TextField usernameInput){
        usernameInput.textProperty().addListener(input -> {
            if (usernameInput.getText().isEmpty() || usernameInput.getText().isBlank()){
                usernameInput.setTooltip(fieldIsEmpty(Field.USERNAME));
                ValidationUtil.markTextFieldAsError(usernameInput);
            }else if (usernameInput.getText().length() < USERNAME_MIN_LENGTH || usernameInput.getText().length() > USERNAME_MAX_LENGTH){
                usernameInput.setTooltip(fieldLengthLimit(Field.USERNAME, USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH));
                ValidationUtil.markTextFieldAsError(usernameInput);
            }else{
                usernameInput.setTooltip(null);
                ValidationUtil.unmarkTextFieldAsError(usernameInput);
            }
        });
    }

    public void createEmailValidatorListener(TextField emailInput){
        emailInput.textProperty().addListener(input -> {
            if (emailInput.getText().isEmpty() || emailInput.getText().isBlank()){
                emailInput.setTooltip(fieldIsEmpty(Field.EMAIL));
                ValidationUtil.markTextFieldAsError(emailInput);
            }else if (!emailInput.getText().matches(EMAIL_REGEX)){
                emailInput.setTooltip(emailNotValid());
                ValidationUtil.markTextFieldAsError(emailInput);
            }else{
                emailInput.setTooltip(null);
                ValidationUtil.unmarkTextFieldAsError(emailInput);
            }
        });
    }


    public void createPasswordValidatorListener(TextField passwordInput, PasswordField repeatPasswordInput) {
        passwordInput.textProperty().addListener(input -> {
            if (passwordInput.getText().isEmpty() || passwordInput.getText().isBlank()){
                passwordInput.setTooltip(fieldIsEmpty(Field.PASSWORD));
                ValidationUtil.markTextFieldAsError(passwordInput);
            }else if (passwordInput.getText().length() < PASSWORD_MIN_LENGTH || passwordInput.getText().length() > PASSWORD_MAX_LENGTH){
                passwordInput.setTooltip(fieldLengthLimit(Field.PASSWORD, PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH));
                ValidationUtil.markTextFieldAsError(passwordInput);
            }else if (!passwordInput.getText().matches(PASSWORD_REGEX)){
                passwordInput.setTooltip(passwordNotValid());
                ValidationUtil.markTextFieldAsError(passwordInput);
            }else if (repeatPasswordInput.getText() != null && !passwordInput.getText().equals(repeatPasswordInput.getText())){
                passwordInput.setTooltip(passwordNotMatch());
                repeatPasswordInput.setTooltip(passwordNotMatch());
                ValidationUtil.markTextFieldAsError(passwordInput);
                ValidationUtil.markTextFieldAsError(repeatPasswordInput);
            }else{
                passwordInput.setTooltip(null);
                repeatPasswordInput.setTooltip(null);
                ValidationUtil.unmarkTextFieldAsError(passwordInput);
                ValidationUtil.unmarkTextFieldAsError(repeatPasswordInput);
            }
        });

        repeatPasswordInput.textProperty().addListener(input -> {
            if (repeatPasswordInput.getText() != null && !passwordInput.getText().equals(repeatPasswordInput.getText())){
                passwordInput.setTooltip(passwordNotMatch());
                repeatPasswordInput.setTooltip(passwordNotMatch());
                ValidationUtil.markTextFieldAsError(passwordInput);
                ValidationUtil.markTextFieldAsError(repeatPasswordInput);
            }else if (!passwordInput.getText().matches(PASSWORD_REGEX)) {
                passwordInput.setTooltip(passwordNotValid());
                ValidationUtil.markTextFieldAsError(repeatPasswordInput);
            }else if (repeatPasswordInput.getText().isEmpty() || repeatPasswordInput.getText().isBlank()){
                repeatPasswordInput.setTooltip(fieldIsEmpty(Field.PASSWORD_REPEAT));
                ValidationUtil.markTextFieldAsError(repeatPasswordInput);
            }else{
                passwordInput.setTooltip(null);
                repeatPasswordInput.setTooltip(null);
                ValidationUtil.unmarkTextFieldAsError(passwordInput);
                ValidationUtil.unmarkTextFieldAsError(repeatPasswordInput);
            }
        });
    }

    private Tooltip passwordNotMatch() {
        return validationUtil.prepareValidationTooltip(PASSWORD_AND_REPEAT_PASSWORD_NOT_EQUALS_KEY);
    }

    private Tooltip passwordNotValid(){
        return validationUtil.prepareValidationTooltip(PASSWORD_NOT_VALID_KEY);
    }

    private Tooltip emailNotValid() {
        return validationUtil.prepareValidationTooltip(EMAIL_NOT_VALID_KEY);
    }

    private Tooltip fieldIsEmpty(Field field){
        return switch (field){
            case USERNAME -> validationUtil.prepareValidationTooltip(USERNAME_FIELD_IS_EMPTY_KEY);
            case EMAIL -> validationUtil.prepareValidationTooltip(EMAIL_FIELD_IS_EMPTY_KEY);
            case PASSWORD -> validationUtil.prepareValidationTooltip(PASSWORD_FIELD_IS_EMPTY_KEY);
            case PASSWORD_REPEAT -> validationUtil.prepareValidationTooltip(PASSWORD_REPEAT_FIELD_IS_EMPTY_KEY);
        };
    }

    private Tooltip fieldLengthLimit(Field field, int min, int max){
        return switch(field){
            case USERNAME -> validationUtil.prepareValidationTooltip(USERNAME_FIELD_LENGTH_LIMIT_KEY, min,max);
            case PASSWORD -> validationUtil.prepareValidationTooltip(PASSWORD_FIELD_LENGTH_LIMIT_KEY, min,max);
            default -> throw new IllegalStateException("Unexpected value: " + field);
        };
    }

    private enum Field{
        USERNAME,
        EMAIL,
        PASSWORD,
        PASSWORD_REPEAT
    }

}
