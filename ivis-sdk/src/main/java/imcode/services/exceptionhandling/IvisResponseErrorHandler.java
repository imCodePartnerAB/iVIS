package imcode.services.exceptionhandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.http.OAuth2ErrorHandler;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ruslan on 25.08.16.
 */
public class IvisResponseErrorHandler extends OAuth2ErrorHandler {

    public IvisResponseErrorHandler(OAuth2ProtectedResourceDetails resource) {
        super(resource);
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) ||
                (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException, GeneralException {

        switch (response.getStatusCode()) {

            case BAD_REQUEST:
                throw buildException(response.getBody());

            case UNAUTHORIZED:
                throw buildException(response.getBody());

            case FORBIDDEN:
                throw buildException(response.getBody());

            default:
                super.handleError(response);

        }
    }

    private GeneralException buildException(InputStream responseBody) throws IOException {
        return new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
                .readValue(responseBody, GeneralException.class);
    }

 }
