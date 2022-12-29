package pl.edziennik.client.rest.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.ApiResponse;
import pl.edziennik.client.utils.ThreadUtils;

import static pl.edziennik.client.common.ResourcesConstants.*;

import java.util.List;

public class RestClient {

    private final RestClientStatusCodesHandler statusCodesHandler;
    private final DialogFactory dialogFactory;
    private RestTemplate restTemplate;
    private RestClientObjectMapper mapper;

    public RestClient() {
        this.mapper = new RestClientObjectMapper();
        this.statusCodesHandler = new RestClientStatusCodesHandler();
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(new RestClientErrorLogger());
        this.dialogFactory = DialogFactory.getInstance();
    }

    public <T> T get(String url, Class<T> response){
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<Void> entityToSend = new HttpEntity<>(null ,authorizationHeader);
        try {
            ResponseEntity<ApiResponse<T>> result = restTemplate.exchange(url, HttpMethod.GET, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
            return mapper.mapToObject(result.getBody(), response);
        }catch (ResourceAccessException e){
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY));
            throw new RestClientException("Server not responding");
        }

    }

    public <T, E> T post(String url, E request, Class<T> response){
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<E> entityToSend = new HttpEntity<>(request ,authorizationHeader);
        try{
            ResponseEntity<ApiResponse<T>> result = restTemplate.exchange(url, HttpMethod.POST, entityToSend, new ParameterizedTypeReference<>(){});
            statusCodesHandler.checkStatusCodes(result);
            return mapper.mapToObject(result.getBody(), response);
        }catch (ResourceAccessException e){
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY));
            throw new RestClientException("Server not responding");
        }
    }

    public <E> void post(String url, E request){
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<E> entityToSend = new HttpEntity<>(request ,authorizationHeader);
        try{
            ResponseEntity<ApiResponse<Void>> result = restTemplate.exchange(url, HttpMethod.POST, entityToSend, new ParameterizedTypeReference<>(){});
            statusCodesHandler.checkStatusCodes(result);
        }catch (ResourceAccessException e){
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY));
            throw new RestClientException("Server not responding");
        }
    }

    private HttpHeaders createAuthorizationHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setBearerAuth(PropertiesLoader.readProperty("token"));
        return httpHeaders;
    }

}
