package pl.edziennik.client.task.config;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class LoadConfigurationsTask extends Task<List<ConfigurationDto>> {

    private final AdminRestClient restClient;

    public LoadConfigurationsTask() {
        this.restClient = new AdminRestClient();
    }

    @Override
    protected List<ConfigurationDto> call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.getConfigurationList();
    }
}
