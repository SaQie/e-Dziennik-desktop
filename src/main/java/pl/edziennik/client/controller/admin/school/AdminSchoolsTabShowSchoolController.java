package pl.edziennik.client.controller.admin.school;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.edziennik.client.util.NodeUtils;

public class AdminSchoolsTabShowSchoolController extends AdminSchoolsTabActionAbstractController {

    @FXML
    private Button cancelButton;

    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
    }

    @Override
    protected Stage getActualStage() {
        return (Stage) cancelButton.getScene().getWindow();
    }


}
