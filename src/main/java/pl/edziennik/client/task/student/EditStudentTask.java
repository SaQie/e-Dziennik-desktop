package pl.edziennik.client.task.student;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.StudentPojo;
import pl.edziennik.client.rest.pojo.StudentRequestPojo;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.ResourceConst.*;

public class EditStudentTask extends Task<StudentPojo> {

    private final AdminRestClient restClient;
    private final StudentRequestPojo pojo;
    private final Long id;

    public EditStudentTask(StudentRequestPojo pojo, Long id) {
        this.restClient = new AdminRestClient();
        this.pojo = pojo;
        this.id = id;
    }

    @Override
    protected StudentPojo call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_ADD_NEW_STUDENT_MESSAGE_KEY.value()));
            return restClient.editStudent(id, pojo);
        }catch (RestClientException e){
            cancel(true);
            return null;
        }
    }
}
