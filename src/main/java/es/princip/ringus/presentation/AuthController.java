package es.princip.ringus.presentation;

import es.princip.ringus.application.auth.dto.request.GenerateCodeRequest;
import es.princip.ringus.application.auth.dto.request.LoginRequest;
import es.princip.ringus.application.auth.dto.request.SignUpRequest;
import es.princip.ringus.application.auth.dto.request.VerifyRequest;
import es.princip.ringus.application.auth.dto.response.LoginResponse;
import es.princip.ringus.application.auth.dto.response.SignUpResponse;
import es.princip.ringus.application.auth.service.AuthService;
import es.princip.ringus.application.auth.service.EmailVerificationService;
import es.princip.ringus.global.util.ApiResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest request) {
        SignUpResponse response = authService.signUp(request);
        return ResponseEntity.created(URI.create(String.format("/api/members/%s", response.id()))).build();
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        LoginResponse response = authService.authenticateAndAuthorize(request, session);

        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "성공적으로 로그인되었습니다"));
    }

    @PostMapping("/email/code")
    public ResponseEntity<ApiResponse<Void>> requestCode(@Valid @RequestBody GenerateCodeRequest request) {
        emailVerificationService.generateVerificationCode(request.email());

        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "인증번호가 발급되었습니다"));
    }

    @PostMapping("/email/code/verify")
    public ResponseEntity<ApiResponse<Void>> verifyCode(@Valid @RequestBody VerifyRequest request){
        emailVerificationService.verifyCode(request.email(), request.code());

        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK, "인증되었습니다"));
    }
}
