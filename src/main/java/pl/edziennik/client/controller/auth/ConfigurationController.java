package pl.edziennik.client.controller.auth;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.core.AbstractController;

public class ConfigurationController extends AbstractController {

    @FXML
    private TextField serverAddressInput;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private Button configurationButton;


    @Override
    protected void fetchStageData() {
        initializeComboBoxItems();
    }

    @Override
    protected void createActions() {
        initializeConfigurationButtonAction();
        initializeServerAddressTextFieldValue();
    }

    @Override
    protected void setSceneSettings() {

    }

    @Override
    protected Stage getActualStage() {
        return (Stage) serverAddressInput.getScene().getWindow();
    }

    private void initializeServerAddressTextFieldValue() {
        serverAddressInput.setText(PropertiesLoader.readProperty("serverAddress"));
    }

    private void initializeConfigurationButtonAction() {
        configurationButton.setOnAction(input -> {
            if (isValuesChanged()) {
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
