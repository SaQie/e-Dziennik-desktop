package pl.edziennik.client.controller.admin.accounts.parent;

import pl.edziennik.client.rest.dto.parent.ParentRequestDto;
import pl.edziennik.client.task.parent.AddNewParentTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.validator.parent.ParentValidator;

public class AdminAccountsTabParentsAddController extends AdminAccountsTabParentActionAbstractController {


    @Override
    protected void setSceneSettings() {
        super.setSceneSettings();
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, usernameTextField, firstNameTextField, lastNameTextField,
                addressTextField, postalCodeTextField, cityTextField,
                peselTextField, emailTextField, phoneNumberTextField, studentComboBox);
        NodeUtils.setTextFieldAsNumbersOnly(peselTextField, phoneNumberTextField);
    }

    @Override
    protected void createActions() {
        super.createActions();
        initializeSaveButtonAction();
    }

    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            ParentRequestDto request = mapToDto();
            progressFactory.createLittleProgressBar(new AddNewParentTask(request), (response) -> {
                AdminAccountsTabParentsTabController controller = AdminAccountsTabParentsTabController.getInstance();
                controller.refreshButton.fire();
                NodeUtils.closeCurrentStage(getActualStage());
                dialogFactory.createSuccessInformationDialog(null);
            });
        });
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
