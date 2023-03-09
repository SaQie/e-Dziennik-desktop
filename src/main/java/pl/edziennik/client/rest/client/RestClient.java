package pl.edziennik.client.rest.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
import pl.edziennik.client.rest.dto.Page;
import pl.edziennik.client.utils.AuthorizationUtils;
import pl.edziennik.client.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pl.edziennik.client.common.ResourceConst.*;

public class RestClient {

    public static final String BASE_URL = PropertiesLoader.readProperty(PROPERTIES_LOADER_SERVER_ADDRESS_KEY.value());

    private static final Logger LOGGER = Logger.getLogger(RestClient.class.getName());

    private final RestClientStatusCodesHandler statusCodesHandler;
    private final DialogFactory dialogFactory;
    private final RestTemplate restTemplate;
    private final RestClientObjectMapper mapper;

    private static RestClient instance;

    private RestClient() {
        this.mapper = new RestClientObjectMapper();
        this.statusCodesHandler = new RestClientStatusCodesHandler();
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(new RestClientErrorLogger());
        this.dialogFactory = DialogFactory.getInstance();
        configureRestClient();
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    public <T> T send(HttpMethod method, String url, Class<T> response) {
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<Void> entityToSend = new HttpEntity<>(null, authorizationHeader);
        try {
            ResponseEntity<ApiResponse<T>> result = restTemplate.exchange(url, method, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
            LOGGER.log(Level.INFO, "Request send " + method.name() + " URL: " + url);
            return mapper.mapToObject(result.getBody(), response);
        } catch (ResourceAccessException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY.value()));
            LOGGER.severe(e.getMessage());
            throw new RestClientException(e);
        }
    }

    public <T> Page<T> sendPageable(String url, int page, Class<T> response) {
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<Void> entityToSend = new HttpEntity<>(null, authorizationHeader);
        url = url + "?page=" + page;
        try {
            ResponseEntity<ApiResponse<T>> result = restTemplate.exchange(url, HttpMethod.GET, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
            LOGGER.log(Level.INFO, "Request send " + HttpMethod.GET.name() + " URL: " + url);
            return mapper.mapToPage(result.getBody(), response);
        } catch (ResourceAccessException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY.value()));
            LOGGER.severe(e.getMessage());
            throw new RestClientException(e);
        }
    }

    public <T, E> T send(HttpMethod method, String url, E request, Class<T> response) {
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<E> entityToSend = new HttpEntity<>(request, authorizationHeader);
        try {
            ResponseEntity<ApiResponse<T>> result = restTemplate.exchange(url, method, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
            LOGGER.log(Level.INFO, "Request send " + method.name() + " URL: " + url);
            return mapper.mapToObject(result.getBody(), response);
        } catch (ResourceAccessException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY.value()));
            LOGGER.severe(e.getMessage());
            throw new RestClientException(e);
        }
    }

    public <E> void send(HttpMethod method, String url, E request) {
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<E> entityToSend = new HttpEntity<>(request, authorizationHeader);
        try {
            ResponseEntity<ApiResponse<Void>> result = restTemplate.exchange(url, method, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
            LOGGER.log(Level.INFO, "Request send " + method.name() + " URL: " + url);
        } catch (ResourceAccessException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY.value()));
            LOGGER.severe(e.getMessage());
            throw new RestClientException(e);
        }
    }

    public void send(HttpMethod method, String url, Long id) {
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<Long> entityToSend = new HttpEntity<>(null, authorizationHeader);
        try {
            ResponseEntity<ApiResponse<Void>> result = restTemplate.exchange(url + id, method, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
            LOGGER.log(Level.INFO, "Request send " + method.name() + " URL: " + url);
        } catch (ResourceAccessException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY.value()));
            LOGGER.severe(e.getMessage());
            throw new RestClientException(e);
        }
    }

    public <E> void login(String url, E request) {
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<E> entityToSend = new HttpEntity<>(request, authorizationHeader);
        try {
            ResponseEntity<ApiResponse<ApiAuthResponse>> result = restTemplate.exchange(url, HttpMethod.POST, entityToSend, new ParameterizedTypeReference<>() {
            });
            statusCodesHandler.checkStatusCodes(result);
            LOGGER.log(Level.INFO, "Request send " + HttpMethod.POST.name() + " URL: " + url);
            HttpHeaders headers = result.getHeaders();
            AuthorizationUtils.readAuthorizationDataAndSaveLocally(headers);
        } catch (HttpServerErrorException | ResourceAccessException e) {
            ThreadUtils.runInFxThread(() -> dialogFactory.createErrorConfirmationDialogFromRawStackTrace(e.getStackTrace(), SERVER_NOT_RESPONDING_MESSAGE_KEY.value()));
            LOGGER.severe(e.getMessage());
            throw new RestClientException(e);
        }
    }

    private HttpHeaders createAuthorizationHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", PropertiesLoader.readProperty("language", new PropertiesBackendLangugageConverter()));
        if (PropertiesLoader.isExist(PROPERTIES_LOADER_TOKEN_KEY.value())) {
            httpHeaders.setBearerAuth(PropertiesLoader.readProperty(PROPERTIES_LOADER_TOKEN_KEY.value()));
        }
        return httpHeaders;
    }


    private void configureRestClient() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

}
