package pl.edziennik.client.controller.admin.schools;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.rest.dto.schoollevel.SimpleSchoolLevelDto;
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

    protected void loadStageFields(SchoolDto schoolDto) {
        nameTextField.setText(schoolDto.getName());
        nipTextField.setText(schoolDto.getNip());
        regonTextField.setText(schoolDto.getRegon());
        addressTextField.setText(schoolDto.getAddress());
        postalCodeTextField.setText(schoolDto.getPostalCode());
        phoneNumberTextField.setText(schoolDto.getPhoneNumber());
        cityTextField.setText(schoolDto.getCity());
        schoolLevelComboBox.getSelectionModel().select(new SchoolLevelComboBoxItem(schoolDto.getSchoolLevel()));
        schoolLevelComboBox.setOnShown(show -> schoolLevelComboBox.hide());
    }

    protected SchoolDto mapToSchoolPojo() {
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setName(nameTextField.getText());
        schoolDto.setCity(cityTextField.getText());
        SimpleSchoolLevelDto simpleSchoolLevelDto = new SimpleSchoolLevelDto();
        simpleSchoolLevelDto.setId(schoolLevelComboBox.getSelectionModel().getSelectedItem().getId().getValue());
        simpleSchoolLevelDto.setName(schoolLevelComboBox.getSelectionModel().getSelectedItem().getName().getValue());
        schoolDto.setSchoolLevel(simpleSchoolLevelDto);
        schoolDto.setIdSchoolLevel(schoolLevelComboBox.getSelectionModel().getSelectedItem().getId().getValue());
        schoolDto.setNip(nipTextField.getText());
        schoolDto.setRegon(regonTextField.getText());
        schoolDto.setPhoneNumber(phoneNumberTextField.getText());
        schoolDto.setAddress(addressTextField.getText());
        schoolDto.setPostalCode(postalCodeTextField.getText());
        return schoolDto;
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
