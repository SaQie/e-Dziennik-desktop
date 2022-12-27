package pl.edziennik.client.utils;

import javafx.application.Platform;

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

    public static void runInBackgroundThread(Runnable runnable){
        Objects.requireNonNull(runnable);
        if (Platform.isFxApplicationThread()){
            new Thread(runnable).start();
        }else{
            runnable.run();
        }
    }

    public static void runInNewBackgroundThread(Runnable runnable){
        Objects.requireNonNull(runnable);
        new Thread(runnable).start();
    }

}
