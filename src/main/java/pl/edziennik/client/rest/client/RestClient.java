package pl.edziennik.client.rest.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import pl.edziennik.client.rest.ApiResponse;

import java.util.List;

public class RestClient {

    private final RestClientStatusCodesHandler statusCodesHandler;
    private RestTemplate restTemplate;
    private RestClientObjectMapper mapper;

    public RestClient() {
        this.mapper = new RestClientObjectMapper();
        this.statusCodesHandler = new RestClientStatusCodesHandler();
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(new RestClientErrorLogger());
    }

    public <T> T get(String url, Class<T> clazz){
        HttpHeaders authorizationHeader = createAuthorizationHeader();
        HttpEntity<Void> entityToSend = new HttpEntity<>(null ,authorizationHeader);
        ResponseEntity<ApiResponse<T>> result = restTemplate.exchange(url, HttpMethod.GET, entityToSend, new ParameterizedTypeReference<>(){});
        statusCodesHandler.checkStatusCodes(result);
        return mapper.mapToObject(result.getBody(), clazz);
    }

    private HttpHeaders createAuthorizationHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setBearerAuth(PropertiesLoader.readProperty("token"));
        return httpHeaders;
    }

}
