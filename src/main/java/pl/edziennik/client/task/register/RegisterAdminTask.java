package pl.edziennik.client.task.register;

import javafx.concurrent.Task;
import static pl.edziennik.client.common.ResourceConst.*;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.admin.AdminDto;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;

public class RegisterAdminTask extends Task<AdminDto> {

    private final AdminRestClient restClient;
    private AdminDto adminDto;


    public RegisterAdminTask(AdminDto adminDto) {
        this.restClient = new AdminRestClient();
        this.adminDto = adminDto;
    }


    @Override
    protected AdminDto call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_REGISTER_MESSAGE_KEY.value()));
            adminDto = restClient.register(adminDto);
        }catch (RestClientException e){
            updateMessage(Arrays.toString(e.getStackTrace()));
            cancel(true);
            return null;
        }
        return adminDto;
    }
}
