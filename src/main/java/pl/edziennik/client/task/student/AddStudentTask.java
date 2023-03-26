package pl.edziennik.client.task.student;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.student.StudentDto;
import pl.edziennik.client.rest.dto.student.StudentRequestDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;
public class AddStudentTask extends Task<StudentDto> {

    private final AdminRestClient adminRestClient;
    private final StudentRequestDto studentPojo;

    public AddStudentTask(StudentRequestDto studentPojo) {
        this.adminRestClient = new AdminRestClient();
        this.studentPojo = studentPojo;
    }

    @Override
    protected StudentDto call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
            return adminRestClient.saveNewStudent(studentPojo);
        }catch (RestClientException e){
            cancel(true);
            return null;
        }
    }
}
