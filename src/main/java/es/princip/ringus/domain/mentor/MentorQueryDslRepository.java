package es.princip.ringus.domain.mentor;

import es.princip.ringus.domain.support.Cursor;
import es.princip.ringus.domain.support.CursorPageable;
import es.princip.ringus.presentation.mentor.MentorSearchFilter;
import es.princip.ringus.presentation.mentor.dto.MentorCardResponse;
import org.springframework.data.domain.Slice;

public interface MentorQueryDslRepository{
    Slice<MentorCardResponse> findMentorBy(MentorSearchFilter filter, CursorPageable<? extends Cursor> pageable);
}
