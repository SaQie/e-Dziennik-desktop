package pl.edziennik.client.rest.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.edziennik.client.common.ConfirmationDialogFactory;
import pl.edziennik.client.rest.ApiResponse;

import java.util.Arrays;

import static pl.edziennik.client.common.ResourcesConstants.PARSE_ERROR;

class RestClientObjectMapper {

    private ObjectMapper objectMapper;
    private ConfirmationDialogFactory dialogFactory;

    public RestClientObjectMapper() {
        this.objectMapper = new ObjectMapper();
        this.dialogFactory = ConfirmationDialogFactory.getInstance();
    }

    public <T> T mapToObject(ApiResponse<T> body, Class<T> responseObject) {
        if (body != null) {
            try {
                String readedObjectAsString = objectMapper.writeValueAsString(body.getResult());
                return objectMapper.readValue(readedObjectAsString, responseObject);
            } catch (JsonProcessingException e) {
                dialogFactory.createErrorConfirmationDialog(Arrays.toString(e.getStackTrace()), PARSE_ERROR);
            }
        }
        return null;
    }
}
