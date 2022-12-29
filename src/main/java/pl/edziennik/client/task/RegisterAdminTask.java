package pl.edziennik.client.task;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.pojo.AdminPojo;

import java.util.ResourceBundle;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class RegisterAdminTask extends Task<AdminPojo> {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_RESOURCES_ADDRESS);

    private final AdminRestClient restClient;
    private final AdminPojo adminPojo;


    public RegisterAdminTask(AdminPojo adminPojo) {
        this.restClient = new AdminRestClient();
        this.adminPojo = adminPojo;
    }


    @Override
    protected AdminPojo call() throws Exception {
        try{
            updateMessage(resourceBundle.getString(WAITING_REGISTER_MESSAGE_KEY));
            restClient.register(adminPojo);
        }catch (RestClientException e){
            cancel(true);
        }
        return null;
    }
}
