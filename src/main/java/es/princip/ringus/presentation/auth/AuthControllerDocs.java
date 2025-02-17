package es.princip.ringus.presentation.auth;

import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.auth.dto.request.EmailVerifyRequest;
import es.princip.ringus.presentation.auth.dto.request.GenerateCodeRequest;
import es.princip.ringus.presentation.auth.dto.request.LoginRequest;
import es.princip.ringus.presentation.auth.dto.request.SignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Auth API", description = "회원 인증 관련 API")
@RequestMapping("/auth")
public interface AuthControllerDocs {

    @Operation(summary = "회원가입", description = "회원가입을 처리합니다.")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "409", description = "이미 가입된 이메일"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/signup")
    ResponseEntity<?> signUp(@Valid @RequestBody @Parameter SignUpRequest request);

    @Operation(summary = "로그인", description = "로그인을 처리합니다.")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @PostMapping("/login")
    ResponseEntity<ApiResponseWrapper<Void>> login(@Valid @RequestBody @Parameter LoginRequest request, HttpSession session);

    @Operation(summary = "이메일 인증 코드 요청", description = "이메일 인증번호를 발급합니다.")
    @ApiResponse(responseCode = "200", description = "인증번호 발급 성공")
    @PostMapping("/email/code")
    ResponseEntity<ApiResponseWrapper<Void>> requestCode(@Valid @RequestBody @Parameter GenerateCodeRequest request);

    @Operation(summary = "이메일 인증 코드 검증", description = "인증번호를 검증합니다.")
    @ApiResponse(responseCode = "200", description = "인증 성공")
    @PostMapping("/email/code/verify")
    ResponseEntity<ApiResponseWrapper<Void>> verifyCode(@Valid @RequestBody @Parameter EmailVerifyRequest request, HttpSession session, HttpServletResponse httpResponse);
}