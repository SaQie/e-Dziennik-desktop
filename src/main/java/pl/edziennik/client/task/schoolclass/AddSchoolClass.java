package pl.edziennik.client.task.schoolclass;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassRequestDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class AddSchoolClass extends Task<SchoolClassDto> {

    private final AdminRestClient restClient;
    private final SchoolClassRequestDto dto;

    public AddSchoolClass(SchoolClassRequestDto dto) {
        this.restClient = new AdminRestClient();
        this.dto = dto;
    }

    @Override
    protected SchoolClassDto call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_SAVE_DATA.value()));
            return restClient.saveNewSchoolClass(dto);
        }catch (RestClientException e){
            cancel(true);
        }
        return null;
    }
}
