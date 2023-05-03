package pl.edziennik.client.task.parent;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.WAITING_DELETE_DATA;

public class DeleteParentTask extends Task<Void> {


    private final AdminRestClient restClient;
    private List<Long> idsToDelete;

    public DeleteParentTask(List<Long> idsToDelete) {
        this.restClient = new AdminRestClient();
        this.idsToDelete = idsToDelete;
    }

    @Override
    protected Void call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_DELETE_DATA.value()));
        for (Long id : idsToDelete) {
            restClient.deleteParent(id);
        }
        return null;
    }
}
