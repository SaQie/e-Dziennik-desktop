package pl.edziennik.client.task;

import javafx.concurrent.Task;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.ResourcesConstants.*;

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
            updateMessage(ResourceUtil.getMessage(SAVING_NEW_SCHOOL_MESSAGE_KEY));
            return restClient.saveNewSchool(pojo);
        }catch (RestClientException e){
            cancel(true);
            return null;
        }
    }
}
