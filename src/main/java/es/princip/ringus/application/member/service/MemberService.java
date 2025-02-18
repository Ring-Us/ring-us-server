package es.princip.ringus.application.member.service;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.domain.serviceTerm.ServiceTermAgreement;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.util.UniversityDomainUtil;
import es.princip.ringus.presentation.auth.dto.request.SignUpRequest;
import es.princip.ringus.presentation.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 저장 (이메일 인증 후 회원가입 진행)
     * @param request 회원가입 요청 DTO
     */
    @Transactional
    public Member createMember(SignUpRequest request, Set<ServiceTermAgreement> serviceTerm) {

        if(memberRepository.existsByEmail(request.email())){
            throw new CustomRuntimeException(SignUpErrorCode.DUPLICATE_EMAIL);
        }

        boolean isUniversityVerified = UniversityDomainUtil.isUniversityVerified(request.email());

        Member member = Member.of(request.email(), request.password(), passwordEncoder, request.memberType(), serviceTerm, isUniversityVerified);
        memberRepository.save(member);

        return member;
    }

    public MemberResponse getMemberById(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberResponse.from(member);
    }
}
