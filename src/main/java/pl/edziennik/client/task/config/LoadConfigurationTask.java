package pl.edziennik.client.task.config;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class LoadConfigurationTask extends Task<ConfigurationDto> {

    private final AdminRestClient restClient;
    private final Long id;

    public LoadConfigurationTask(Long id) {
        this.id = id;
        this.restClient = new AdminRestClient();
    }

    @Override
    protected ConfigurationDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.getConfigurationById(id);
    }
}
