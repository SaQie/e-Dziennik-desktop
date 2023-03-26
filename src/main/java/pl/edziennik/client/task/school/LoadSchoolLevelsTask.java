package pl.edziennik.client.task.school;

import javafx.concurrent.Task;
import pl.edziennik.client.controller.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;


public class LoadSchoolLevelsTask extends Task<List<SchoolLevelComboBoxItem>> {

    private final AdminRestClient restClient;

    public LoadSchoolLevelsTask() {
        this.restClient = new AdminRestClient();
    }

    @Override
    protected List<SchoolLevelComboBoxItem> call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
            return restClient.loadComboBoxItems();
        } catch (RestClientException e) {
            updateMessage(Arrays.toString(e.getStackTrace()));
            cancel(true);
            return Collections.emptyList();
        }
    }
}
