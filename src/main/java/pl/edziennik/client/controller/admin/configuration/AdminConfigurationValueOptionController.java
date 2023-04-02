package pl.edziennik.client.controller.admin.configuration;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.exception.ViewException;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;
import pl.edziennik.client.rest.dto.config.SettingsValueDto;
import pl.edziennik.client.task.config.LoadConfigurationTask;
import pl.edziennik.client.task.config.SaveConfigurationTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;

public class AdminConfigurationValueOptionController extends AbstractController {

    @FXML
    private HBox hBox;

    @FXML
    private Button cancelButton, saveButton;

    private CheckBox checkBox;
    private TextField value;
    private Label label;

    private volatile ConfigurationDto dto;

    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
        initializeSaveButtonAction(saveButton);
    }

    private void initializeSaveButtonAction(Button saveButton) {
        saveButton.setOnAction(button -> {
            if (dto != null) {
                setValues(dto);
                SettingsValueDto settingValueDto = SettingsValueDto.getSettingValueDto(dto);
                progressFactory.createLittleProgressBar(new SaveConfigurationTask(Arrays.asList(settingValueDto)), (response) -> {
                    NodeUtils.closeCurrentStage(getActualStage());
                    dialogFactory.createSuccessInformationDialog(null);
                });
            }
        });
    }

    @Override
    protected void setSceneSettings() {

    }

    @Override
    protected void fetchStageData() {
        this.checkBox = Styles.tableViewSelectionCheckBox();
        checkBox.setDisable(false);
        hBox.setSpacing(20);
        this.label = new Label();
        label.setTextFill(Paint.valueOf("#FFFFFF"));
        label.setWrapText(true);
        label.setMaxWidth(300);
        hBox.getChildren().add(label);
        this.value = new TextField();
        value.setMaxWidth(150);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }

    public void fetchData(Long configId) {
        progressFactory.createLittleProgressBar(new LoadConfigurationTask(configId), (response) -> {
            this.dto = response;
            this.label.setText(response.getName());
            if (response.getBooleanValue() != null) {
                checkBox.setSelected(response.getBooleanValue());
                hBox.getChildren().add(checkBox);
            } else if (response.getStringValue() != null) {
                value.setText(response.getStringValue());
                hBox.getChildren().add(value);
            } else if (response.getLongValue() != null) {
                NodeUtils.setTextFieldAsNumbersOnly(value);
                value.setText(response.getLongValue().toString());
                hBox.getChildren().add(value);
            } else {
                throw new ViewException(ResourceUtil.getMessage("unsupported.configuration.type"));
            }
        });
    }

    private void setValues(ConfigurationDto dto) {
        if (dto.getBooleanValue() != null) {
            dto.setBooleanValue(checkBox.isSelected());
        } else if (dto.getStringValue() != null) {
            dto.setStringValue(value.getText());
        } else if (dto.getLongValue() != null) {
            String value = this.value.getText();
            dto.setLongValue(Long.valueOf(value));
        }
    }
}
