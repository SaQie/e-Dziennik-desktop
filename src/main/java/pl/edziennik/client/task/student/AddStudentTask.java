package pl.edziennik.client.task.student;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.StudentPojo;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.ResourceConst.WAITING_ADD_NEW_STUDENT_MESSAGE_KEY;

public class AddStudentTask extends Task<StudentPojo> {

    private final AdminRestClient adminRestClient;
    private final StudentPojo studentPojo;

    public AddStudentTask(StudentPojo studentPojo) {
        this.adminRestClient = new AdminRestClient();
        this.studentPojo = studentPojo;
    }

    @Override
    protected StudentPojo call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_ADD_NEW_STUDENT_MESSAGE_KEY.value()));
            return adminRestClient.saveNewStudent(studentPojo);
        }catch (RestClientException e){
            cancel(true);
            return null;
        }
    }
}
