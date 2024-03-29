package pl.edziennik.client.task.school;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.util.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class LoadSchoolTask extends Task<SchoolDto> {

    private final AdminRestClient adminRestClient;
    private final Long id;

    public LoadSchoolTask(Long id) {
        this.adminRestClient = new AdminRestClient();
        this.id = id;
    }

    @Override
    protected SchoolDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return adminRestClient.getSchoolPojo(id);
    }
}
