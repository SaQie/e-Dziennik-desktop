package pl.edziennik.client.task.admin;

import javafx.concurrent.Task;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.ResourceConst.*;

public class LoadAdminTask extends Task<AdminDto> {

    private final AdminRestClient restClient;
    private final Long idAdmin;

    public LoadAdminTask(Long idAdmin) {
        this.idAdmin = idAdmin;
        this.restClient = new AdminRestClient();
    }

    @Override
    protected AdminDto call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(WAITING_FETCHING_ADMINS_LIST_MESSAGE_KEY.value()));
            return restClient.getAdmin(idAdmin);
        } catch (RestClientException e) {
            cancel(true);
            return null;
        }
    }
}
