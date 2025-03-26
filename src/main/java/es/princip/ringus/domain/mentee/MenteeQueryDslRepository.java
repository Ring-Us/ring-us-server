package es.princip.ringus.domain.mentee;

import es.princip.ringus.presentation.mentee.dto.MenteeCardResponse;
import es.princip.ringus.presentation.mentee.dto.MenteeCursorRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MenteeQueryDslRepository {
    Slice<MenteeCardResponse> findMenteeBy(MenteeCursorRequest request, Pageable pageable, Long memberId);
}
