package pl.edziennik.client.task.school;

import javafx.concurrent.Task;
import pl.edziennik.client.rest.AdminRestClient;
import pl.edziennik.client.util.ResourceUtil;

import java.util.List;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class DeleteSchoolTask extends Task<Void> {

    private final AdminRestClient restClient;
    private final List<Long> idsSchool;

    public DeleteSchoolTask(List<Long> idsSchool) {
        this.restClient = new AdminRestClient();
        this.idsSchool = idsSchool;
    }

    @Override
    protected Void call() throws Exception {
        updateMessage(ResourceUtil.getMessage(WAITING_DELETE_SCHOOL_MESSAGE_KEY.value()));
        for (Long idSchool : idsSchool) {
            // zastanowic sie czy to jest ok, czy nie lepiej wyslac 1 zapytanie z kolekcja
            restClient.deleteSchool(idSchool);
        }
        return null;
    }
}
