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

    // TODO: 로그인된 유저가 멘토인지 확인
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

    // TODO: 로그인된 유저가 멘티인지 확인
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

    // TODO: 로그인 여부 확인
    public boolean isLoginUser(
        HttpSession session,
        Long memberId
    ){
        return session != null && session.getAttribute("memberId") != null;
    }

    // TODO: 역할이 멘토인지 확인
    public boolean isMentorRole(String role) {
        return "MENTOR".equals(role);
    }

    // TODO: 역할이 멘티인지 확인
    public boolean isMenteeRole(String role) {
        return "MENTEE".equals(role);
    }
}