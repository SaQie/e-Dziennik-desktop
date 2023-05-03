package pl.edziennik.client.task.school;

import javafx.concurrent.Task;
import pl.edziennik.client.common.model.admin.SchoolLevelComboBoxItem;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;


public class LoadSchoolLevelsTask extends Task<List<SchoolLevelComboBoxItem>> {

    private final AdminRestClient restClient;

    public LoadSchoolLevelsTask() {
        this.restClient = new AdminRestClient();
    }

    @Override
    protected List<SchoolLevelComboBoxItem> call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.loadComboBoxItems();
    }
}
