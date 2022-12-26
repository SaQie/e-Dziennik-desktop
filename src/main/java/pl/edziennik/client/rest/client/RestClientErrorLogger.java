package pl.edziennik.client.rest.client;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class RestClientErrorLogger implements ResponseErrorHandler {

    private static final Logger LOGGER = Logger.getLogger( RestClientErrorLogger.class.getName() );

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return new DefaultResponseErrorHandler().hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()){
            LOGGER.log(Level.SEVERE, "Something went wrong...");
        }
        if (response.getStatusCode().is5xxServerError()){
            LOGGER.log(Level.SEVERE, "Server not responding !");
        }
    }
}
