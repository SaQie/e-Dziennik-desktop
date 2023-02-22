package pl.edziennik.client.task.teacher;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.TeacherPojo;
import pl.edziennik.client.rest.pojo.TeacherRequestPojo;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.ResourceConst.*;

public class AddTeacherTask extends Task<TeacherPojo> {

    private final AdminRestClient restClient;
    private final TeacherRequestPojo pojo;

    public AddTeacherTask(TeacherRequestPojo pojo) {
        this.restClient = new AdminRestClient();
        this.pojo = pojo;
    }

    @Override
    protected TeacherPojo call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_ADD_NEW_TEACHER_MESSAGE_KEY.value()));
            return restClient.saveNewTeacher(pojo);
        }catch (RestClientException e){
            cancel(true);
            return null;
        }
    }
}
