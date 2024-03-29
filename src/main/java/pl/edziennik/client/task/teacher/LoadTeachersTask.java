package pl.edziennik.client.task.teacher;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.teacher.TeacherDto;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.WAITING_FETCH_DATA;

public class LoadTeachersTask extends Task<Page<List<TeacherDto>>> {

    private final AdminRestClient adminRestClient;
    private int actualPage = 0;

    public LoadTeachersTask() {
        this.adminRestClient = new AdminRestClient();
    }

    public LoadTeachersTask(int actualPage) {
        this.adminRestClient = new AdminRestClient();
        this.actualPage = actualPage;
    }

    @Override
    protected Page<List<TeacherDto>> call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return adminRestClient.getTeacherList(actualPage);
    }
}
