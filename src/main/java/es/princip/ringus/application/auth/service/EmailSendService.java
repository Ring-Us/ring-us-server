package es.princip.ringus.application.auth.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSendService {
    private final JavaMailSender mailSender;

    @Async
    public void sendMimeMessage(String email, String code) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            // 메일을 받을 수신자 설정
            mimeMessageHelper.setTo(email);
            // 메일의 제목 설정
            mimeMessageHelper.setSubject("[RINGUS] 이메일 인증번호 안내");

            // html 문법 적용한 메일의 내용 (code 값을 동적으로 삽입)
            String content = String.format("""
                    <!DOCTYPE html>
                    <html xmlns:th="http://www.thymeleaf.org">

                    <body>
                    <div style="margin:100px;">
                        <h1> 이메일 인증 코드 </h1>
                        <br>

                        <div align="center" style="border:1px solid black;">
                            <h3> 인증 코드: <span style="color:blue;">%s</span> </h3>
                        </div>
                        <br/>
                        <p>이메일 인증 코드를 정확히 입력해주세요.</p>
                    </div>

                    </body>
                    </html>
                    """, code);

            // 메일의 내용 설정
            mimeMessageHelper.setText(content, true);
            mailSender.send(mimeMessage);

            log.info("메일 발송 성공!");
        } catch (Exception e) {
            log.info("메일 발송 실패!");
            throw new RuntimeException(e);
        }
    }
}