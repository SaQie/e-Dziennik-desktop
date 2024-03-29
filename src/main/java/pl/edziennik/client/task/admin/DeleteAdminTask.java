package pl.edziennik.client.task.admin;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class DeleteAdminTask extends Task<Void> {

    private final AdminRestClient restClient;
    private final List<Long> idsToDelete;

    public DeleteAdminTask(List<Long> idsToDelete) {
        this.idsToDelete = idsToDelete;
        this.restClient = new AdminRestClient();
    }

    @Override
    protected Void call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_DELETE_DATA.value()));
        for (Long id : idsToDelete) {
            restClient.deleteAdmin(id);
        }
        return null;
    }

}
