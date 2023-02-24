package pl.edziennik.client.task.config;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.config.ConfigurationDto;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Collections;
import java.util.List;

import static pl.edziennik.client.common.ResourceConst.*;

public class LoadConfigurationsTask extends Task<List<ConfigurationDto>> {

    private final AdminRestClient restClient;

    public LoadConfigurationsTask() {
        this.restClient = new AdminRestClient();
    }

    @Override
    protected List<ConfigurationDto> call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(WAITING_FETCHING_CONFIGURATION_LIST_MESSAGE_KEY.value()));
            return restClient.getConfigurationList();
        } catch (RestClientException e) {
            cancel(true);
            return Collections.EMPTY_LIST;
        }
    }
}
