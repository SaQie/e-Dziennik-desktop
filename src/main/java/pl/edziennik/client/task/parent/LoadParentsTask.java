package pl.edziennik.client.task.parent;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.parent.ParentDto;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class LoadParentsTask extends Task<Page<List<ParentDto>>> {

    private final AdminRestClient restClient;
    private int actualPage = 0;

    public LoadParentsTask() {
        this.restClient = new AdminRestClient();
    }

    public LoadParentsTask(int actualPage) {
        this.restClient = new AdminRestClient();
        this.actualPage = actualPage;
    }

    @Override
    protected Page<List<ParentDto>> call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.getParentsList(actualPage);
    }
}
