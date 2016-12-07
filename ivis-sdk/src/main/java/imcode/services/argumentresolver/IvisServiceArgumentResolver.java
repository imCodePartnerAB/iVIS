package com.imcode.configuration;

import com.imcode.services.GenericService;
import imcode.services.IvisServiceFactory;
import imcode.services.utils.IvisOAuth2Utils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ruslan on 06.12.16.
 */
public class IvisServiceArgumentResolver implements WebArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
        Class<?> type = methodParameter.getParameterType();
        Object nativeRequest = webRequest.getNativeRequest();
        if (!GenericService.class.isAssignableFrom(type) && !(nativeRequest instanceof HttpServletRequest)) {
            return UNRESOLVED;
        }

        HttpServletRequest request = (HttpServletRequest) nativeRequest;
        IvisServiceFactory ivisServiceFactory = IvisOAuth2Utils.getServiceFactory(request);

        return ivisServiceFactory.getService((Class<? extends GenericService>) type);

    }

}
