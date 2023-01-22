package pl.edziennik.client.task.school;

import javafx.concurrent.Task;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;

public class AddNewSchoolTask extends Task<SchoolPojo> {

    private final AdminRestClient restClient;
    private SchoolPojo pojo;

    public AddNewSchoolTask(SchoolPojo pojo) {
        this.restClient = new AdminRestClient();
        this.pojo = pojo;
    }

    @Override
    protected SchoolPojo call() {
        try{
            updateMessage(ResourceUtil.getMessage(ResourceConst.SAVING_NEW_SCHOOL_MESSAGE_KEY.value()));
            return restClient.saveNewSchool(pojo);
        }catch (RestClientException e){
            updateMessage(Arrays.toString(e.getStackTrace()));
            cancel(true);
            return null;
        }
    }
}
