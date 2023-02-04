package pl.edziennik.client.controller.admin.schools;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.task.school.LoadSchoolLevelsTask;

abstract class AdminSchoolsTabActionAbstractController extends AbstractController {

    @FXML
    protected TextField nameTextField, addressTextField, postalCodeTextField, cityTextField, nipTextField,
            regonTextField, phoneNumberTextField;

    @FXML
    private ComboBox<SchoolLevelComboBoxItem> schoolLevelComboBox;

    @Override
    protected void setSceneSettings() {
        fetchComboBoxItems();
    }

    protected void loadStageFields(SchoolPojo schoolPojo) {
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

    protected SchoolPojo mapToSchoolPojo() {
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
