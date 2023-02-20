package pl.edziennik.client.task.student;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.StudentPojo;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.ResourceConst.*;

public class LoadStudentTask extends Task<StudentPojo> {

    private final Long idStudent;
    private final AdminRestClient restClient;

    public LoadStudentTask(Long idStudent) {
        this.idStudent = idStudent;
        this.restClient = new AdminRestClient();
    }

    @Override
    protected StudentPojo call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_FETCHING_STUDENT_MESSAGE_KEY.value()));
            Thread.sleep(2000);
            return restClient.getStudent(idStudent);
        }catch (RestClientException e){
            cancel(true);
            return null;
        }
    }
}
