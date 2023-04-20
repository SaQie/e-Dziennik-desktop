package pl.edziennik.client.task.schoolclass;

import javafx.concurrent.Task;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.schoolclass.SchoolClassDto;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.WAITING_FETCH_DATA;

public class LoadSchoolClassesTask extends Task<Page<List<SchoolClassDto>>> {

    private final AdminRestClient adminRestClient;
    private final Long idSchool;
    private int page = 0;


    public LoadSchoolClassesTask(Long idSchool, int page) {
        this.adminRestClient = new AdminRestClient();
        this.idSchool = idSchool;
        this.page = page;
    }

    public LoadSchoolClassesTask(int page) {
        this.adminRestClient = new AdminRestClient();
        this.idSchool = null;
        this.page = page;
    }

    public LoadSchoolClassesTask() {
        this.adminRestClient = new AdminRestClient();
        this.idSchool = null;
    }


    @Override
    protected Page<List<SchoolClassDto>> call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        if (idSchool == null) {
            return adminRestClient.getSchoolClassesList(page);
        }
        return adminRestClient.getSchoolClassesBySchoolId(idSchool);
    }
}
