package pl.edziennik.client.controller.admin.accounts.student;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.Role;
import pl.edziennik.client.controller.model.admin.SchoolClassComboBoxItem;
import pl.edziennik.client.controller.model.admin.SchoolComboBoxItem;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.task.schoolclass.LoadSchoolClassesTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.validator.student.AddStudentValidator;

import java.util.ArrayList;
import java.util.List;

import static pl.edziennik.client.common.ResourceConst.*;

public class AdminAccountsTabStudentsAddController extends AbstractController {


    @FXML
    private ComboBox<SchoolComboBoxItem> schoolComboBox;

    @FXML
    private ComboBox<SchoolClassComboBoxItem> schoolClassComboBox;

    @FXML
    private TextField usernameTextField, firstNameTextField, lastNameTextField, addressTextField, postalCodeTextField, cityTextField,
            peselTextField, parentFirstNameTextField, parentLastNameTextField, parentPhoneNumberTextField, emailTextField, roleTextField;

    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
    }

    @Override
    protected void fetchStageData() {
        roleTextField.setText(Role.ROLE_STUDENT.name());
        fetchSchoolComboBoxItemsIfNeeded();
    }

    private void fetchSchoolComboBoxItemsIfNeeded() {
        schoolClassComboBox.disableProperty().bind(schoolComboBox.valueProperty().isNull());
        schoolComboBox.valueProperty().addListener((value) -> {
            if (schoolComboBox.getValue() != null) {
                Long idSchool = schoolComboBox.getValue().getId().getValue();
                progressFactory.createLittleProgressBar(new LoadSchoolClassesTask(idSchool), (response) -> {
                    List<SchoolClassComboBoxItem> comboBoxItems = response.stream().map(SchoolClassComboBoxItem::new).toList();
                    schoolClassComboBox.setItems(FXCollections.observableList(comboBoxItems));
                });
            }
            if (schoolClassComboBox.getValue() != null) {
                schoolClassComboBox.getSelectionModel().select(null);
            }
        });
        if (schoolComboBox.getItems().isEmpty()) {
            progressFactory.createLittleProgressBar(new LoadSchoolsTask(), (response) -> {
                List<SchoolComboBoxItem> comboBoxItems = response.stream().map(SchoolComboBoxItem::new).toList();
                schoolComboBox.setItems(FXCollections.observableList(comboBoxItems));
            });
        }
    }

    @Override
    protected void setSceneSettings() {
        initializeValidators();
        NodeUtils.enableButtonIfFieldsHasNoErrors(saveButton, usernameTextField, firstNameTextField, lastNameTextField,
                addressTextField, postalCodeTextField, cityTextField,
                peselTextField, parentFirstNameTextField, parentLastNameTextField,
                parentPhoneNumberTextField, emailTextField);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }

    private void initializeValidators() {
        AddStudentValidator.builder()
                .withCityValidator(cityTextField)
                .withEmailValidator(emailTextField)
                .withFirstNameValidator(firstNameTextField)
                .withFirstNameValidator(parentFirstNameTextField)
                .withLastNameValidator(lastNameTextField)
                .withLastNameValidator(parentLastNameTextField)
                .withPeselValidator(peselTextField)
                .withUsernameValidator(usernameTextField)
                .withAddressValidator(addressTextField)
                .withPostalCodeValidator(postalCodeTextField)
                .withPhoneNumberValidator(parentPhoneNumberTextField)
                .build();

    }
}
