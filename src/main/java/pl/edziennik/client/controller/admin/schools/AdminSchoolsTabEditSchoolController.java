package pl.edziennik.client.controller.admin.schools;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.task.school.EditSchoolTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;
import pl.edziennik.client.validator.school.SchoolValidator;

public class AdminSchoolsTabEditSchoolController extends AdminSchoolsTabActionAbstractController {

    @FXML
    private Button cancelButton, saveButton;

    private Long objectId;

    @Override
    protected void setSceneValidators() {
        initializeValidators();
    }

    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
        initializeSaveButtonAction();
    }

    @Override
    protected void setSceneSettings() {
        super.setSceneSettings();
        NodeUtils.setTextFieldAsNumbersOnly(phoneNumberTextField, nipTextField, regonTextField);
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, nameTextField, addressTextField,
                postalCodeTextField, cityTextField, nipTextField,
                regonTextField, phoneNumberTextField);
    }

    @Override
    protected void loadStageFields(SchoolDto schoolDto) {
        super.loadStageFields(schoolDto);
        this.objectId = schoolDto.getSchoolId();
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) saveButton.getScene().getWindow();
    }

    private void initializeValidators() {
        SchoolValidator.builder()
                .withNameValidator(nameTextField)
                .withPhoneNumberValidator(phoneNumberTextField)
                .withAddressValidator(addressTextField)
                .withCityValidator(cityTextField)
                .withNipValidator(nipTextField)
                .withRegonValidator(regonTextField)
                .withPostalCodeValidator(postalCodeTextField)
                .build();
    }

    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            SchoolDto schoolDto = mapToSchoolPojo();
            ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new EditSchoolTask(objectId, schoolDto), (response) -> {
                AdminSchoolsTabController controller = AdminSchoolsTabController.getInstance();
                controller.refreshButton.fire();
                dialogFactory.createSuccessInformationDialog(null);
            }));
        });
    }




}
