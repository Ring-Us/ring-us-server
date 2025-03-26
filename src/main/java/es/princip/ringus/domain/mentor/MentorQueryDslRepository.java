package es.princip.ringus.domain.mentor;

import es.princip.ringus.presentation.mentor.dto.CursorRequest;
import es.princip.ringus.presentation.mentor.dto.MentorCardResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MentorQueryDslRepository{
    Slice<MentorCardResponse> findMentorBy(CursorRequest request, Pageable pageable, Long memberId);
}
