package es.princip.ringus.application.auth.service;

import es.princip.ringus.application.auth.dto.request.LoginRequest;
import es.princip.ringus.application.auth.dto.response.LoginResponse;
import es.princip.ringus.application.auth.dto.request.SignUpRequest;
import es.princip.ringus.application.auth.dto.response.SignUpResponse;
import es.princip.ringus.application.member.service.MemberService;
import es.princip.ringus.domain.member.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;
    private final MemberService memberService;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request){

        Member member = memberService.createMember(request);

        return new SignUpResponse(member.getId());
    }

    @Transactional
    public LoginResponse authenticateAndAuthorize(LoginRequest request, HttpSession session) {
        Member member = authenticationService.authenticate(request, session);

        authorizationService.createSession(member, session);

        Map<String, Object> data = new HashMap<>();
        data.put("memberId", member.getId());

        return new LoginResponse(201, "로그인 성공", data);
    }
}
