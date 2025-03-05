package es.princip.ringus.application.mentoring;

import es.princip.ringus.domain.mentoring.MentoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MentoringService {
    private final MentoringRepository mentoringRepository;


}
