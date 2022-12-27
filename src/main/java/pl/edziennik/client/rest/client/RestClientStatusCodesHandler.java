package pl.edziennik.client.rest.client;

import javafx.application.Platform;
import org.springframework.http.ResponseEntity;
import pl.edziennik.client.common.ConfirmationDialogFactory;
import pl.edziennik.client.rest.ApiResponse;
import pl.edziennik.client.utils.ThreadUtils;

import static pl.edziennik.client.common.ResourcesConstants.*;

class RestClientStatusCodesHandler {

    private final ConfirmationDialogFactory dialogFactory;

    protected RestClientStatusCodesHandler() {
        this.dialogFactory = ConfirmationDialogFactory.getInstance();
    }

    protected <T> void checkStatusCodes(ResponseEntity<ApiResponse<T>> result) {
        if (result.getStatusCode().is5xxServerError()) {
            ThreadUtils.runInFxThread(() ->
                    dialogFactory.createErrorConfirmationDialog(null, SERVER_ERROR_MESSAGE_KEY));
        }

        if (result.getStatusCode().is4xxClientError()) {
            ApiResponse<T> responseBody = result.getBody();
            ThreadUtils.runInFxThread(() ->
                    dialogFactory.createErrorConfirmationDialog(responseBody.getStackTrace(), responseBody.getErrors()));
        }

    }


}
