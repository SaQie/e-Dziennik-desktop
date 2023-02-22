package pl.edziennik.client.validator.teacher;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.validator.BasicValidator;
import pl.edziennik.client.validator.student.StudentValidator;

public class TeacherValidator {

    private TeacherValidator() {
    }

    public static TeacherValidatorBuilder builder(){
        return new TeacherValidatorBuilder();
    }

    public static class TeacherValidatorBuilder extends BasicValidator{

        public TeacherValidatorBuilder withFirstNameValidator(TextField field){
            createFirstNameValidatorListener(field);
            return this;
        }

        public TeacherValidatorBuilder withLastNameValidator(TextField field){
            createLastNameValidatorListener(field);
            return this;
        }

        public TeacherValidatorBuilder withPhoneNumberValidator(TextField field){
            createPhoneNumberValidatorListener(field);
            return this;
        }

        public TeacherValidatorBuilder withCityValidator(TextField field){
            createCityValidatorListener(field);
            return this;
        }

        public TeacherValidatorBuilder withPostalCodeValidator(TextField field){
            createPostalCodeValidatorListener(field);
            return this;
        }

        public TeacherValidatorBuilder withEmailValidator(TextField field){
            createEmailValidatorListener(field);
            return this;
        }

        public TeacherValidatorBuilder withUsernameValidator(TextField field){
            createNameValidatorListener(field);
            return this;
        }

        public TeacherValidatorBuilder withAddressValidator(TextField field){
            createAddressValidatorListener(field);
            return this;
        }

        public TeacherValidatorBuilder withPeselValidator(TextField field){
            createPeselValidatorListener(field);
            return this;
        }

        public TeacherValidatorBuilder withNotEmptySchoolComboBox(ComboBox<?> comboBox){
            createSchoolValidatorListener(comboBox);
            return this;
        }

        public TeacherValidatorBuilder withCorrectRoleField(TextField field){
            withAlwaysCorrectField(field);
            return this;
        }

        public void build(){

        }


    }
}
