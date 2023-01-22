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

public class AdminSchoolsTabEditSchoolController extends AbstractController {

    @FXML
    private TextField nameTextField, addressTextField, postalCodeTextField, cityTextField, nipTextField,
            regonTextField, phoneNumberTextField;

    @FXML
    private ComboBox<SchoolLevelComboBoxItem> schoolLevelComboBox;

    @FXML
    private Button cancelButton, saveButton;

    private Long objectId;

    @Override
    protected void fetchStageData() {
        fetchComboBoxItems();
    }

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
        NodeUtils.setTextFieldAsNumbersOnly(phoneNumberTextField, nipTextField, regonTextField);
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, nameTextField, addressTextField,
                postalCodeTextField, cityTextField, nipTextField,
                regonTextField, phoneNumberTextField);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) saveButton.getScene().getWindow();
    }

    public void loadStageFields(SchoolPojo schoolPojo) {
        nameTextField.setText(schoolPojo.getName());
        nipTextField.setText(schoolPojo.getNip());
        regonTextField.setText(schoolPojo.getRegon());
        addressTextField.setText(schoolPojo.getAddress());
        postalCodeTextField.setText(schoolPojo.getPostalCode());
        phoneNumberTextField.setText(schoolPojo.getPhoneNumber());
        cityTextField.setText(schoolPojo.getCity());
        schoolLevelComboBox.getSelectionModel().select(getCorrectComboBoxItem(schoolPojo.getIdSchoolLevel()));
        this.objectId = schoolPojo.getId();
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

    private void fetchComboBoxItems() {
        if (schoolLevelComboBox.getItems().isEmpty()) {
            progressFactory.createLittleProgressBar(new LoadSchoolLevelsTask(), (response) -> {
                schoolLevelComboBox.setItems(FXCollections.observableList(response));
                schoolLevelComboBox.getSelectionModel().selectFirst();
            });
        }

    }

    private SchoolLevelComboBoxItem getCorrectComboBoxItem(Long id) {
        return schoolLevelComboBox.getItems()
                .stream()
                .filter(item -> item.getId().getValue().equals(id))
                .findFirst()
                .orElse(null);
    }

    private SchoolPojo mapToSchoolPojo() {
        SchoolPojo schoolPojo = new SchoolPojo();
        schoolPojo.setName(nameTextField.getText());
        schoolPojo.setCity(cityTextField.getText());
        schoolPojo.setIdSchoolLevel(schoolLevelComboBox.getSelectionModel().getSelectedItem().getId().getValue());
        schoolPojo.setNip(nipTextField.getText());
        schoolPojo.setRegon(regonTextField.getText());
        schoolPojo.setPhoneNumber(phoneNumberTextField.getText());
        schoolPojo.setAddress(addressTextField.getText());
        schoolPojo.setPostalCode(postalCodeTextField.getText());
        return schoolPojo;
    }
}
