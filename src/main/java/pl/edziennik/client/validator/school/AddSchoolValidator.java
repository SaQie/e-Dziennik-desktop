package pl.edziennik.client.validator.school;

import javafx.scene.control.TextField;
import pl.edziennik.client.validator.BasicValidator;

public class AddSchoolValidator{

    private AddSchoolValidator() {
    }

    public static SchoolValidatorBuilder builder(){
        return new SchoolValidatorBuilder();
    }

    public static class SchoolValidatorBuilder extends BasicValidator {

        public SchoolValidatorBuilder withNameValidator(TextField nameField){
            createNameValidatorListener(nameField);
            return this;
        }

        public SchoolValidatorBuilder withAddressValidator(TextField addressField){
            createAddressValidatorListener(addressField);
            return this;
        }

        public SchoolValidatorBuilder withPostalCodeValidator(TextField postalCodeField){
            createPostalCodeValidatorListener(postalCodeField);
            return this;
        }

        public SchoolValidatorBuilder withCityValidator(TextField cityField){
            createCityValidatorListener(cityField);
            return this;
        }

        public SchoolValidatorBuilder withNipValidator(TextField nipField){
            createNipValidatorListener(nipField);
            return this;
        }

        public SchoolValidatorBuilder withRegonValidator(TextField regonField){
            createRegonValidatorListener(regonField);
            return this;
        }

        public SchoolValidatorBuilder withPhoneNumberValidator(TextField phoneNumberField){
            createPhoneNumberValidatorListener(phoneNumberField);
            return this;
        }

        public void build(){

        }

    }
}
