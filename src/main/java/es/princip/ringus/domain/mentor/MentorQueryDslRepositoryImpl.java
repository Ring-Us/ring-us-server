package es.princip.ringus.domain.mentor;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import es.princip.ringus.domain.bookmark.BookmarkRepository;
import es.princip.ringus.domain.mentoring.MentoringQueryDslRepository;
import es.princip.ringus.domain.support.QueryDslSupport;
import es.princip.ringus.presentation.mentor.dto.CursorRequest;
import es.princip.ringus.presentation.mentor.dto.MentorCardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.Order.ASC;
import static es.princip.ringus.domain.bookmark.QBookmark.bookmark;
import static es.princip.ringus.domain.mentee.QMentee.mentee;
import static es.princip.ringus.domain.mentor.QMentor.mentor;
import static es.princip.ringus.domain.mentoring.QMentoring.mentoring;

@Repository
@RequiredArgsConstructor
public class MentorQueryDslRepositoryImpl extends QueryDslSupport implements MentorQueryDslRepository{

    private final MentoringQueryDslRepository mentoringQueryDslRepository;
    private final BookmarkRepository bookmarkRepository;

    private List<Tuple> fetchMentor(
            final Pageable pageable,
            final CursorRequest request,
            final Long memberId
    ) {
        JPAQuery<Tuple> query;
        if (request.isSuggested()) {
            query = queryFactory.select(
                mentor.id,
                mentor.nickname,
                mentor.profileImage,
                mentor.introduction,
                mentor.organization,
                mentor.message,
                mentoring.mentoringStatus
            )
            .from(mentor);
        }
        else {
            query = queryFactory.select(
                mentor.id,
                mentor.nickname,
                mentor.profileImage,
                mentor.introduction,
                mentor.organization,
                mentor.message
            )
            .from(mentor);
        }
        queryFilter(query, request, memberId);

        query
            .where(request.cursor() != null ? mentor.id.goe(request.cursor()) : mentor.id.isNotNull())
            .orderBy(new OrderSpecifier<>(ASC, mentor.id))
            .limit(pageable.getPageSize() + 1);

        return query.fetch();
    }

    private List<MentorCardResponse> fetchContent(
            final Pageable pageable,
            final CursorRequest request,
            final Long memberId
    ) {
        return fetchMentor(pageable, request, memberId).stream()
            .map(tuple -> {
                if (request.isSuggested()) {
                    return MentorCardResponse.of(
                        tuple.get(mentor.id),
                        tuple.get(mentor.nickname),
                        tuple.get(mentor.profileImage),
                        tuple.get(mentor.introduction),
                        tuple.get(mentor.organization),
                        tuple.get(mentor.message),
                        tuple.get(mentoring.mentoringStatus.stringValue()),
                        mentoringQueryDslRepository.findMentoringCountBy(tuple.get(mentor.id))
                    );
                } else {
                    return MentorCardResponse.of(
                        tuple.get(mentor.id),
                        tuple.get(mentor.nickname),
                        tuple.get(mentor.profileImage),
                        tuple.get(mentor.introduction),
                        tuple.get(mentor.organization),
                        tuple.get(mentor.message),
                        mentoringQueryDslRepository.findMentoringCountBy(tuple.get(mentor.id)),
                        bookmarkRepository.isBookmarked(memberId, tuple.get(mentor.id))
                    );
                }
            })
            .toList();
    }

    private void queryFilter(
            JPAQuery<Tuple> query,
            CursorRequest request,
            Long memberId
    ) {
        if(request.isBookmarked() && memberId != null){
            query
                .join(mentee).on(mentee.memberId.eq(memberId))
                .join(bookmark).on(mentor.id.eq(bookmark.mentor.id))
                .where(
                        bookmark.mentee.id.eq(mentee.id)
                );
        }

        if (request.isSuggested()) {
            query.join(mentoring).on(mentoring.mentor.id.eq(mentor.id))
                .where(
                        mentoring.mentoringStatus.isNotNull()
                );
        }
    }

    @Override
    public Slice<MentorCardResponse> findMentorBy(CursorRequest request, Pageable pageable, Long memberId) {
        final List<MentorCardResponse> content = fetchContent(pageable, request, memberId);
        return paginate(pageable, content);
    }
}
