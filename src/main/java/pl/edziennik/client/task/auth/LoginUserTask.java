package pl.edziennik.client.task.auth;

import javafx.concurrent.Task;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.rest.AuthorizationRestClient;
import pl.edziennik.client.rest.dto.auth.LoginCredentialsDto;
import pl.edziennik.client.util.ResourceUtil;

public class LoginUserTask extends Task<Void> {

    private final AuthorizationRestClient restClient;
    private final LoginCredentialsDto dto;

    public LoginUserTask(LoginCredentialsDto dto) {
        this.restClient = new AuthorizationRestClient();
        this.dto = dto;
    }

    @Override
    protected Void call() throws Exception {
        updateMessage(ResourceUtil.getMessage(ResourceConst.WAITING_FETCH_DATA.value()));
        restClient.login(dto);
        return null;
    }
}
