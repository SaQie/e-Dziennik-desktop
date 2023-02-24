package pl.edziennik.client.task.admin;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Collections;
import java.util.List;

import static pl.edziennik.client.common.ResourceConst.*;

public class LoadAdminsTask extends Task<List<AdminDto>> {

    private final AdminRestClient adminRestClient;

    public LoadAdminsTask() {
        this.adminRestClient = new AdminRestClient();
    }

    @Override
    protected List<AdminDto> call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(WAITING_FETCHING_ADMINS_LIST_MESSAGE_KEY.value()));
            return adminRestClient.getAdminList();
        } catch (RestClientException e) {
            cancel(true);
            return Collections.emptyList();
        }
    }
}
