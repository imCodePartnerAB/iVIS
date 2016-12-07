package imcode.services.argumentresolver;

import com.imcode.services.GenericService;
import imcode.services.IvisServiceFactory;
import imcode.services.utils.IvisOAuth2Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ruslan on 06.12.16.
 */
public class IvisServiceArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(IvisServiceArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return GenericService.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> type = parameter.getParameterType();
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        IvisServiceFactory ivisServiceFactory = IvisOAuth2Utils.getServiceFactory(request);
        logger.debug("Resolved service argument: " + type.getTypeName());
        return ivisServiceFactory.getService((Class<? extends GenericService>) type);
    }
}
