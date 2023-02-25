package pl.edziennik.client.validator;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.utils.ValidationUtil;

import java.util.regex.Pattern;

public abstract class BasicValidator {

    private final String phoneNumberRegex = "^\\d{9}$";
    private final String postalCodeNumberRegex = "^[0-9]{2}-[0-9]{3}$";
    private final String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    private final Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);
    private final Pattern postalCodePattern = Pattern.compile(postalCodeNumberRegex);
    private final Pattern emailPattern = Pattern.compile(emailRegex);

    protected static final int PESEL_LENGTH = 11;


    protected void createNameValidatorListener(TextField nameField) {
        nameField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(nameField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.NAME_FIELD_IS_EMPTY_KEY.value(), nameField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(nameField);
            }
        });
    }

    protected void createAddressValidatorListener(TextField addressField) {
        addressField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(addressField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.ADDRESS_FIELD_IS_EMPTY_KEY.value(), addressField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(addressField);
            }
        });
    }

    protected void createPostalCodeValidatorListener(TextField postalCodeField) {
        postalCodeField.textProperty().addListener(input -> {
            if (ValidationUtil.isFieldNotValidToRegex(postalCodePattern, postalCodeField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.POSTAL_CODE_NOT_VALID_KEY.value(), postalCodeField);
            } else if (ValidationUtil.isEmpty(postalCodeField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.POSTAL_CODE_IS_EMPTY_KEY.value(), postalCodeField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(postalCodeField);
            }
        });
    }

    protected void createCityValidatorListener(TextField cityField) {
        cityField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(cityField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.CITY_FIELD_IS_EMPTY_KEY.value(), cityField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(cityField);
            }
        });
    }

    protected void createNipValidatorListener(TextField nipField) {
        nipField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(nipField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.NIP_FIELD_IS_EMPTY_KEY.value(), nipField);
            } else if (ValidationUtil.isFieldHasLengthLimit(nipField, 10)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.NIP_CODE_NOT_VALID_KEY.value(), nipField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(nipField);
            }
        });
    }

    protected void createRegonValidatorListener(TextField regonField) {
        regonField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(regonField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.REGON_FIELD_IS_EMPTY_KEY.value(), regonField);
            } else if (ValidationUtil.isFieldHasLengthLimit(regonField, 9)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.REGON_CODE_NOT_VALID_KEY.value(), regonField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(regonField);
            }
        });
    }

    protected void createPhoneNumberValidatorListener(TextField phoneNubmerField) {
        phoneNubmerField.textProperty().addListener(input -> {
            if (ValidationUtil.isFieldNotValidToRegex(phoneNumberPattern, phoneNubmerField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.PHONE_NUMBER_CODE_NOT_VALID_KEY.value(), phoneNubmerField);
            } else if (ValidationUtil.isEmpty(phoneNubmerField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.PHONE_NUMBER_FIELD_IS_EMPTY_KEY.value(), phoneNubmerField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(phoneNubmerField);
            }
        });
    }

    protected void createEmailValidatorListener(TextField emailField) {
        emailField.textProperty().addListener(input -> {
            if (ValidationUtil.isFieldNotValidToRegex(emailPattern, emailField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.EMAIL_NOT_VALID_KEY.value(), emailField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(emailField);
            }
        });
    }

    protected void createPasswordValidatorListener(TextField passwordField) {
        passwordField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(passwordField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.PASSWORD_FIELD_IS_EMPTY_KEY.value(), passwordField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(passwordField);
            }
        });
    }

    protected void createFirstNameValidatorListener(TextField firstNameField) {
        firstNameField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(firstNameField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.FIRST_NAME_FIELD_IS_EMPTY_KEY.value(), firstNameField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(firstNameField);
            }
        });
    }

    protected void createLastNameValidatorListener(TextField lastNameField) {
        lastNameField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(lastNameField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.LAST_NAME_FIELD_IS_EMPTY_KEY.value(), lastNameField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(lastNameField);
            }
        });
    }

    protected void createPeselValidatorListener(TextField peselField) {
        peselField.textProperty().addListener(input -> {
            if (ValidationUtil.isFieldHasNotLengthEqualTo(peselField, PESEL_LENGTH)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.PESEL_FIELD_IS_NOT_CORRECT.value(), peselField);
            } else {
                ValidationUtil.unmarkTextFieldAsError(peselField);
            }
        });
    }

    protected void createSchoolValidatorListener(ComboBox<?> comboBox) {
        comboBox.valueProperty().addListener(input -> {
            if (ValidationUtil.isComboBoxEmpty(comboBox)) {
                ValidationUtil.addErrorTooltipToComboBox(ResourceConst.SCHOOL_CANNOT_BE_EMPTY.value(), comboBox);
            } else {
                ValidationUtil.unmarkComboBoxAsError(comboBox);
            }
        });
    }

    protected void createSchoolClassValidatorListener(ComboBox<?> comboBox) {
        comboBox.valueProperty().addListener(input -> {
            if (ValidationUtil.isComboBoxEmpty(comboBox)) {
                ValidationUtil.addErrorTooltipToComboBox(ResourceConst.SCHOOL_CLASS_CANNOT_BE_EMPTY.value(), comboBox);
            } else {
                ValidationUtil.unmarkComboBoxAsError(comboBox);
            }
        });
    }

    protected void withAlwaysCorrectField(TextField field) {
        ValidationUtil.unmarkTextFieldAsError(field);
    }


}
