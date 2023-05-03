package pl.edziennik.client.task.school;

import javafx.concurrent.Task;

import static pl.edziennik.client.common.constants.ResourceConst.*;

import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

public class LoadSchoolsTask extends Task<Page<List<SchoolDto>>> {

    private final AdminRestClient adminRestClient;
    private int page = 0;

    public LoadSchoolsTask() {
        this.adminRestClient = new AdminRestClient();
    }

    public LoadSchoolsTask(int page) {
        this.adminRestClient = new AdminRestClient();
        this.page = page;
    }

    @Override
    protected Page<List<SchoolDto>> call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return adminRestClient.getSchoolList(page);
    }
}
