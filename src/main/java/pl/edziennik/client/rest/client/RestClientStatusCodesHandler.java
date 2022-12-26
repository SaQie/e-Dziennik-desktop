package pl.edziennik.client.rest.client;

import javafx.application.Platform;
import org.springframework.http.ResponseEntity;
import pl.edziennik.client.common.ConfirmationDialogFactory;
import pl.edziennik.client.rest.ApiResponse;

import static pl.edziennik.client.common.ResourcesConstants.*;

class RestClientStatusCodesHandler {

    private final ConfirmationDialogFactory dialogFactory;

    protected RestClientStatusCodesHandler() {
        this.dialogFactory = ConfirmationDialogFactory.getInstance();
    }

    protected <T> void checkStatusCodes(ResponseEntity<ApiResponse<T>> result) {
        if (result.getStatusCode().is5xxServerError()) {
            Platform.runLater(() -> dialogFactory.createErrorConfirmationDialog(null, SERVER_NOT_RESPONDING_MESSAGE_KEY));
        }

        if (result.getStatusCode().is4xxClientError()) {
            ApiResponse<T> responseBody = result.getBody();
            Platform.runLater(() -> dialogFactory.createErrorConfirmationDialog(responseBody.getStackTrace(), responseBody.getErrors()));
        }

    }
}
