package es.princip.ringus.presentation.serviceTerm;

import es.princip.ringus.application.serviceTerm.ServiceTermService;
import es.princip.ringus.presentation.serviceTerm.dto.ServiceTermRequest;
import es.princip.ringus.presentation.serviceTerm.dto.ServiceTermResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-terms")
@RequiredArgsConstructor
public class ServiceTermController {
    private final ServiceTermService serviceTermService;

    @PostMapping
    // TODO: 관리자만 접근 가능하도록 권한 제어
    public ServiceTermResponse registerServiceTerm(@RequestBody @Valid ServiceTermRequest request) {
        return ServiceTermResponse.from(serviceTermService.register(request));
    }
}
