package es.princip.ringus.presentation;

import es.princip.ringus.application.auth.dto.request.CertificationNumberRequest;
import es.princip.ringus.application.auth.dto.request.VerifyRequest;
import es.princip.ringus.application.auth.dto.response.CertificationNumberResponse;
import es.princip.ringus.application.auth.dto.response.VerifyResponse;
import es.princip.ringus.application.auth.service.AuthService;
import es.princip.ringus.application.auth.dto.request.LoginRequest;
import es.princip.ringus.application.auth.dto.response.LoginResponse;
import es.princip.ringus.application.auth.dto.request.SignUpRequest;
import es.princip.ringus.application.auth.dto.response.SignUpResponse;
import es.princip.ringus.application.auth.service.EmailVerificationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        LoginResponse response = authService.authenticateAndAuthorize(request, session);

        return ResponseEntity.ok(response);
    }
}
