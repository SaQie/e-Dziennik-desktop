package pl.edziennik.client.task.schoolclass;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.utils.ResourceUtil;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class LoadSchoolClass extends Task<SchoolClassDto> {

    private final Long idSchoolClass;
    private final AdminRestClient restClient;

    public LoadSchoolClass(Long idSchoolClass) {
        this.idSchoolClass = idSchoolClass;
        this.restClient = new AdminRestClient();
    }

    @Override
    protected SchoolClassDto call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
            return restClient.getSchoolClass(idSchoolClass);
        } catch (RestClientException e) {
            cancel(true);

        }
        return null;
    }
}
