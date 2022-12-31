package pl.edziennik.client.controller;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;

public class ProgressTaskExample extends Task<Void> {

    private final AdminRestClient adminRestClient;

    public ProgressTaskExample() {
        this.adminRestClient = new AdminRestClient();
    }

    @Override
    protected Void call() throws Exception {
        try {
            Thread.sleep(2000);

            succeeded();
            return null;
        }catch (RestClientException e){
            cancel(true);
        }
        return null;
    }
}
