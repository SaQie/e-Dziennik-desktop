package pl.edziennik.client.controller.admin.account.parent;

import pl.edziennik.client.common.factory.ActionType;
import pl.edziennik.client.rest.dto.parent.ParentDto;
import pl.edziennik.client.validator.parent.ParentValidator;

public class AdminAccountsTabParentsEditController extends AdminAccountsTabParentActionAbstractController {

    private Long objectId;

    @Override
    protected void loadStageFields(ParentDto dto, ActionType type) {
        super.loadStageFields(dto, type);
        this.objectId = dto.getParentId();
    }

    @Override
    protected void setSceneValidators() {
        ParentValidator.builder()
                .withFirstNameValidator(firstNameTextField)
                .withLastNameValidator(lastNameTextField)
                .withPeselValidator(peselTextField)
                .withCityValidator(cityTextField)
                .withAddressValidator(addressTextField)
                .withEmailValidator(emailTextField)
                .withCorrectRoleField(roleTextField)
                .withUsernameValidator(usernameTextField)
                .withPhoneNumberValidator(phoneNumberTextField)
                .withPostalCodeValidator(postalCodeTextField)
                .withNotEmptyStudentComboBox(studentComboBox)
                .build();
    }
}
