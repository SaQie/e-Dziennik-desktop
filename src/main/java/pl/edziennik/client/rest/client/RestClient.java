package pl.edziennik.client.rest.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.configuration.PropertiesLoader;
import pl.edziennik.client.configuration.converter.PropertiesBackendLangugageConverter;
import pl.edziennik.client.exception.RestClientException;
import pl.edziennik.client.rest.client.response.ApiAuthResponse;
import pl.edziennik.client.rest.client.response.ApiResponse;
import pl.edziennik.client.utils.AuthorizationUtils;
import pl.edziennik.client.utils.ThreadUtils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class RestClient {

    public static final String BASE_URL = PropertiesLoader.readProperty("serverAddress");

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
        configureRestClient();
    }

    public <T> T get(String url, Class<T> response) {
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<Void> entityToSend = new HttpEntity<>(null, authorizationHeader);
        try {
            ResponseEntity<ApiResponse<T>> result = restTemplate.exchange(url, HttpMethod.GET, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
            return mapper.mapToObject(result.getBody(), response);
        } catch (ResourceAccessException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY));
            throw new RestClientException("Server not responding");
        }

    }

    public <T, E> T post(String url, E request, Class<T> response) {
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<E> entityToSend = new HttpEntity<>(request, authorizationHeader);
        String token = PropertiesLoader.readProperty("token");
        try {
            ResponseEntity<ApiResponse<T>> result = restTemplate.exchange(url, HttpMethod.POST, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
            return mapper.mapToObject(result.getBody(), response);
        } catch (ResourceAccessException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY));
            throw new RestClientException("Server not responding");
        }
    }

    public <E> void post(String url, E request) {
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<E> entityToSend = new HttpEntity<>(request, authorizationHeader);
        try {
            ResponseEntity<ApiResponse<Void>> result = restTemplate.exchange(url, HttpMethod.POST, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
        } catch (ResourceAccessException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY));
            throw new RestClientException("Server not responding");
        }
    }

    public <E> void login(String url, E request) {
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<E> entityToSend = new HttpEntity<>(request, authorizationHeader);
        try {
            ResponseEntity<ApiResponse<ApiAuthResponse>> result = restTemplate.exchange(url, HttpMethod.POST, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
            HttpHeaders headers = result.getHeaders();
            AuthorizationUtils.readAuthorizationDataAndSaveLocally(headers);

        } catch (HttpServerErrorException | ResourceAccessException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY));
            throw new RestClientException("Server not responding");
        }
    }

    private HttpHeaders createAuthorizationHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", PropertiesLoader.readProperty("language", new PropertiesBackendLangugageConverter()));
        if (PropertiesLoader.isExist("token")) {
            httpHeaders.setBearerAuth(PropertiesLoader.readProperty("token"));
        }
        return httpHeaders;
    }


    private void configureRestClient() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
    }

}
