package pl.edziennik.client.task.parent;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.parent.ParentDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.ResourceConst.WAITING_FETCH_DATA;

public class EditParentTask extends Task<ParentDto> {

    private final AdminRestClient restClient;
    private final Long id;
    private final ParentDto dto;

    public EditParentTask(Long id, ParentDto dto) {
        this.restClient = new AdminRestClient();
        this.id = id;
        this.dto = dto;
    }


    @Override
    protected ParentDto call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
            return restClient.editParent(id,dto);
        } catch (RestClientException e) {
            cancel(true);
            return null;
        }
    }
}
