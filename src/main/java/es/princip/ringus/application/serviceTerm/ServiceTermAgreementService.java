package es.princip.ringus.application.serviceTerm;

import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.serviceTerm.ServiceTerm;
import es.princip.ringus.domain.serviceTerm.ServiceTermAgreement;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.serviceTerm.dto.ServiceTermAgreementRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceTermAgreementService {
    private final ServiceTermService serviceTermService;

    public Set<ServiceTermAgreement> validateAndCreateAgreements(Set<ServiceTermAgreementRequest> agreementRequests) {
        return agreementRequests.stream()
                .map(request -> {
                    ServiceTerm serviceTerm = serviceTermService.getByTag(request.tag());

                    if(serviceTerm.isRequired() && !request.agreed()){
                        throw new CustomRuntimeException(SignUpErrorCode.NOT_AGREED_REQUIRED_SERVICE_TERM);
                    }

                    return new ServiceTermAgreement(serviceTerm.getTag(), request.agreed());
                }).collect(Collectors.toSet());
    }
}
