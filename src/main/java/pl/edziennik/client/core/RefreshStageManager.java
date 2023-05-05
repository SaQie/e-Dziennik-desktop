package pl.edziennik.client.core;

import javafx.scene.control.Button;

public class RefreshStageManager {

    private static Button refreshButton;

    public static void setRefreshButton(Button refreshButton) {
        RefreshStageManager.refreshButton = refreshButton;
    }

    public static void refreshScene() {
        refreshButton.fire();
        refreshButton = null;
    }

}
