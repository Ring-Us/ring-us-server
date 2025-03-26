package es.princip.ringus.global.aop;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionToMemberId{

    public static Long getSessionMemberId (HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("memberId") == null){
            throw new CustomRuntimeException(MemberErrorCode.SESSION_EXPIRED);
        }

        System.out.println("[Session Interceptor] : Interceptor 실행, 세션 유효, 멤버 ID: " + session.getAttribute("memberId"));
        CookieUtil.addSessionCookie(response, session.getId());
        request.setAttribute("memberId", session.getAttribute("memberId"));
        return (Long) request.getAttribute("memberId");
    }
}