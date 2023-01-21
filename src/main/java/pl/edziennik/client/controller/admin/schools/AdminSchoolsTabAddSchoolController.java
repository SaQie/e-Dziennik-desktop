package pl.edziennik.client.controller.admin.schools;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.common.ProgressFactory;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.task.AddNewSchoolTask;
import pl.edziennik.client.task.LoadSchoolLevelsTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;
import pl.edziennik.client.validator.school.AddSchoolValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminSchoolsTabAddSchoolController extends AbstractController {

    @FXML
    private TextField nameTextField, addressTextField, postalCodeTextField, cityTextField, nipTextField,
            regonTextField, phoneNumberTextField;

    @FXML
    private ComboBox<SchoolLevelComboBoxItem> schoolLevelComboBox;

    @FXML
    private Button cancelButton, saveButton;


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
            ThreadUtils.runInNewFxThread(() -> progressFactory.createLittleProgressBar(new AddNewSchoolTask(schoolPojo), (response) -> {
                AdminSchoolsTabController controller = AdminSchoolsTabController.getInstance();
                controller.addItem(SchoolListModel.mapPojoToModel(response));
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
