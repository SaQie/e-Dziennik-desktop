package pl.edziennik.client.controller.admin.configuration;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.rest.pojo.ConfigurationPojo;
import pl.edziennik.client.rest.pojo.SettingsValue;
import pl.edziennik.client.task.config.SaveConfigurationTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;

public class AdminConfigurationOptionController extends AbstractController {

    @FXML
    private Label firstParameterLabel, secondParameterLabel;

    @FXML
    private CheckBox firstParameterValue, secondParameterValue;

    @FXML
    private Button cancelButton, saveButton;

    private Long firstParameterId, secondParameterId;


    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
        initializeSaveButtonAction();
    }

    @Override
    protected void setSceneSettings() {

    }

    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            List<SettingsValue> configurationPojos = new ArrayList<>();
            configurationPojos.add(new SettingsValue(firstParameterId, firstParameterValue.isSelected()));
            configurationPojos.add(new SettingsValue(secondParameterId, secondParameterValue.isSelected()));
            progressFactory.createLittleProgressBar(new SaveConfigurationTask(configurationPojos), (response) -> {
                getActualStage().close();
            });
        });
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }

    public void fetchData(List<ConfigurationPojo> configurationList) {
        firstParameterLabel.setText(configurationList.get(0).getName());
        firstParameterValue.setSelected(configurationList.get(0).isEnabled());
        firstParameterId = configurationList.get(0).getId();

        secondParameterLabel.setText(configurationList.get(1).getName());
        secondParameterValue.setSelected(configurationList.get(1).isEnabled());
        secondParameterId = configurationList.get(1).getId();
    }
}
