package es.princip.ringus.application.auth.service;

import es.princip.ringus.domain.member.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService {
    public void createSession(Member member, HttpSession session) {
        session.setAttribute("memberId", member.getId());
    }
}
