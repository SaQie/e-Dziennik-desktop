package pl.edziennik.client.controller.admin.schools;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.common.ProgressFactory;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.task.LoadSchoolLevelsTask;
import pl.edziennik.client.utils.NodeUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminSchoolsTabAddSchoolController implements Initializable {

    private final ProgressFactory progressFactory;

    public AdminSchoolsTabAddSchoolController() {
        this.progressFactory = ProgressFactory.getInstance();
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
        fetchComboBoxItems();
    }

    private void fetchComboBoxItems() {
        if (schoolLevelComboBox.getItems().isEmpty()){
            progressFactory.createLittleProgressBar(new LoadSchoolLevelsTask(), (response) -> {
                schoolLevelComboBox.setItems(FXCollections.observableList(response));
                schoolLevelComboBox.getSelectionModel().selectFirst();
            });
        }

    }
}
