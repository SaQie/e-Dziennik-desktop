package pl.edziennik.client.task.school;

import javafx.concurrent.Task;
import static pl.edziennik.client.common.ResourceConst.*;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.school.SchoolDto;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoadSchoolsTask extends Task<Page<List<SchoolDto>>> {

    private final AdminRestClient adminRestClient;
    private int page = 0;

    public LoadSchoolsTask() {
        this.adminRestClient = new AdminRestClient();
    }

    public LoadSchoolsTask(int page){
        this.adminRestClient = new AdminRestClient();
        this.page = page;
    }

    @Override
    protected Page<List<SchoolDto>> call() throws Exception {
        try {
            updateMessage(ResourceUtil.getMessage(FETCHING_SCHOOL_LIST_DATA_MESSAGE_KEY.value()));
            return adminRestClient.getSchoolList(page);
        }catch (RestClientException e){
            updateMessage(Arrays.toString(e.getStackTrace()));
            cancel(true);
            return Page.empty();
        }
    }
}
