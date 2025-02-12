package es.princip.ringus.presentation.auth.dto.request;

import es.princip.ringus.presentation.serviceTerm.dto.ServiceTermAgreementRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record SignUpRequest(
        @NotBlank
        String memberType,

        @NotBlank(message = "이메일을 입력해주세요.")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세욘.")
        @Size(min = 8, max = 20, message = "비밀번호는 8~20자여야 합니다.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*_+=/])[A-Za-z\\d!@#$%^&*_+=/]{8,20}$",
                message = "비밀번호는 8~20자의 대소문자, 숫자, 특수문자로 구성해야 합니다."
        )
        String password,

        @NotNull Set<ServiceTermAgreementRequest> serviceTerms
) { }
