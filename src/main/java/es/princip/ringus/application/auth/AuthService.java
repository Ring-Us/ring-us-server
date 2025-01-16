package es.princip.ringus.application.auth;

import es.princip.ringus.application.auth.dto.LoginRequest;
import es.princip.ringus.application.auth.dto.LoginResponse;
import es.princip.ringus.application.auth.dto.SignUpRequest;
import es.princip.ringus.application.auth.dto.SignUpResponse;
import es.princip.ringus.application.member.MemberService;
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
    public SignUpResponse signUp(SignUpRequest request, HttpSession session) {
        Member member = memberService.createMember(request, session);

        Map<String, Object> data = new HashMap<>();
        data.put("memberId", member.getId());

        return new SignUpResponse(201, "성공적으로 회원가입이 완료되었습니다", data);
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
