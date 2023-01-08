package pl.edziennik.client.controller.admin.schools;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.common.ProgressFactory;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.task.AddNewSchoolTask;
import pl.edziennik.client.task.LoadSchoolLevelsTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;
import pl.edziennik.client.validator.school.AddSchoolValidator;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class AdminSchoolsTabAddSchoolController implements Initializable {

    private final ProgressFactory progressFactory;
    private final DialogFactory dialogFactory;

    public AdminSchoolsTabAddSchoolController() {
        this.progressFactory = ProgressFactory.getInstance();
        this.dialogFactory = DialogFactory.getInstance();
    }

    @FXML
    private TextField nameTextField, addressTextField, postalCodeTextField, cityTextField, nipTextField,
            regonTextField, phoneNumberTextField;

    @FXML
    private ComboBox<SchoolLevelComboBoxItem> schoolLevelComboBox;

    @FXML
    private Button cancelButton, saveButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NodeUtils.createCancelButtonAction(cancelButton);
        NodeUtils.setTextFieldAsNumbersOnly(phoneNumberTextField, nipTextField, regonTextField);
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, nameTextField, addressTextField,
                postalCodeTextField, cityTextField, nipTextField,
                regonTextField, phoneNumberTextField);
        fetchComboBoxItems();
        initializeSaveButtonAction();
        initializeValidators();
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
