package pl.edziennik.client.task.admin;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class EditAdminTask extends Task<AdminDto> {

    private final AdminRestClient restClient;
    private final Long idAdmin;
    private final AdminDto dto;

    public EditAdminTask(Long idAdmin, AdminDto dto) {
        this.idAdmin = idAdmin;
        this.dto = dto;
        this.restClient = new AdminRestClient();
    }

    @Override
    protected AdminDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return restClient.editAdmin(idAdmin, dto);
    }

}
