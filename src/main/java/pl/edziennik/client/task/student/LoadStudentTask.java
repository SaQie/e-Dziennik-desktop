package pl.edziennik.client.task.student;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.student.StudentDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class LoadStudentTask extends Task<StudentDto> {

    private final Long idStudent;
    private final AdminRestClient restClient;

    public LoadStudentTask(Long idStudent) {
        this.idStudent = idStudent;
        this.restClient = new AdminRestClient();
    }

    @Override
    protected StudentDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.getStudent(idStudent);
    }
}
