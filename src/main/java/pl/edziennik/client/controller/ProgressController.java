package pl.edziennik.client.controller;

import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.utils.ThreadUtils;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgressController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(ProgressController.class.getName());

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

    private DialogFactory dialogFactory;

    public ProgressController() {
        this.dialogFactory = DialogFactory.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @SuppressWarnings("all")
    public void startLarge(Task task, Runnable runnable) {
        progressBar.progressProperty().bind(task.progressProperty());
        operationLabel.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(succes -> {
            getStage().close();
            runnable.run();
        });

        task.setOnFailed(failed -> {
            cancelTask(task, failed, getStage());
        });

        task.setOnCancelled(cancelled -> {
            cancelTask(task, cancelled, getStage());
        });

        ThreadUtils.runInBackgroundThread(task);

    }

    @SuppressWarnings("all")
    public void startLittle(Task task, Runnable runnable) {
        indicator.progressProperty().bind(task.progressProperty());
        label.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(succes -> {
            getStageLittle().close();
            runnable.run();
        });

        task.setOnFailed(failed -> {
            cancelTask(task, failed, getStageLittle());
        });

        task.setOnCancelled(cancelled -> {
            cancelTask(task, cancelled, getStageLittle());
        });

        ThreadUtils.runInBackgroundThread(task);
    }



    private Stage getStageLittle() {
        return (Stage) label.getScene().getWindow();
    }

    private Stage getStage() {
        return (Stage) progresBarPane.getScene().getWindow();
    }

    @SuppressWarnings("rawtypes")
    private void cancelTask(Task task, Event event, Stage progressStage) {
        progressStage.close();
        String taskSimpleName = event.getTarget().getClass().getSimpleName();
        LOGGER.log(Level.SEVERE, "Error during execute task " + taskSimpleName);
        LOGGER.log(Level.SEVERE, "Cause: " + task.getMessage());
        dialogFactory.createErrorProgressDialog(task.getMessage(), taskSimpleName);
    }

}
