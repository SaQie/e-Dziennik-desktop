package pl.edziennik.client.task.student;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.student.StudentDto;
import pl.edziennik.client.rest.dto.student.StudentRequestDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class EditStudentTask extends Task<StudentDto> {

    private final AdminRestClient restClient;
    private final StudentRequestDto pojo;
    private final Long id;

    public EditStudentTask(StudentRequestDto pojo, Long id) {
        this.restClient = new AdminRestClient();
        this.pojo = pojo;
        this.id = id;
    }

    @Override
    protected StudentDto call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
            return restClient.editStudent(id, pojo);
        }catch (RestClientException e){
            cancel(true);
            return null;
        }
    }
}
