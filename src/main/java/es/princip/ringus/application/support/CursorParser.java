package es.princip.ringus.application.support;

import es.princip.ringus.presentation.mentor.dto.MentorCardResponse;
import org.springframework.data.domain.Slice;

public class CursorParser {
    public static Long parse(final Long cursor, final Slice<MentorCardResponse> slice) {
        if (slice.isLast()) {
            return null;
        }
        if (slice.hasNext()) {
            return  slice.getContent()
                .get(slice.getSize() -1)
                .mentorId();
        }
        return null;
    }
}
