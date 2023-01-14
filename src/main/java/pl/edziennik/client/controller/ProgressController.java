package pl.edziennik.client.controller;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import pl.edziennik.client.utils.ThreadUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProgressController implements Initializable {

    @FXML
    private Label operationLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private AnchorPane progresBarPane;

    @FXML
    private ProgressIndicator indicator;

    @FXML
    private Label label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @SuppressWarnings("all")
    public void startLarge(Task task, Runnable runnable){
        progressBar.progressProperty().bind(task.progressProperty());
        operationLabel.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(succes -> {
            getStage().close();
            runnable.run();
        });

        task.setOnFailed(failed -> {
            getStage().close();
        });

        task.setOnCancelled(cancelled -> {
            getStage().close();
        });

        ThreadUtils.runInBackgroundThread(task);

    }

    @SuppressWarnings("all")
    public void startLittle(Task task, Runnable runnable){
        indicator.progressProperty().bind(task.progressProperty());
        label.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(succes -> {
            getStageLittle().close();
            runnable.run();
        });

        task.setOnFailed(failed -> {
            getStageLittle().close();
        });

        task.setOnCancelled(cancelled -> {
            getStageLittle().close();
            Stage actualStage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).reduce((first, last) -> last).orElse(null);
            actualStage.close();
            // TODO dorobic lapanie wyjatkow tutaj, + czas w jakim zostalo wykonane do loggera + zamykanie poprzedniej sceny
        });

        ThreadUtils.runInBackgroundThread(task);
    }

    private Stage getStageLittle(){
        return (Stage) label.getScene().getWindow();
    }

    private Stage getStage() {
        return (Stage) progresBarPane.getScene().getWindow();
    }

}
