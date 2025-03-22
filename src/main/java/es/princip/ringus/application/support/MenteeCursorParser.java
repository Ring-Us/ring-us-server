package es.princip.ringus.application.support;

import es.princip.ringus.presentation.mentee.dto.MenteeCardResponse;
import org.springframework.data.domain.Slice;

public class MenteeCursorParser {
    public static Long parse(final Long cursor, final Slice<MenteeCardResponse> slice) {
        if (slice.isLast()) {
            return null;
        }
        if (slice.hasNext()) {
            return  slice.getContent()
                .get(slice.getSize() -1)
                .menteeId();
        }
        return null;
    }
}
