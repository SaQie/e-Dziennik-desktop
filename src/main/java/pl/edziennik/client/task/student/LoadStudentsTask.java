package pl.edziennik.client.task.student;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.rest.dto.student.StudentDto;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class LoadStudentsTask extends Task<Page<List<StudentDto>>> {

    private final AdminRestClient adminRestClient;
    private int actualPage;

    public LoadStudentsTask() {
        this.adminRestClient = new AdminRestClient();
        this.actualPage = 0;
    }

    public LoadStudentsTask(int actualPage) {
        this.adminRestClient = new AdminRestClient();
        this.actualPage = actualPage;
    }

    @Override
    protected Page<List<StudentDto>> call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_FETCH_DATA.value()));
        return adminRestClient.getStudentList(actualPage);
    }
}
