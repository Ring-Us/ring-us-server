package es.princip.ringus.application.auth.service;

import es.princip.ringus.presentation.auth.dto.request.LoginRequest;
import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member authenticate(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomRuntimeException(SignUpErrorCode.NOT_FOUND_MEMBER));

        if(!passwordEncoder.matches(request.password(), member.getPassword())){
            throw new CustomRuntimeException(SignUpErrorCode.WRONG_PASSWORD);
        }

        return member;
    }
}
