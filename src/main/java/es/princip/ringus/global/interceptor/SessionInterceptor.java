package es.princip.ringus.global.interceptor;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        HttpSession session = request.getSession(false);
        if(session == null){
            throw new CustomRuntimeException(MemberErrorCode.SESSION_EXPIRED);
        }
        CookieUtil.addSessionCookie(response, session.getId());
        return true;
    }
}
