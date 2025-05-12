package es.princip.ringus.global.util;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.domain.member.Member;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionUtil {

    private final MemberRepository memberRepository;

    public boolean isLoginMentorUser(
        HttpSession session,
        Long memberId
    ){
        if (!isLoginUser(session, memberId)) {
            return false;
        }

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new CustomRuntimeException(MemberErrorCode.MEMBER_NOT_FOUND));

        return member.isMentor();
    }

    public boolean isLoginMenteeUser(
        HttpSession session,
        Long memberId
    ){
        if (!isLoginUser(session, memberId)) {
            return false;
        }

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new CustomRuntimeException(MemberErrorCode.MEMBER_NOT_FOUND));

        return member.isMentee();
    }

    public boolean isLoginUser(
        HttpSession session,
        Long memberId
    ){
        return session != null && session.getAttribute("memberId") != null;
    }

    public boolean isMentorRole(String role) {
        return "MENTOR".equals(role);
    }

    public boolean isMenteeRole(String role) {
        return "MENTEE".equals(role);
    }
}