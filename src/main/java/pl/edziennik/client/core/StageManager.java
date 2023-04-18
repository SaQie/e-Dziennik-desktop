package pl.edziennik.client.core;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StageManager {

    private StageManager() {

    }

    private static Stage MAIN_STAGE = null;

    public static Stage getMainStage() {
        return MAIN_STAGE;
    }

    public static void setMainStage(Stage stage) {
        MAIN_STAGE = stage;
    }

    public static boolean isShowing(Stage stage) {
        Button button = (Button) stage.getUserData();
        return button.isDisabled();
    }

    public static void setIsShowing(Stage stage) {
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, (event) -> {
            disableButtonIfNeeded(stage, false);
        });
        disableButtonIfNeeded(stage, true);
    }

    public static void close(Stage stage) {
        disableButtonIfNeeded(stage, false);
        stage.close();
    }

    private static void disableButtonIfNeeded(Stage stage, boolean disable) {
        Button button = (Button) stage.getUserData();
        if (button != null) {
            button.setDisable(disable);
        }
    }
}
