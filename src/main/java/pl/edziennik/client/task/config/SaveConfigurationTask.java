package pl.edziennik.client.task.config;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.config.SettingsValueDto;

import java.util.List;

public class SaveConfigurationTask extends Task<Void> {

    private final AdminRestClient adminRestClient;
    private final List<SettingsValueDto> settingsValueDtos;

    public SaveConfigurationTask(List<SettingsValueDto> settingsValueDtos) {
        this.adminRestClient = new AdminRestClient();
        this.settingsValueDtos = settingsValueDtos;
    }

    @Override
    protected Void call() throws Exception {
        for (SettingsValueDto values : settingsValueDtos) {
            adminRestClient.saveConfiguration(values);
        }
        return null;
    }
}
