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
import pl.edziennik.client.task.school.LoadSchoolLevelsTask;
import pl.edziennik.client.utils.NodeUtils;

public class AdminSchoolsTabShowSchoolController extends AbstractController {

    @FXML
    private TextField nameTextField, addressTextField, postalCodeTextField, cityTextField, nipTextField,
            regonTextField, phoneNumberTextField;

    @FXML
    private ComboBox<SchoolLevelComboBoxItem> schoolLevelComboBox;

    @FXML
    private Button cancelButton;

    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
    }

    @Override
    protected void setSceneSettings() {
        fetchComboBoxItems();
    }


    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
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
        schoolLevelComboBox.setOnShown(show -> schoolLevelComboBox.hide());
    }

    private SchoolLevelComboBoxItem getCorrectComboBoxItem(Long id) {
        return schoolLevelComboBox.getItems()
                .stream()
                .filter(item -> item.getId().getValue().equals(id))
                .findFirst()
                .orElse(null);
    }


    private void fetchComboBoxItems() {
        if (schoolLevelComboBox.getItems().isEmpty()) {
            progressFactory.createLittleProgressBar(new LoadSchoolLevelsTask(), (response) -> {
                schoolLevelComboBox.setItems(FXCollections.observableList(response));
                schoolLevelComboBox.getSelectionModel().selectFirst();
            });
        }

    }
}
