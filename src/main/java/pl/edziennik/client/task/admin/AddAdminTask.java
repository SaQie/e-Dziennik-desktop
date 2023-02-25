package pl.edziennik.client.task.admin;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.ResourceConst.*;

public class AddAdminTask extends Task<AdminDto> {

    private final AdminRestClient restClient;
    private final AdminDto dto;

    public AddAdminTask(AdminDto dto) {
        this.dto = dto;
        this.restClient = new AdminRestClient();
    }

    @Override
    protected AdminDto call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_ADD_ADMIN_MESSAGE_KEY.value()));
            return restClient.saveNewAdmin(dto);
        }catch (RestClientException e){
            cancel(true);
            return null;
        }
    }
}
