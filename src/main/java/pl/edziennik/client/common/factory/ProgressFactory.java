package pl.edziennik.client.common.factory;

import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.builder.CommonStageBuilder;
import pl.edziennik.client.controller.ProgressController;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.logging.Logger;

import static  pl.edziennik.client.common.builder.CommonStageBuilder.StageBuilder.ShowMode.*;

public class ProgressFactory {

    private static ProgressFactory factory;

    private static final Logger LOGGER = Logger.getLogger(ProgressFactory.class.getName());

    private ProgressFactory() {
    }

    public static ProgressFactory getInstance() {
        if (factory == null) {
            factory = new ProgressFactory();
        }
        return factory;
    }

    public <T> void createLargeProgressBar(Task<T> task, Consumer<T> consumer) {
        ProgressController controller = createBasicProgressBarView(300, 200, ResourceConst.PROGRESS_BAR_LARGE_VIEW_ADDRESS.value());
        controller.startLarge(task, () -> checkTaskIsDone(task, consumer));
    }

    public <T> void createLittleProgressBar(Task<T> task, Consumer<T> consumer) {
        ProgressController controller = createBasicProgressBarView(250, 150, ResourceConst.PROGRESS_BAR_LITTLE_VIEW_ADDRESS.value());
        controller.startLittle(task, () -> checkTaskIsDone(task, consumer));
    }


    private static <T> void checkTaskIsDone(Task<T> task, Consumer<T> consumer) {
        if (task.isDone()) {
            try {
                consumer.accept(task.get());
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.severe(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @SneakyThrows
    private ProgressController createBasicProgressBarView(int width, int height, String view) {
        return CommonStageBuilder.stageBuilder()
                .withView(view)
                .withWidth(width)
                .withHeight(height)
                .withStyle(StageStyle.TRANSPARENT)
                .withModality(Modality.APPLICATION_MODAL)
                .withFillColor(Color.TRANSPARENT)
                .withFocusRequest(true)
                .withAlwaysOnTop(true)
                .withShowMode(OPEN_ABOVE_AND_RETURN_CONTROLLER)
                .build();
    }
}
