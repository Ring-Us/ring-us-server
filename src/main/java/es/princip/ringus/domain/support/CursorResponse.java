package es.princip.ringus.domain.support;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
public class CursorResponse<T> {

    private final List<T> content;
    private final SliceInfo sliceInfo;

    private CursorResponse(final List<T> content, final SliceInfo sliceInfo) {
        this.content = content;
        this.sliceInfo = sliceInfo;
    }

    public static <T> CursorResponse<T> of(final Slice<T> slice, final String cursor) {
        SliceInfo pageInfo = SliceInfo.builder()
                .size(slice.getSize())
                .numberOfElements(slice.getNumberOfElements())
                .first(slice.isFirst())
                .last(slice.isLast())
                .empty(slice.isEmpty())
                .sort(slice.getSort())
                .cursor(cursor)
                .build();
        return new CursorResponse<>(slice.getContent(), pageInfo);
    }

    record SliceInfo(
            int size,
            int numberOfElements,
            boolean first,
            boolean last,
            boolean empty,
            String cursor,
            Sort sort
    ) {
        @Builder
        public SliceInfo {
        }
    }
}
