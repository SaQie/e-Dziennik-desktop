package pl.edziennik.client.task;

import javafx.concurrent.Task;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.AdminPojo;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class RegisterAdminTask extends Task<AdminPojo> {

    private final AdminRestClient restClient;
    private AdminPojo adminPojo;


    public RegisterAdminTask(AdminPojo adminPojo) {
        this.restClient = new AdminRestClient();
        this.adminPojo = adminPojo;
    }


    @Override
    protected AdminPojo call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_REGISTER_MESSAGE_KEY));
            adminPojo = restClient.register(adminPojo);
        }catch (RestClientException e){
            cancel(true);
            return null;
        }
        return adminPojo;
    }
}
