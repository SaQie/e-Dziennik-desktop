package pl.edziennik.client.controller.teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesStringToLongConverter;
import pl.edziennik.client.controller.teacher.subject.TeacherSubjectsManagmentController;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.task.teacher.LoadTeacherSubjectsTask;
import pl.edziennik.client.util.NodeUtils;
import pl.edziennik.client.util.ThreadUtils;

public class TeacherController extends AbstractController {

    @FXML
    private Label timerLabel;

    @FXML
    private Button exitButton, logoutButton;

    @FXML
    private TeacherSubjectsManagmentController subjectManagmentTabController;


    @Override
    protected void createActions() {
        NodeUtils.createExitButtonAction(exitButton);
        NodeUtils.createLogoutButton(logoutButton);
    }

    @Override
    protected void setSceneSettings() {
        NodeUtils.createTimer(timerLabel);
        fetchTableItemsIfNeeded();
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) exitButton.getScene().getWindow();
    }

    private void fetchTableItemsIfNeeded() {
        if (subjectManagmentTabController.isTableDataEmpty()) {
            fetchTeacherSubjectsTableData();
        }
    }

    private void fetchTeacherSubjectsTableData() {
        Long superId = PropertiesLoader.readProperty(ResourceConst.PROPERTIES_LOADER_SUPER_ID_KEY.value(),
                new PropertiesStringToLongConverter());

        ThreadUtils.runInNewFxThread(() ->
                progressFactory.createLittleProgressBar(new LoadTeacherSubjectsTask(superId),
                        (response) -> subjectManagmentTabController.fetchTabData(response)));
    }
}
