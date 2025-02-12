package es.princip.ringus.application.serviceTerm;

import es.princip.ringus.domain.exception.SignUpErrorCode;
import es.princip.ringus.domain.serviceTerm.ServiceTerm;
import es.princip.ringus.domain.serviceTerm.ServiceTermRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.serviceTerm.dto.ServiceTermRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ServiceTermService{

    private final ServiceTermRepository serviceTermRepository;

    @Transactional
    public ServiceTerm register(final ServiceTermRequest request) {
        serviceTermRepository.existsById(request.tag());
        final ServiceTerm serviceTerm = ServiceTerm.of(request.tag(), request.required());
        serviceTermRepository.save(serviceTerm);
        return serviceTerm;
    }

    public ServiceTerm getByTag(String tag){
        return serviceTermRepository.findById(tag)
                .orElseThrow(() -> new CustomRuntimeException(SignUpErrorCode.SERVICE_TERM_NOT_FOUND));
    }
}