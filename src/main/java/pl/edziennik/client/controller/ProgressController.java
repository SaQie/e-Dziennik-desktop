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

    @FXML
    private AnchorPane littlePane;


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
