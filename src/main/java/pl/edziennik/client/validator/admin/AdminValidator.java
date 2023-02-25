package pl.edziennik.client.validator.admin;

import javafx.scene.control.TextField;
import pl.edziennik.client.validator.BasicValidator;

public class AdminValidator {

    private AdminValidator() {

    }

    public static AdminValidatorBuilder builder() {
        return new AdminValidatorBuilder();
    }

    public static class AdminValidatorBuilder extends BasicValidator {

        public AdminValidatorBuilder withUsernameValidator(TextField textField) {
            createNameValidatorListener(textField);
            return this;
        }

        public AdminValidatorBuilder withEmailValidator(TextField textField) {
            createEmailValidatorListener(textField);
            return this;
        }

        public AdminValidatorBuilder withPasswordValidator(TextField textField) {
            withAlwaysCorrectField(textField);
            return this;
        }

        public AdminValidatorBuilder withRoleValidator(TextField field){
            withAlwaysCorrectField(field);
            return this;
        }

        public void build() {

        }


    }

}
