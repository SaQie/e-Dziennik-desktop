package pl.edziennik.client.task.schoolclass;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassRequestDto;
import pl.edziennik.client.util.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class AddSchoolClassTask extends Task<SchoolClassDto> {

    private final AdminRestClient restClient;
    private final SchoolClassRequestDto dto;

    public AddSchoolClassTask(SchoolClassRequestDto dto) {
        this.restClient = new AdminRestClient();
        this.dto = dto;
    }

    @Override
    protected SchoolClassDto call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_SAVE_DATA.value()));
        return restClient.saveNewSchoolClass(dto);
    }
}
