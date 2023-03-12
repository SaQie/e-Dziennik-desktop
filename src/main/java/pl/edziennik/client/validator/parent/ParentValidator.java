package pl.edziennik.client.validator.parent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.validator.BasicValidator;
import pl.edziennik.client.validator.student.StudentValidator;

public class ParentValidator {

    private ParentValidator() {

    }

    public static ParentValidatorBuilder builder() {
        return new ParentValidatorBuilder();
    }

    public static class ParentValidatorBuilder extends BasicValidator {

        public ParentValidatorBuilder withCityValidator(TextField field) {
            createCityValidatorListener(field);
            return this;
        }

        public ParentValidatorBuilder withPostalCodeValidator(TextField field) {
            createPostalCodeValidatorListener(field);
            return this;
        }

        public ParentValidatorBuilder withEmailValidator(TextField field) {
            createEmailValidatorListener(field);
            return this;
        }

        public ParentValidatorBuilder withUsernameValidator(TextField field) {
            createNameValidatorListener(field);
            return this;
        }

        public ParentValidatorBuilder withFirstNameValidator(TextField field) {
            createFirstNameValidatorListener(field);
            return this;
        }

        public ParentValidatorBuilder withLastNameValidator(TextField field) {
            createLastNameValidatorListener(field);
            return this;
        }

        public ParentValidatorBuilder withAddressValidator(TextField field) {
            createAddressValidatorListener(field);
            return this;
        }

        public ParentValidatorBuilder withPeselValidator(TextField field) {
            createPeselValidatorListener(field);
            return this;
        }

        public ParentValidatorBuilder withPhoneNumberValidator(TextField field) {
            createPhoneNumberValidatorListener(field);
            return this;
        }

        public ParentValidatorBuilder withCorrectRoleField(TextField field) {
            withAlwaysCorrectField(field);
            return this;
        }

        public ParentValidatorBuilder withNotEmptyStudentComboBox(ComboBox<?> comboBox) {
            createStudentValidatorListener(comboBox);
            return this;
        }

        public void build(){

        }


    }

}
