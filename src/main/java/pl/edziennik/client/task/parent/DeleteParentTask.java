package pl.edziennik.client.task.parent;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.ResourceConst.WAITING_DELETE_DATA;
import static pl.edziennik.client.common.ResourceConst.WAITING_FETCH_DATA;

public class DeleteParentTask extends Task<Void> {


    private final AdminRestClient restClient;
    private List<Long> idsToDelete;

    public DeleteParentTask(List<Long> idsToDelete) {
        this.restClient = new AdminRestClient();
        this.idsToDelete = idsToDelete;
    }

    @Override
    protected Void call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(WAITING_DELETE_DATA.value()));
            for (Long id : idsToDelete) {
                restClient.deleteParent(id);
            }
        } catch (RestClientException e) {
            cancel(true);
        }
        return null;
    }
}