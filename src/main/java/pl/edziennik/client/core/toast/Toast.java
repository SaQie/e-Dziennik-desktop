package pl.edziennik.client.core.toast;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.edziennik.client.core.StageManager;

import static pl.edziennik.client.common.Styles.createPopup;

public class Toast {

    private static final int TOAST_TIMEOUT = 3500;
    private static final int POPUP_OFFSET = 15;

    private static volatile boolean isShowing = false;

    public static void show(final String messageKey) {
        if (!isShowing) {

            Stage stage = StageManager.getMainStage();
            final Popup popup = createPopup(messageKey);
            popup.setOnShown(event -> {
                double x = stage.getX() + stage.getWidth() - popup.getWidth() - POPUP_OFFSET;
                double y = stage.getY() + stage.getHeight() - popup.getHeight() - POPUP_OFFSET;
                popup.setX(x);
                popup.setY(y);
            });

            popup.show(stage);
            isShowing = true;

            new Timeline(new KeyFrame(
                    Duration.millis(TOAST_TIMEOUT),
                    ae -> {
                        popup.hide();
                        isShowing = false;
                    })).play();
        }
    }


}
