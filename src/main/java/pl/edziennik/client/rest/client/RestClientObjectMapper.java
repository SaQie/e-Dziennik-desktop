package pl.edziennik.client.rest.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.client.response.ApiResponse;
import pl.edziennik.client.utils.ThreadUtils;

import java.util.Arrays;

import static pl.edziennik.client.common.ResourcesConstants.PARSE_ERROR;

class RestClientObjectMapper {

    private ObjectMapper objectMapper;
    private DialogFactory dialogFactory;

    public RestClientObjectMapper() {
        this.objectMapper = new ObjectMapper();
        this.dialogFactory = DialogFactory.getInstance();
    }

    public <T> T mapToObject(ApiResponse<T> body, Class<T> responseObject) {
        if (body != null) {
            try {
                String readedObjectAsString = objectMapper.writeValueAsString(body.getResult());
                return objectMapper.readValue(readedObjectAsString, responseObject);
            } catch (JsonProcessingException e) {
                ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialog(Arrays.toString(e.getStackTrace()), PARSE_ERROR));
                throw new RestClientException("Error while parsing response");
            }
        }
        return null;
    }
}
