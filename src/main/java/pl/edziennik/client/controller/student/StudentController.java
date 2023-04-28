package pl.edziennik.client.controller.student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesStringToLongConverter;
import pl.edziennik.client.controller.student.grades.StudentGradesTabController;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.task.student.LoadAllStudentGradesTask;
import pl.edziennik.client.utils.NodeUtils;
import pl.edziennik.client.utils.ThreadUtils;

public class StudentController extends AbstractController {

    @FXML
    private Tab gradesTab;

    @FXML
    private StudentGradesTabController gradesTabController;

    @FXML
    private Label timerLabel;

    @FXML
    private Button exitButton, logoutButton;

    @FXML
    private TabPane mainViewPane;

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

    private void fetchTableItemsIfNeeded() {
        if (gradesTabController.isTableDataEmpty()) {
            fetchGradesTableData();
        }
    }

    private void fetchGradesTableData() {
        Long idUser = PropertiesLoader.readProperty(ResourceConst.PROPERTIES_LOADER_ID_KEY.value(), new PropertiesStringToLongConverter());

        ThreadUtils.runInNewFxThread(() ->
                progressFactory.createLittleProgressBar(new LoadAllStudentGradesTask(3L),
                        (response) -> gradesTabController.fetchTabData(response)));
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) exitButton.getScene().getWindow();
    }
}
