package pl.edziennik.client.task.config;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.ConfigurationPojo;
import pl.edziennik.client.rest.pojo.SettingsValue;

import java.util.List;

public class SaveConfigurationTask extends Task<Void> {

    private final AdminRestClient adminRestClient;
    private final List<SettingsValue> settingsValues;

    public SaveConfigurationTask(List<SettingsValue> settingsValues) {
        this.adminRestClient = new AdminRestClient();
        this.settingsValues = settingsValues;
    }

    @Override
    protected Void call() throws Exception {
        try {
            for (SettingsValue values : settingsValues) {
                adminRestClient.saveConfiguration(values);
            }
        } catch (RestClientException e) {
            cancel(true);
        }
        return null;
    }
}
