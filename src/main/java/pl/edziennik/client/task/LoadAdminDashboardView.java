package pl.edziennik.client.task;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import pl.edziennik.client.rest.pojo.AdminPojo;

public class LoadAdminDashboardView extends Task<Void> {

    @Override
    protected Void call() throws Exception {
        updateMessage("Wczytuje statystki");
        updateProgress(20, 100);
        Thread.sleep(3000);
        updateMessage("Wczytuje dane wykresow");
        updateProgress(40, 100);
        Thread.sleep(3000);
        updateMessage("Wczytuje konta");
        updateProgress(60, 100);
        Thread.sleep(3000);
        updateMessage("Wczytuje konfiguracje");
        updateProgress(80, 100);
        Thread.sleep(3000);
        return null;
    }
}
