package pl.edziennik.client.task.parent;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.parent.ParentDto;
import pl.edziennik.client.rest.dto.parent.ParentRequestDto;
import pl.edziennik.client.util.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.WAITING_FETCH_DATA;

public class AddNewParentTask extends Task<ParentDto> {

    private final AdminRestClient restClient;
    private final ParentRequestDto parentDto;

    public AddNewParentTask(ParentRequestDto dto) {
        this.restClient = new AdminRestClient();
        this.parentDto = dto;
    }


    @Override
    protected ParentDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.saveNewParent(parentDto);
    }
}
