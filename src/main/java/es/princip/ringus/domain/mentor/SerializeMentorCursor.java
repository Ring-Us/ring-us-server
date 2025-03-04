package es.princip.ringus.domain.mentor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.princip.ringus.domain.exception.SerialErrorCode;
import es.princip.ringus.domain.support.Cursor;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.mentor.dto.MentorCardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class SerializeMentorCursor {

    private final ObjectMapper objectMapper;

    public String serializeCursor(final Slice<MentorCardResponse> slice) {
        if (!slice.hasNext()) {
            return null;
        }
        final Long id = slice.getContent()
                .stream()
                .reduce((first, second) -> second)
                .map(MentorCardResponse::mentorId)
                .orElse(null);
        if (id == null) {
            return null;
        }
        final Cursor cursor = new Cursor(id);
        try {
            final String cursorJson = objectMapper.writeValueAsString(cursor);
            return Base64.getEncoder().encodeToString(cursorJson.getBytes());
        } catch (final JsonProcessingException exception) {
            log.debug("Failed to serialize cursor", exception);
            throw new CustomRuntimeException(SerialErrorCode.CURSOR_NOT_SERIALIZABLE);
        }
    }
}