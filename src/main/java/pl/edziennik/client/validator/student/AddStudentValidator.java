package pl.edziennik.client.validator.student;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.validator.BasicValidator;

public class AddStudentValidator {

    private AddStudentValidator() {
    }

    public static StudentValidatorBuilder builder(){
        return new StudentValidatorBuilder();
    }

    public static class StudentValidatorBuilder extends BasicValidator {

        public StudentValidatorBuilder withCityValidator(TextField field){
            createCityValidatorListener(field);
            return this;
        }

        public StudentValidatorBuilder withPostalCodeValidator(TextField field){
            createPostalCodeValidatorListener(field);
            return this;
        }

        public StudentValidatorBuilder withEmailValidator(TextField field){
            createEmailValidatorListener(field);
            return this;
        }

        public StudentValidatorBuilder withUsernameValidator(TextField field){
            createNameValidatorListener(field);
            return this;
        }

        public StudentValidatorBuilder withFirstNameValidator(TextField field){
            createFirstNameValidatorListener(field);
            return this;
        }

        public StudentValidatorBuilder withLastNameValidator(TextField field){
            createLastNameValidatorListener(field);
            return this;
        }

        public StudentValidatorBuilder withAddressValidator(TextField field){
            createAddressValidatorListener(field);
            return this;
        }

        public StudentValidatorBuilder withPeselValidator(TextField field){
            createPeselValidatorListener(field);
            return this;
        }

        public StudentValidatorBuilder withPhoneNumberValidator(TextField field){
            createPhoneNumberValidatorListener(field);
            return this;
        }

        public StudentValidatorBuilder withCorrectPeselField(TextField field){
            withAlwaysCorrectField(field);
            return this;
        }

        public StudentValidatorBuilder withNotEmptySchoolComboBox(ComboBox<?> comboBox){
            createSchoolValidatorListener(comboBox);
            return this;
        }

        public StudentValidatorBuilder withNotEmptySchoolClassComboBox(ComboBox<?> comboBox){
            createSchoolClassValidatorListener(comboBox);
            return this;
        }

        public void build(){

        }

    }

}
