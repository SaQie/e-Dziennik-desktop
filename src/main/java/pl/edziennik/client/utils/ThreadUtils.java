package pl.edziennik.client.utils;

import javafx.application.Platform;
import pl.edziennik.client.exception.RestClientException;

import java.util.Objects;

public class ThreadUtils {

    public static void runInFxThread(final Runnable runnable){
        Objects.requireNonNull(runnable);
        if (Platform.isFxApplicationThread()){
            runnable.run();
            return;
        }
        Platform.runLater(runnable);
    }

    public static void runInNewFxThread(final Runnable runnable){
        Objects.requireNonNull(runnable);
        Platform.runLater(runnable);
    }

    public static void runInBackgroundThread(Runnable runnable){
        try {
            Objects.requireNonNull(runnable);
            if (Platform.isFxApplicationThread()) {
                new Thread(runnable).start();
            } else {
                runnable.run();
            }
        }catch (RestClientException e){
            System.out.println("xxxx");
        }
    }

    public static void runInNewBackgroundThread(Runnable runnable){
        Objects.requireNonNull(runnable);
        new Thread(runnable).start();
    }

}
