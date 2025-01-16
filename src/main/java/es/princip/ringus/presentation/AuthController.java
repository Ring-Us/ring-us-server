package es.princip.ringus.presentation;

import es.princip.ringus.application.auth.AuthService;
import es.princip.ringus.application.auth.dto.LoginRequest;
import es.princip.ringus.application.auth.dto.LoginResponse;
import es.princip.ringus.application.auth.dto.SignUpRequest;
import es.princip.ringus.application.auth.dto.SignUpResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request, HttpSession session) {
        SignUpResponse response = authService.signUp(request, session);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        LoginResponse response = authService.authenticateAndAuthorize(request, session);

        return ResponseEntity.ok(response);
    }

}
