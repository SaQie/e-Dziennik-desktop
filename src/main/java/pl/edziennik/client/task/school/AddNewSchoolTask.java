package pl.edziennik.client.task.school;

import javafx.concurrent.Task;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.util.ResourceUtil;

public class AddNewSchoolTask extends Task<SchoolDto> {

    private final AdminRestClient restClient;
    private SchoolDto pojo;

    public AddNewSchoolTask(SchoolDto pojo) {
        this.restClient = new AdminRestClient();
        this.pojo = pojo;
    }

    @Override
    protected SchoolDto call() {
        updateMessage(ResourceUtil.getMessage(ResourceConst.SAVING_NEW_SCHOOL_MESSAGE_KEY.value()));
        return restClient.saveNewSchool(pojo);
    }
}
