package pl.edziennik.client.task;

import javafx.concurrent.Task;
import pl.edziennik.client.common.ResourceConst;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;

import java.util.Collections;
import java.util.List;


public class LoadSchoolLevelsTask extends Task<List<SchoolLevelComboBoxItem>> {

    private final AdminRestClient restClient;

    public LoadSchoolLevelsTask() {
        this.restClient = new AdminRestClient();
    }

    @Override
    protected List<SchoolLevelComboBoxItem> call() throws Exception {
        try{
            updateMessage(ResourceConst.FETCHING_SCHOOL_LEVEL_LIST_MESSAGE_KEY.value());
            return restClient.loadComboBoxItems();
        }catch (RestClientException e){
            cancel(true);
            return Collections.emptyList();
        }
    }
}
