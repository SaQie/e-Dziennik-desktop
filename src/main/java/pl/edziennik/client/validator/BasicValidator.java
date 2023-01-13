package pl.edziennik.client.validator;

import javafx.scene.control.TextField;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.utils.ValidationUtil;

public abstract class BasicValidator {

    private final String phoneNumberRegex = "^\\d{9}$";
    private final String postalCodeNumberRegex = "^[0-9]{2}-[0-9]{3}$";


    protected void createNameValidatorListener(TextField nameField){
        nameField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(nameField)){
                ValidationUtil.addErrorTooltipToField(ResourceConst.NAME_FIELD_IS_EMPTY_KEY.value(), nameField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(nameField);
            }
        });
    }

    protected void createAddressValidatorListener(TextField addressField){
        addressField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(addressField)){
                ValidationUtil.addErrorTooltipToField(ResourceConst.ADDRESS_FIELD_IS_EMPTY_KEY.value(), addressField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(addressField);
            }
        });
    }

    protected void createPostalCodeValidatorListener(TextField postalCodeField){
        postalCodeField.textProperty().addListener(input -> {
            if (ValidationUtil.isFieldNotValidToRegex(postalCodeNumberRegex, postalCodeField)){
                ValidationUtil.addErrorTooltipToField(ResourceConst.POSTAL_CODE_NOT_VALID_KEY.value(), postalCodeField);
            }else if (ValidationUtil.isEmpty(postalCodeField)){
                ValidationUtil.addErrorTooltipToField(ResourceConst.POSTAL_CODE_IS_EMPTY_KEY.value(), postalCodeField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(postalCodeField);
            }
        });
    }

    protected void createCityValidatorListener(TextField cityField){
        cityField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(cityField)){
                ValidationUtil.addErrorTooltipToField(ResourceConst.CITY_FIELD_IS_EMPTY_KEY.value(), cityField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(cityField);
            }
        });
    }

    protected void createNipValidatorListener(TextField nipField){
        nipField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(nipField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.NIP_FIELD_IS_EMPTY_KEY.value(), nipField);
            }else if(ValidationUtil.isFieldHasLengthLimit(nipField, 10)){
                ValidationUtil.addErrorTooltipToField(ResourceConst.NIP_CODE_NOT_VALID_KEY.value(), nipField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(nipField);
            }
        });
    }

    protected void createRegonValidatorListener(TextField regonField){
        regonField.textProperty().addListener(input -> {
            if (ValidationUtil.isEmpty(regonField)){
                ValidationUtil.addErrorTooltipToField(ResourceConst.REGON_FIELD_IS_EMPTY_KEY.value(), regonField);
            }else if(ValidationUtil.isFieldHasLengthLimit(regonField, 9)){
                ValidationUtil.addErrorTooltipToField(ResourceConst.REGON_CODE_NOT_VALID_KEY.value(), regonField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(regonField);
            }
        });
    }

    protected void createPhoneNumberValidatorListener(TextField phoneNubmerField){
        phoneNubmerField.textProperty().addListener(input -> {
            if (ValidationUtil.isFieldNotValidToRegex(phoneNumberRegex, phoneNubmerField)) {
                ValidationUtil.addErrorTooltipToField(ResourceConst.PHONE_NUMBER_CODE_NOT_VALID_KEY.value(), phoneNubmerField);
            }else if(ValidationUtil.isEmpty(phoneNubmerField)){
                ValidationUtil.addErrorTooltipToField(ResourceConst.PHONE_NUMBER_FIELD_IS_EMPTY_KEY.value(), phoneNubmerField);
            }else{
                ValidationUtil.unmarkTextFieldAsError(phoneNubmerField);
            }
        });
    }


}
