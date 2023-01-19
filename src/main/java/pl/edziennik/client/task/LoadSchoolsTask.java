package pl.edziennik.client.task;

import javafx.concurrent.Task;
import static pl.edziennik.client.common.ResourceConst.*;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.SchoolPojo;
import pl.edziennik.client.utils.ResourceUtil;

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
            updateProgress(50, 100);
            updateMessage(ResourceUtil.getMessage(FETCHING_SCHOOL_LIST_DATA_MESSAGE_KEY.value()));
            List<SchoolPojo> schoolList = adminRestClient.getSchoolList();
            updateProgress(100, 100);
            return schoolList;
        }catch (RestClientException e){
            cancel(true);
            return Collections.emptyList();
        }
    }
}
