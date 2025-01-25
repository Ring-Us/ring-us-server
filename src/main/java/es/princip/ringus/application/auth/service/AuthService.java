package es.princip.ringus.application.auth.service;

import es.princip.ringus.presentation.auth.dto.request.LoginRequest;
import es.princip.ringus.presentation.auth.dto.response.LoginResponse;
import es.princip.ringus.presentation.auth.dto.request.SignUpRequest;
import es.princip.ringus.presentation.auth.dto.response.SignUpResponse;
import es.princip.ringus.application.member.service.MemberService;
import es.princip.ringus.domain.member.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;
    private final EmailVerificationService verificationService;
    private final MemberService memberService;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request){
        verificationService.verifySession(request.email());

        Member member = memberService.createMember(request);

        return new SignUpResponse(member.getId());
    }

    @Transactional
    public LoginResponse authenticateAndAuthorize(LoginRequest request, HttpSession session) {
        Member member = authenticationService.authenticate(request);

        authorizationService.createSession(member, session);

        return new LoginResponse(member.getId());
    }
}
