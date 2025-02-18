package es.princip.ringus.application.auth.service;

import es.princip.ringus.domain.email.EmailSession;
import es.princip.ringus.domain.email.EmailSessionRepository;
import es.princip.ringus.domain.email.EmailVerification;
import es.princip.ringus.domain.email.EmailVerificationRepository;
import es.princip.ringus.domain.exception.EmailErrorCode;
import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.member.MemberRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationRepository verificationRepository;
    private final EmailSessionRepository sessionRepository;
    private final MemberRepository memberRepository;
    private final EmailSendService emailSendService;

    @Transactional
    public void generateVerificationCode(String email) {
        if(memberRepository.existsByEmail(email)){
            throw new CustomRuntimeException(SignUpErrorCode.DUPLICATE_EMAIL);
        }

        EmailVerification verification = EmailVerification.of(email);

        emailSendService.sendMimeMessage(email, verification.getVerificationCode());

        verificationRepository.save(verification);
    }

    /**
     * 4자리 인증 코드가 vaild 여부 검토
     * @param email
     * @param inputCode
     * @return
     * @throws CustomRuntimeException
     */
    @Transactional
    public void verifyCode(String email, String inputCode, HttpSession session){
        EmailVerification verification = verificationRepository.findById(email)
                .orElseThrow(() -> new CustomRuntimeException(EmailErrorCode.TTL_EXPIRED));

        if(!verification.hasVerificationAttemptsLeft()){
            throw new CustomRuntimeException(EmailErrorCode.ERROR_EXCEEDED_ATTEMPTS);
        }

        if(!verification.isValid(inputCode)){
            verification.plusFailedAttempts();
            verificationRepository.save(verification);
            throw new CustomRuntimeException(EmailErrorCode.ERROR_INVALID_CODE);
        }

        verificationRepository.delete(verification);

        String sessionId = session.getId();

        sessionRepository.save(EmailSession.of(email, sessionId));

    }

    /**
     * 이메일 인증 후 발급되는 세션이 아직 유요한지 검토하는 메서드(TTL : 30분)
     * @param email
     * @throws CustomRuntimeException
     */
    @Transactional
    public void verifySession(String email, HttpSession session){
        EmailSession emailSession = sessionRepository.findById(email)
                .orElseThrow(() -> new CustomRuntimeException(EmailErrorCode.TTL_EXPIRED));

        if(!Objects.equals(emailSession.getSessionId(), session.getId())){
            throw new CustomRuntimeException(EmailErrorCode.SESSION_EMAIL_MISMATCH);
        }

        sessionRepository.delete(emailSession);
    }
}