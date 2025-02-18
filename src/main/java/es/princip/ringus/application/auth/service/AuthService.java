package es.princip.ringus.application.auth.service;


import es.princip.ringus.application.member.service.MemberService;
import es.princip.ringus.application.serviceTerm.ServiceTermAgreementService;
import es.princip.ringus.domain.email.EmailSessionRepository;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.presentation.auth.dto.request.LoginRequest;
import es.princip.ringus.presentation.auth.dto.request.SignUpRequest;
import es.princip.ringus.presentation.auth.dto.response.LoginResponse;
import es.princip.ringus.presentation.auth.dto.response.SignUpResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;
    private final EmailVerificationService verificationService;
    private final MemberService memberService;
    private final ServiceTermAgreementService serviceTermAgreementService;
    private final EmailSessionRepository emailSessionRepository;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request, HttpSession session){
        String sessionId = session.getId();

        verificationService.verifySession(request.email(), session);

        Member member = memberService.createMember(request, serviceTermAgreementService.validateAndCreateAgreements(request.serviceTerms()));

        emailSessionRepository.deleteById(sessionId);

        return new SignUpResponse(member.getId());
    }

    @Transactional
    public LoginResponse authenticateAndAuthorize(LoginRequest request, HttpSession session) {
        Member member = authenticationService.authenticate(request);

        authorizationService.createSession(member, session);

        return new LoginResponse(member.getId());
    }
}
