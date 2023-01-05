package pl.edziennik.client.controller.auth;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.edziennik.client.common.AccountType;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {


    @FXML
    private TextField serverAddressInput;

    @FXML
    private ComboBox<String> languageComboBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        languageComboBox.getItems().setAll("en_EN", "pl_PL");
        languageComboBox.getSelectionModel().selectFirst();
    }
}
