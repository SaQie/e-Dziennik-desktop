package pl.edziennik.client.task.teacher;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.teacher.TeacherDto;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Collections;
import java.util.List;

import static pl.edziennik.client.common.ResourceConst.*;

public class LoadTeachersTask extends Task<List<TeacherDto>> {

    private final AdminRestClient adminRestClient;

    public LoadTeachersTask() {
        this.adminRestClient = new AdminRestClient();
    }

    @Override
    protected List<TeacherDto> call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(WAITING_FETCHING_TEACHERS_LIST_MESSAGE_KEY.value()));
            return adminRestClient.getTeacherList();
        } catch (RestClientException e) {
            cancel(true);
            return Collections.emptyList();
        }
    }
}
