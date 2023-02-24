package pl.edziennik.client.task.schoolclass;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Collections;
import java.util.List;

import static pl.edziennik.client.common.ResourceConst.*;

public class LoadSchoolClassesTask extends Task<List<SchoolClassDto>> {

    private final AdminRestClient adminRestClient;
    private final Long idSchool;

    public LoadSchoolClassesTask(Long idSchool) {
        this.adminRestClient = new AdminRestClient();
        this.idSchool = idSchool;
    }

    @Override
    protected List<SchoolClassDto> call() throws Exception {
        try{
            updateMessage(ResourceUtil.getMessage(WAITING_FETCHING_SCHOOL_CLASSES_LIST_MESSAGE_KEY.value()));
            return adminRestClient.getSchoolClassesBySchoolId(idSchool);
        }catch (RestClientException e){
            cancel(true);
            return Collections.emptyList();
        }
    }
}
