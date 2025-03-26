package es.princip.ringus.domain.mentee;

import static com.querydsl.core.types.Order.ASC;
import static es.princip.ringus.domain.mentee.QMentee.mentee;
import static es.princip.ringus.domain.mentor.QMentor.mentor;
import static es.princip.ringus.domain.mentoring.QMentoring.mentoring;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import es.princip.ringus.domain.support.QueryDslSupport;
import es.princip.ringus.presentation.mentee.dto.MenteeCardResponse;
import es.princip.ringus.presentation.mentee.dto.MenteeCursorRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MenteeQueryDslRepositoryImpl extends QueryDslSupport implements MenteeQueryDslRepository {

    private List<Tuple> fetchMentee(
        final Pageable pageable,
        final MenteeCursorRequest request,
        final Long memberId
    ) {
        JPAQuery<Tuple> query;
        if (request.isSuggested()) {
            query = queryFactory.select(
                    mentee.id,
                    mentee.nickname,
                    mentee.profileImage,
                    mentoring.mentoringStatus
                )
                .from(mentee);
        }
        else {
            query = queryFactory.select(
                    mentee.id,
                    mentee.nickname,
                    mentee.profileImage
                )
                .from(mentee);
        }
        queryFilter(query, request, memberId);

        query
            .where(request.cursor() != null ? mentee.id.goe(request.cursor()) : mentee.id.isNotNull())
            .orderBy(new OrderSpecifier<>(ASC, mentee.id))
            .limit(pageable.getPageSize() + 1);

        return query.fetch();
    }

    private List<MenteeCardResponse> fetchContent(
        final Pageable pageable,
        final MenteeCursorRequest request,
        final Long memberId
    ) {

        return fetchMentee(pageable, request, memberId).stream()
            .map(tuple -> {
                if (request.isSuggested()) {

                    return MenteeCardResponse.of(
                        tuple.get(mentee.id),
                        tuple.get(mentee.nickname),
                        tuple.get(mentee.profileImage),
                        tuple.get(mentoring.mentoringStatus.stringValue())

                    );
                } else {
                    return MenteeCardResponse.of(
                        tuple.get(mentee.id),
                        tuple.get(mentee.nickname),
                        tuple.get(mentee.profileImage)
                    );
                }
            })
            .toList();
    }

    private void queryFilter(
        JPAQuery<Tuple> query,
        MenteeCursorRequest request,
        Long memberId
    ) {
        if(request.isSuggested() && memberId != null){
            query
                .join(mentor).on(mentor.memberId.eq(memberId))
                .join(mentoring).on(mentoring.mentor.id.eq(mentor.id))
                .where(mentoring.mentee.id.eq(mentee.id));
        }
    }


    @Override
    public Slice<MenteeCardResponse> findMenteeBy(MenteeCursorRequest request, Pageable pageable, Long memberId){
        final List<MenteeCardResponse> content = fetchContent(pageable, request, memberId);
        return paginate(pageable, content);
    }
}
