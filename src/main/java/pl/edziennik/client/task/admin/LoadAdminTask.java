package pl.edziennik.client.task.admin;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.util.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class LoadAdminTask extends Task<AdminDto> {

    private final AdminRestClient restClient;
    private final Long idAdmin;

    public LoadAdminTask(Long idAdmin) {
        this.idAdmin = idAdmin;
        this.restClient = new AdminRestClient();
    }

    @Override
    protected AdminDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.getAdmin(idAdmin);
    }
}
