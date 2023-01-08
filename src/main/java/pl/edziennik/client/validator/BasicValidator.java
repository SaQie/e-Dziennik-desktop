package pl.edziennik.client.validator;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.utils.ValidationUtil;

import static pl.edziennik.client.common.ResourcesConstants.*;

public abstract class BasicValidator {

    private final String phoneNumberRegex = "^\\d{9}$";
    private final String postalCodeNumberRegex = "^[0-9]{2}-[0-9]{3}$";


    protected void createNameValidatorListener(TextField nameField){
        nameField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(nameField)){
                ValidationUtil.addErrorTooltipToField(NAME_FIELD_IS_EMPTY_KEY, nameField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(nameField);
            }
        });
    }

    protected void createAddressValidatorListener(TextField addressField){
        addressField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(addressField)){
                ValidationUtil.addErrorTooltipToField(ADDRESS_FIELD_IS_EMPTY_KEY, addressField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(addressField);
            }
        });
    }

    protected void createPostalCodeValidatorListener(TextField postalCodeField){
        postalCodeField.textProperty().addListener(input -> {
            if (ValidationUtil.isFieldNotValidToRegex(postalCodeNumberRegex, postalCodeField)){
                ValidationUtil.addErrorTooltipToField(POSTAL_CODE_NOT_VALID_KEY, postalCodeField);
            }else if (ValidationUtil.isEmpty(postalCodeField)){
                ValidationUtil.addErrorTooltipToField(POSTAL_CODE_IS_EMPTY_KEY, postalCodeField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(postalCodeField);
            }
        });
    }

    protected void createCityValidatorListener(TextField cityField){
        cityField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(cityField)){
                ValidationUtil.addErrorTooltipToField(CITY_FIELD_IS_EMPTY_KEY, cityField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(cityField);
            }
        });
    }

    protected void createNipValidatorListener(TextField nipField){
        nipField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(nipField)) {
                ValidationUtil.addErrorTooltipToField(NIP_FIELD_IS_EMPTY_KEY, nipField);
            }else if(ValidationUtil.isFieldHasLengthLimit(nipField, 10)){
                ValidationUtil.addErrorTooltipToField(NIP_CODE_NOT_VALID_KEY, nipField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(nipField);
            }
        });
    }

    protected void createRegonValidatorListener(TextField regonField){
        regonField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(regonField)){
                ValidationUtil.addErrorTooltipToField(REGON_FIELD_IS_EMPTY_KEY, regonField);
            }else if(ValidationUtil.isFieldHasLengthLimit(regonField, 9)){
                ValidationUtil.addErrorTooltipToField(REGON_CODE_NOT_VALID_KEY, regonField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(regonField);
            }
        });
    }

    protected void createPhoneNumberValidatorListener(TextField phoneNubmerField){
        phoneNubmerField.textProperty().addListener(input -> {
            if (ValidationUtil.isFieldNotValidToRegex(phoneNumberRegex, phoneNubmerField)) {
                ValidationUtil.addErrorTooltipToField(PHONE_NUMBER_CODE_NOT_VALID_KEY, phoneNubmerField);
            }else if(ValidationUtil.isEmpty(phoneNubmerField)){
                ValidationUtil.addErrorTooltipToField(PHONE_NUMBER_FIELD_IS_EMPTY_KEY, phoneNubmerField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(phoneNubmerField);
            }
        });
    }


}
