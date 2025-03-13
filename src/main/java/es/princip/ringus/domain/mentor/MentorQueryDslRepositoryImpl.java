package es.princip.ringus.domain.mentor;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import es.princip.ringus.domain.support.QueryDslSupport;
import es.princip.ringus.presentation.mentor.dto.CursorRequest;
import es.princip.ringus.presentation.mentor.dto.MentorCardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.Order.ASC;
import static es.princip.ringus.domain.mentor.QMentor.mentor;

@Repository
@RequiredArgsConstructor
public class MentorQueryDslRepositoryImpl extends QueryDslSupport implements MentorQueryDslRepository{

    private List<Tuple> fetchMentor(
            final Pageable pageable,
            final Long cursor
    ) {
        return queryFactory.select(
                    mentor.id,
                    mentor.nickname,
                    mentor.profileImage,
                    mentor.introduction,
                    mentor.organization,
                    mentor.message
                )
                .from(mentor)
                .where(cursor != null ? mentor.id.goe( cursor) : mentor.id.isNotNull())
                .orderBy(new OrderSpecifier<>(ASC, mentor.id))
                .limit(pageable.getPageSize() + 1)
                .fetch();
    }

    private List<MentorCardResponse> fetchContent(
            final Pageable pageable,
            final  Long cursor
    ) {
        return fetchMentor(pageable, cursor).stream()
                .map(tuple -> MentorCardResponse.of(
                    tuple.get(mentor.id),
                    tuple.get(mentor.nickname),
                    tuple.get(mentor.profileImage),
                    tuple.get(mentor.introduction),
                    tuple.get(mentor.organization),
                    tuple.get(mentor.message),
                    0
                ))
                .toList();
    }

    @Override
    public Slice<MentorCardResponse> findMentorBy(CursorRequest request, Pageable pageable) {
        final List<MentorCardResponse> content = fetchContent(pageable, request.cursor());
        return paginate(pageable, content);
    }
}
