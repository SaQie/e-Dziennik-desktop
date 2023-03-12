package pl.edziennik.client.task.student;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.ResourceConst.*;

public class DeleteStudentTask extends Task<Void> {

    private final AdminRestClient restClient;
    private final List<Long> idToDelete;

    public DeleteStudentTask(List<Long> idToDelete) {
        this.restClient = new AdminRestClient();
        this.idToDelete = idToDelete;
    }

    @Override
    protected Void call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(WAITING_DELETE_DATA.value()));
            for (Long id : idToDelete) {
                restClient.deleteStudent(id);
            }
            return null;
        } catch (RestClientException e) {
            cancel(true);
            return null;
        }
    }
}
