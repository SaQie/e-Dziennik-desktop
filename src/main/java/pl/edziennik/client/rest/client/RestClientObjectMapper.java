package pl.edziennik.client.rest.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.edziennik.client.common.factory.DialogFactory;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.client.response.ApiResponse;
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.util.ThreadUtils;

import java.util.Arrays;
import java.util.logging.Logger;


class RestClientObjectMapper {

    private final ObjectMapper objectMapper;
    private final DialogFactory dialogFactory;

    private static final Logger LOGGER = Logger.getLogger(RestClientObjectMapper.class.getName());

    public RestClientObjectMapper() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        this.dialogFactory = DialogFactory.getInstance();
    }

    public <T> T mapToObject(ApiResponse<T> body, Class<T> responseObject) {
        if (body != null) {
            try {
                String readedObjectAsString = objectMapper.writeValueAsString(body.getResult());
                return objectMapper.readValue(readedObjectAsString, responseObject);
            } catch (JsonProcessingException e) {
                ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialog(Arrays.toString(e.getStackTrace()),
                        ResourceConst.PARSE_ERROR.value()));
                LOGGER.severe(e.getMessage());
                throw new RestClientException("Error while parsing response");
            }
        }
        return null;
    }

    public <T, E> Page<E> mapToPage(ApiResponse<T> body, Class<E> response) {
        try {
            String readedObjectAsString = objectMapper.writeValueAsString(body.getResult());
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Page.class, response);
            return objectMapper.readValue(readedObjectAsString, javaType);
        } catch (JsonProcessingException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialog(Arrays.toString(e.getStackTrace()),
                    ResourceConst.PARSE_ERROR.value()));
            LOGGER.severe(e.getMessage());
            throw new RestClientException("Error while parsing response");
        }
    }


}
