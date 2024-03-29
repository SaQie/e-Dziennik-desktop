package pl.edziennik.client.task.admin;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class LoadAdminsTask extends Task<Page<List<AdminDto>>> {

    private final AdminRestClient adminRestClient;
    private int actualPage = 0;

    public LoadAdminsTask() {
        this.adminRestClient = new AdminRestClient();
    }

    public LoadAdminsTask(int actualPage) {
        this.adminRestClient = new AdminRestClient();
        this.actualPage = actualPage;
    }

    @Override
    protected Page<List<AdminDto>> call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return adminRestClient.getAdminList(actualPage);
    }
}
