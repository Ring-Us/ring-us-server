package es.princip.ringus.application.bookmark;

import es.princip.ringus.domain.bookmark.Bookmark;
import es.princip.ringus.domain.bookmark.BookmarkRepository;
import es.princip.ringus.domain.exception.BookmarkErrorCode;
import es.princip.ringus.domain.exception.MenteeErrorCode;
import es.princip.ringus.domain.exception.MentorErrorCode;
import es.princip.ringus.domain.mentee.Mentee;
import es.princip.ringus.domain.mentee.MenteeRepository;
import es.princip.ringus.domain.mentor.Mentor;
import es.princip.ringus.domain.mentor.MentorRepository;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.presentation.bookmark.dto.BookmarkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    @Transactional
    public Long addBookmark(BookmarkRequest request){
        Mentor mentor = findMentorById(request.mentorId());
        Mentee mentee = findMenteeById(request.menteeId());

        if(bookmarkRepository.findByMentorAndMentee(mentor, mentee).isPresent()){
            throw new CustomRuntimeException(BookmarkErrorCode.BOOKMARK_ALREADY_EXIST);
        }

        Bookmark bookmark = request.toEntity(mentor, mentee);
        mentee.addBookmark(bookmark);

        return bookmarkRepository.save(bookmark).getId();
    }

    @Transactional
    public void deleteBookmark(BookmarkRequest request){
        Mentor mentor = findMentorById(request.mentorId());
        Mentee mentee = findMenteeById(request.menteeId());

        Bookmark bookmark = bookmarkRepository.findByMentorAndMentee(mentor, mentee)
                .orElseThrow(() -> new CustomRuntimeException(BookmarkErrorCode.BOOKMARK_NOT_FOUND));

        bookmarkRepository.delete(bookmark);
        mentee.deleteBookmark(bookmark);
    }

    private Mentor findMentorById(Long mentorId) {
        return mentorRepository.findById(mentorId)
                .orElseThrow(() -> new CustomRuntimeException(MentorErrorCode.MENTOR_PROFILE_NOT_FOUND));
    }

    private Mentee findMenteeById(Long menteeId) {
        return menteeRepository.findById(menteeId)
                .orElseThrow(() -> new CustomRuntimeException(MenteeErrorCode.MENTEE_PROFILE_NOT_FOUND));
    }
}