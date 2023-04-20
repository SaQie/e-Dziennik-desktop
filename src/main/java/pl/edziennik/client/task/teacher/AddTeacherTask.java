package pl.edziennik.client.task.teacher;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.teacher.TeacherDto;
import pl.edziennik.client.rest.dto.teacher.TeacherRequestDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class AddTeacherTask extends Task<TeacherDto> {

    private final AdminRestClient restClient;
    private final TeacherRequestDto pojo;

    public AddTeacherTask(TeacherRequestDto pojo) {
        this.restClient = new AdminRestClient();
        this.pojo = pojo;
    }

    @Override
    protected TeacherDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.saveNewTeacher(pojo);
    }
}
