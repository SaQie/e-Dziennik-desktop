package pl.edziennik.client.rest.client;

import org.springframework.http.ResponseEntity;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.ApiResponse;
import pl.edziennik.client.utils.ThreadUtils;

import static pl.edziennik.client.common.ResourcesConstants.*;

class RestClientStatusCodesHandler {

    private final DialogFactory dialogFactory;

    protected RestClientStatusCodesHandler() {
        this.dialogFactory = DialogFactory.getInstance();
    }

    protected <T> void checkStatusCodes(ResponseEntity<ApiResponse<T>> result) {
        if (result.getStatusCode().is5xxServerError()) {
            ThreadUtils.runInFxThread(() ->
                    dialogFactory.createErrorConfirmationDialog(null, SERVER_ERROR_MESSAGE_KEY));
            throw new RestClientException("500 - internal server error");
        }

        if (result.getStatusCode().is4xxClientError()) {
            ApiResponse<T> responseBody = result.getBody();
            ThreadUtils.runInFxThread(() ->
                    dialogFactory.createErrorConfirmationDialog(responseBody.getStackTrace(), responseBody.getErrors()));
            throw new RestClientException("400 - Bad request");
        }

    }


}
