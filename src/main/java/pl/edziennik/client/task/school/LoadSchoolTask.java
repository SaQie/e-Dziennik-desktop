package pl.edziennik.client.task.school;

import javafx.concurrent.Task;
import pl.edziennik.client.controller.model.admin.SchoolListModel;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;
import java.util.Collections;

import static pl.edziennik.client.common.ResourceConst.FETCHING_SCHOOL_LIST_DATA_MESSAGE_KEY;

public class LoadSchoolTask extends Task<SchoolPojo> {

    private final AdminRestClient adminRestClient;
    private final Long id;

    public LoadSchoolTask(Long id) {
        this.adminRestClient = new AdminRestClient();
        this.id = id;
    }

    @Override
    protected SchoolPojo call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(FETCHING_SCHOOL_LIST_DATA_MESSAGE_KEY.value()));
            return adminRestClient.getSchoolPojo(id);
        }catch (RestClientException e){
            updateMessage(Arrays.toString(e.getStackTrace()));
            cancel(true);
            return null;
        }
    }
}
