package pl.edziennik.client.controller.admin.schools;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.task.school.EditSchoolTask;
import pl.edziennik.client.task.school.LoadSchoolLevelsTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;
import pl.edziennik.client.validator.school.AddSchoolValidator;

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
    protected void loadStageFields(SchoolPojo schoolPojo) {
        super.loadStageFields(schoolPojo);
        this.objectId = schoolPojo.getId();
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) saveButton.getScene().getWindow();
    }

    private void initializeValidators() {
        AddSchoolValidator.builder()
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
            SchoolPojo schoolPojo = mapToSchoolPojo();
            ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new EditSchoolTask(objectId, schoolPojo), (response) -> {
                AdminSchoolsTabController controller = AdminSchoolsTabController.getInstance();
                controller.refreshButton.fire();
                dialogFactory.createSuccessInformationDialog(null);
            }));
        });
    }




}
