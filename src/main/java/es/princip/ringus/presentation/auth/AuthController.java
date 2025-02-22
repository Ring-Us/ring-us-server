package es.princip.ringus.presentation.auth;

import es.princip.ringus.application.auth.service.AuthService;
import es.princip.ringus.application.auth.service.EmailVerificationService;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.global.util.CookieUtil;
import es.princip.ringus.presentation.auth.dto.request.EmailVerifyRequest;
import es.princip.ringus.presentation.auth.dto.request.GenerateCodeRequest;
import es.princip.ringus.presentation.auth.dto.request.LoginRequest;
import es.princip.ringus.presentation.auth.dto.request.SignUpRequest;
import es.princip.ringus.presentation.auth.dto.response.LoginResponse;
import es.princip.ringus.presentation.auth.dto.response.SignUpResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs{
    private final AuthService authService;
    private final EmailVerificationService emailVerificationService;



    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request, HttpSession session, HttpServletResponse httpResponse) {

        SignUpResponse response = authService.signUp(request, session);

        CookieUtil.deleteCookie(httpResponse, "JSESSIONID");

        return ResponseEntity
                .created(URI.create(String.format("/api/members/%s", response.id())))
                .body(ApiResponseWrapper.success(HttpStatus.CREATED,"회원가입이 성공적으로 되었습니다"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseWrapper<Void>> login(@Valid @RequestBody LoginRequest request,
                                                          HttpSession session,
                                                          HttpServletRequest httpRequest,
                                                          HttpServletResponse httpResponse) {

        session.invalidate();

        HttpSession newSession = httpRequest.getSession(true);

        LoginResponse response = authService.authenticateAndAuthorize(request, newSession);

        CookieUtil.addSessionCookie(httpResponse, newSession.getId());

        return ResponseEntity
                .created(URI.create(String.format("/api/members/%s", response.id())))
                .body(ApiResponseWrapper.success(HttpStatus.OK,"로그인이 성공적으로 되었습니다"));
    }

    @PostMapping("logout")
    public ResponseEntity<ApiResponseWrapper<Void>> logout(HttpSession session, HttpServletResponse httpResponse) {
        CookieUtil.deleteCookie(httpResponse, "JSESSIONID");

        session.invalidate();
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "로그아웃이 성공적으로 되었습니다"));
    }

    @PostMapping("/email/code")
    public ResponseEntity<ApiResponseWrapper<Void>> requestCode(@Valid @RequestBody GenerateCodeRequest request) {
        emailVerificationService.generateVerificationCode(request.email());

        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "인증번호가 발급되었습니다"));
    }

    @PostMapping("/email/code/verify")
    public ResponseEntity<ApiResponseWrapper<Void>> verifyCode(@Valid @RequestBody EmailVerifyRequest request, HttpSession session, HttpServletResponse httpResponse){
        emailVerificationService.verifyCode(request.email(), request.code(), session);

        CookieUtil.addSessionCookie(httpResponse, session.getId());

        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "인증되었습니다"));
    }
}
