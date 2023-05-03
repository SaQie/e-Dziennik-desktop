package pl.edziennik.client.rest.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.edziennik.client.common.factory.DialogFactory;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.client.response.ApiResponse;
import pl.edziennik.client.util.ThreadUtils;

class RestClientStatusCodesHandler {

    private final DialogFactory dialogFactory;

    protected RestClientStatusCodesHandler() {
        this.dialogFactory = DialogFactory.getInstance();
    }

    protected <T> void checkStatusCodes(ResponseEntity<ApiResponse<T>> result) {
        if (result.getStatusCode().is5xxServerError()) {
            ThreadUtils.runInFxThread(() ->
                    dialogFactory.createErrorConfirmationDialog(null, ResourceConst.SERVER_ERROR_MESSAGE_KEY.value()));
            throw new RestClientException("500 - internal server error");
        }

        if (result.getStatusCode().value() == HttpStatus.UNAUTHORIZED.value()) {
            ThreadUtils.runInFxThread(() ->
                    dialogFactory.createErrorConfirmationDialog(null, ResourceConst.UNAUTHORIZED_ERROR_MESSAGE_KEY.value()));
            throw new RestClientException("401 - unauthorized");
        }

        if (result.getStatusCode().value() == HttpStatus.FORBIDDEN.value()) {
            ThreadUtils.runInFxThread(() ->
                    dialogFactory.createErrorConfirmationDialog(null, ResourceConst.ACCESS_DENIED_ERROR_MESSAGE_KEY.value()));
            throw new RestClientException("403 - access denied");
        }

        if (result.getStatusCode().is4xxClientError())
            throw new RestClientException("400 - Bad request");
    }

}



