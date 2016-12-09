package imcode.services.exceptionhandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.http.OAuth2ErrorHandler;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.util.StreamUtils;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by ruslan on 25.08.16.
 */
public class IvisResponseErrorHandler extends OAuth2ErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(IvisResponseErrorHandler.class);

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

        InputStream body = response.getBody();
        byte[] error = StreamUtils.copyToByteArray(body);
        logger.error(new String(error, StandardCharsets.UTF_8));

        switch (response.getStatusCode()) {

            case BAD_REQUEST:
                throw buildException(error);

            case UNAUTHORIZED:
                throw buildException(error);

            case FORBIDDEN:
                throw buildException(error);

            default:
                super.handleError(response);
                break;

        }
    }

    private GeneralException buildException(byte [] error) throws IOException {
        return new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
                .readValue(error, GeneralException.class);
    }

 }
