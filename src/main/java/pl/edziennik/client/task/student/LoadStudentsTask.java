package pl.edziennik.client.task.student;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.StudentPojo;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Collections;
import java.util.List;

import static pl.edziennik.client.common.ResourceConst.*;

public class LoadStudentsTask extends Task<List<StudentPojo>> {

    private final AdminRestClient adminRestClient;

    public LoadStudentsTask() {
        this.adminRestClient = new AdminRestClient();
    }

    @Override
    protected List<StudentPojo> call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(WAITING_FETCHING_STUDENTS_LIST_MESSAGE_KEY.value()));
            return adminRestClient.getStudentList();
        } catch (RestClientException e) {
            cancel(true);
            return Collections.emptyList();
        }
    }
}
