package pl.edziennik.client.common;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import lombok.SneakyThrows;
import pl.edziennik.client.controller.ProgressController;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class ProgressFactory {

    private static ProgressFactory factory;

    private String viewLargeUrl = "/pl/edziennik/client/waiting-pop-up.fxml";
    private String viewLittleUrl = "/pl/edziennik/client/waiting-pop-up-little.fxml";

    private ProgressFactory() {
    }

    public static ProgressFactory getInstance() {
        if (factory == null) {
            factory = new ProgressFactory();
        }
        return factory;
    }

    @SneakyThrows
    public <T> void createLargeProgressBar(Task<T> task, Consumer<T> consumer) {
        ProgressController controller = createBasicProgressBarView(300, 200, viewLargeUrl);
        controller.startLarge(task, () -> {
            if (task.isDone()) {
                try {
                    consumer.accept(task.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public <T> void createLittleProgressBar(Task<T> task, Consumer<T> consumer) {
        ProgressController controller = createBasicProgressBarView(250, 150, viewLittleUrl);
        controller.startLittle(task, () -> {
            if (task.isDone()) {
                try {
                    consumer.accept(task.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @SneakyThrows
    private ProgressController createBasicProgressBarView(int width, int height, String view) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        Scene scene = new Scene((AnchorPane) loader.load(), width, height);
        ProgressController controller = loader.<ProgressController>getController();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        scene.setFill(Color.TRANSPARENT);
        stage.requestFocus();
        stage.setAlwaysOnTop(true);
        Stage actualStage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
        if (actualStage != null) {
            stage.initOwner(actualStage);
            stage.setX(actualStage.getX() + actualStage.getWidth() / 2 - scene.getWidth() / 2);
            stage.setY(actualStage.getY() + actualStage.getHeight() / 2 - scene.getHeight() / 2);
        }
        stage.setScene(scene);
        stage.show();
        return controller;
    }
}
