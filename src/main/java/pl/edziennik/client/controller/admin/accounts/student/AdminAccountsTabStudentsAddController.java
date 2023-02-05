package pl.edziennik.client.controller.admin.accounts.student;

import javafx.stage.Stage;
import pl.edziennik.client.core.AbstractController;
import pl.edziennik.client.utils.NodeUtils;

public class AdminAccountsTabStudentsAddController extends AbstractController {


    @Override
    protected void createActions() {
        NodeUtils.createCancelButtonAction(cancelButton);
    }

    @Override
    protected void setSceneSettings() {

    }

    @Override
    protected Stage getActualStage() {
        return null;
    }
}
