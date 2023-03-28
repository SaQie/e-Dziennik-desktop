package pl.edziennik.client.validator.schoolclass;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.validator.BasicValidator;

public class SchoolClassValidator {

    private SchoolClassValidator() {
    }

    public static SchoolClassValidatorBuilder builder() {
        return new SchoolClassValidatorBuilder();
    }

    public static class SchoolClassValidatorBuilder extends BasicValidator {

        public SchoolClassValidatorBuilder withNameValidator(TextField nameField){
            createNotEmptyFieldValidatorListener(nameField);
            return this;
        }

        public SchoolClassValidatorBuilder withNotEmptySchoolComboBox(ComboBox<?> schoolComboBox){
            createSchoolValidatorListener(schoolComboBox);
            return this;
        }

        public SchoolClassValidatorBuilder withAlwaysCorrectSupervisingTeacherComboBox(ComboBox<?> supervisingTeacherComboBox){
            withAlwaysCorrectComboBox(supervisingTeacherComboBox);
            return this;
        }

        public void build(){

        }

    }

}
