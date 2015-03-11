package com.imcode.oauth2;

import com.imcode.exceptions.AutorizationException;
import com.imcode.misc.NormalResponse;
import com.imcode.misc.SecurityService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Created by vitaly on 27.02.15.
 */

//@Component
//@Aspect
public class TokenCheckAdvice {
//    private SecurityService securityService;
//    @Pointcut("within(@org.springframework.stereotype.Controller *)")
//    public void controllerBean() {}
////
////    @Pointcut("execution(* *(..))")
////    public void methodPointcut() {}
////
////    @Around("controllerBean() && methodPointcut() ")
////    @Around("methodPointcut()")
////@Around("@annotation(org.springframework.stereotype.Controller)")
////@Around("bean(securityService)")
////    && @annotation(org.springframework.web.bind.annotation.RequestMapping)
//    @Around("execution(* com.imcode.controllers..get*(..))")
//    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {//, WebRequest webRequest) {
//        Object retVal = null;
////        try {
//
//            Object[] args = pjp.getArgs();
//            WebRequest webRequest = null;
//            for (Object arg : args) {
//                if (arg instanceof WebRequest) {
//                    webRequest = (WebRequest) arg;
//                }
//            }
//
//            if (webRequest == null) {
//                return pjp.proceed();
//            }
//
//            String accessTokenName = webRequest.getParameter("access_token");
//
//            if (accessTokenName == null) {
//                throw new AutorizationException();
//            }else if (securityService.getTokenInfo(accessTokenName) == null) {
//                throw new AutorizationException();
//            }
//
//            retVal = pjp.proceed();
////        } catch (Throwable throwable) {
////            throwable.printStackTrace();
////        }
//
//        return new NormalResponse(retVal);
//
////        return retVal;
//
//    }
//
//
//    public SecurityService getSecurityService() {
//        return securityService;
//    }
//
//    public void setSecurityService(SecurityService securityService) {
//        this.securityService = securityService;
//    }
}
