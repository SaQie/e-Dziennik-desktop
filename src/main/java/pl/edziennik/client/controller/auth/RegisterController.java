package pl.edziennik.client.controller.auth;

import javafx.scene.control.Tab;

public class RegisterController extends AbstractAuthorizationController {

    private Tab registerTab;


    @Override
    protected void createActions() {
        super.createActions();
        initializeCancelButtonAction();
    }


    private void initializeCancelButtonAction() {
        cancelButton.setOnAction((button) -> {
            getActualStage().close();
            registerTab.getTabPane().getSelectionModel().selectFirst();
        });
    }

    public void setRegisterTab(Tab registerTab) {
        this.registerTab = registerTab;
    }


}
