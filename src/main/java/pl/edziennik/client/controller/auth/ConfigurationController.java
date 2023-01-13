package pl.edziennik.client.controller.auth;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.configuration.PropertiesLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {

    private DialogFactory dialogFactory;

    public ConfigurationController() {
        this.dialogFactory = DialogFactory.getInstance();
    }

    @FXML
    private TextField serverAddressInput;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private Button configurationButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeServerAddressTextFieldValue();
        initializeComboBoxItems();
        initializeConfigurationButtonAction();
    }

    private void initializeServerAddressTextFieldValue() {
        serverAddressInput.setText(PropertiesLoader.readProperty("serverAddress"));
    }

    private void initializeConfigurationButtonAction() {
        configurationButton.setOnAction(input -> {
            if(isValuesChanged()) {
                PropertiesLoader.writeConfigurationDataToFile(languageComboBox.getSelectionModel().getSelectedItem(), serverAddressInput.getText());
                dialogFactory.createSuccessInformationDialog(ResourceConst.SUCCESS_DIALOG_SAVE_CONFIGURATION_MESSAGE.value());
            }
        });
    }

    private boolean isValuesChanged() {
        boolean isServerAddressTheSame = serverAddressInput.getText().equals(PropertiesLoader.readProperty("serverAddress"));
        boolean isLanguageComboBoxTheSame = languageComboBox.getSelectionModel().getSelectedItem().equals(PropertiesLoader.readProperty("language"));
        return !isServerAddressTheSame || !isLanguageComboBoxTheSame;
    }

    private void initializeComboBoxItems() {
        languageComboBox.getItems().setAll("Polski", "English");
        languageComboBox.getSelectionModel().select(PropertiesLoader.readProperty("language"));
    }
}
