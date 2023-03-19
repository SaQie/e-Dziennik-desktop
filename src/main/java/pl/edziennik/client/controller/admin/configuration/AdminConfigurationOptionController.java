package pl.edziennik.client.controller.admin.configuration;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.edziennik.client.controller.model.admin.SchoolComboBoxItem;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.core.StageManager;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;
import pl.edziennik.client.rest.dto.config.SettingsValueDto;
import pl.edziennik.client.task.config.SaveConfigurationTask;
import pl.edziennik.client.task.school.LoadSchoolsTask;
import pl.edziennik.client.utils.NodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminConfigurationOptionController extends AbstractController {

    @FXML
    private Label firstParameterLabel, secondParameterLabel;

    @FXML
    private CheckBox firstParameterValue, secondParameterValue;

    @FXML
    private Button cancelButton, saveButton;

    private Long firstParameterId, secondParameterId, actualSelectedSchoolToWhichStudentsCanRegister;


    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
        initializeSaveButtonAction();
    }

    @Override
    protected void setSceneSettings() {
        showChoiceDialogAfterSecondParameterIsChecked();
    }

    private void showChoiceDialogAfterSecondParameterIsChecked() {
        secondParameterValue.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                progressFactory.createLittleProgressBar(new LoadSchoolsTask(), (response) -> {
                    List<SchoolComboBoxItem> comboBoxItems = response.getEntities().stream().map(SchoolComboBoxItem::new).toList();
                    Optional<Long> result = dialogFactory.createChoiceDialog(comboBoxItems, "asd");
                    result.ifPresentOrElse((value) -> {
                        actualSelectedSchoolToWhichStudentsCanRegister = value;
                    }, () -> secondParameterValue.setSelected(false));
                });

            }
        });
    }


    private void initializeSaveButtonAction() {
        saveButton.setOnAction(button -> {
            List<SettingsValueDto> configurationPojos = new ArrayList<>();
            configurationPojos.add(new SettingsValueDto(firstParameterId, firstParameterValue.isSelected()));
            configurationPojos.add(new SettingsValueDto(secondParameterId, secondParameterValue.isSelected()));
            configurationPojos.add(new SettingsValueDto(3L, actualSelectedSchoolToWhichStudentsCanRegister));
            progressFactory.createLittleProgressBar(new SaveConfigurationTask(configurationPojos), (response) -> {
                dialogFactory.createSuccessInformationDialog(null);
                NodeUtils.closeCurrentStage(getActualStage());
            });
        });
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }

    public void fetchData(List<ConfigurationDto> configurationList) {
        // TODO trzeba wymyslec lepszy sposob na operowanie konfiguracjami w desktopie, to jest do bani,to jest rozwiazanie tymczasowe
        for (ConfigurationDto configurationDto : configurationList) {
            if (configurationDto.getId().equals(1L)){
                firstParameterLabel.setText(configurationDto.getName());
                firstParameterValue.setSelected(configurationDto.isBooleanValue());
                firstParameterId = configurationDto.getId();
            }
            if (configurationDto.getId().equals(2L)){
                secondParameterLabel.setText(configurationDto.getName());
                secondParameterValue.setSelected(configurationDto.isBooleanValue());
                secondParameterId = configurationDto.getId();
            }
            if (configurationDto.getId().equals(3L)){
                actualSelectedSchoolToWhichStudentsCanRegister = configurationDto.getLongValue();
            }
        }

    }
}
