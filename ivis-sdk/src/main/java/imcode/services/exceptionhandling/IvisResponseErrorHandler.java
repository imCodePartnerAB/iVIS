package imcode.services.exceptionhandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.http.OAuth2ErrorHandler;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

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

        if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

            GeneralException generalException = objectMapper.readValue(response.getBody(), GeneralException.class);

            throw generalException;
        } else {
            super.handleError(response);
        }
    }
}
