package pl.edziennik.client.task.school;

import javafx.concurrent.Task;
import static pl.edziennik.client.common.ResourceConst.*;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoadSchoolsTask extends Task<List<SchoolPojo>> {

    private final AdminRestClient adminRestClient;

    public LoadSchoolsTask() {
        this.adminRestClient = new AdminRestClient();
    }

    @Override
    protected List<SchoolPojo> call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(FETCHING_SCHOOL_LIST_DATA_MESSAGE_KEY.value()));
            return adminRestClient.getSchoolList();
        }catch (RestClientException e){
            updateMessage(Arrays.toString(e.getStackTrace()));
            cancel(true);
            return Collections.emptyList();
        }
    }
}
