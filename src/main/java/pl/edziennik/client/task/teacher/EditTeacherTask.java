package pl.edziennik.client.task.teacher;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.teacher.TeacherDto;
import pl.edziennik.client.rest.dto.teacher.TeacherRequestDto;
import pl.edziennik.client.util.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class EditTeacherTask extends Task<TeacherDto> {

    private final AdminRestClient restClient;
    private final TeacherRequestDto dto;
    private final Long id;

    public EditTeacherTask(TeacherRequestDto dto, Long id) {
        this.restClient = new AdminRestClient();
        this.dto = dto;
        this.id = id;
    }

    @Override
    protected TeacherDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.editTeacher(dto, id);
    }
}
