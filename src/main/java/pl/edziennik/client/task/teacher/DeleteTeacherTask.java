package pl.edziennik.client.task.teacher;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class DeleteTeacherTask extends Task<Void> {

    private final List<Long> idsToDelete;
    private final AdminRestClient restClient;

    public DeleteTeacherTask(List<Long> idsToDelete) {
        this.idsToDelete = idsToDelete;
        this.restClient = new AdminRestClient();
    }


    @Override
    protected Void call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_DELETE_DATA.value()));
        for (Long id : idsToDelete) {
            restClient.deleteTeacher(id);
        }
        return null;
    }
}
