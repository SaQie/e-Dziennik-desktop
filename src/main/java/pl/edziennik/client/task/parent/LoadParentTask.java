package pl.edziennik.client.task.parent;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.parent.ParentDto;
import pl.edziennik.client.util.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class LoadParentTask extends Task<ParentDto> {

    private final AdminRestClient restClient;
    private final Long id;

    public LoadParentTask(Long id) {
        this.restClient = new AdminRestClient();
        this.id = id;
    }

    @Override
    protected ParentDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.getParent(id);
    }
}
