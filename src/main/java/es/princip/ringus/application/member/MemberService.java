package es.princip.ringus.application.member;

import es.princip.ringus.application.auth.dto.SignUpRequest;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 저장 (이메일 인증 후 회원가입 진행)
     * @param request 회원가입 요청 DTO
     * @param session HttpSession 객체
     */
    @Transactional
    public Member createMember(SignUpRequest request, HttpSession session) {

        Member member = Member.of(request.email(), request.password(), passwordEncoder);
        memberRepository.save(member);

        return member;
    }


}
