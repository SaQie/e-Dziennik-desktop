package pl.edziennik.client.task.school;

import javafx.concurrent.Task;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.util.ResourceUtil;

public class EditSchoolTask extends Task<SchoolDto> {

    private final AdminRestClient restClient;
    private final SchoolDto pojo;
    private final Long id;

    public EditSchoolTask(Long id, SchoolDto pojo) {
        this.restClient = new AdminRestClient();
        this.pojo = pojo;
        this.id = id;
    }

    @Override
    protected SchoolDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(ResourceConst.SAVING_NEW_SCHOOL_MESSAGE_KEY.value()));
        return restClient.editSchool(id, pojo);
    }
}
