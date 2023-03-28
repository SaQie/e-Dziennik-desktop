package pl.edziennik.client.task.schoolclass;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassRequestDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class EditSchoolClassTask extends Task<SchoolClassDto> {

    private final Long idSchoolClass;
    private final SchoolClassRequestDto dto;
    private final AdminRestClient restClient;

    public EditSchoolClassTask(Long idSchoolClass, SchoolClassRequestDto dto) {
        this.idSchoolClass = idSchoolClass;
        this.restClient = new AdminRestClient();
        this.dto = dto;
    }

    @Override
    protected SchoolClassDto call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_SAVE_DATA.value()));
            restClient.editSchoolClass(idSchoolClass, dto);
        }catch (RestClientException e){
            cancel(true);
        }
        return null;
    }
}
