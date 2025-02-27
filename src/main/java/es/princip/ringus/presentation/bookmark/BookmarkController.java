package es.princip.ringus.presentation.bookmark;

import es.princip.ringus.application.bookmark.BookmarkService;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.bookmark.dto.BookmarkRequest;
import es.princip.ringus.presentation.bookmark.dto.BookmarkResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponseWrapper<BookmarkResponse>> addBookmark(@Valid @RequestBody BookmarkRequest request){
        BookmarkResponse response = BookmarkResponse.from(bookmarkService.addBookmark(request));

        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공적으로 추가됨", response));
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponseWrapper<Void>> deleteBookmark(@Valid @RequestBody BookmarkRequest request){
        bookmarkService.deleteBookmark(request);

        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공적으로 삭제됨"));
    }
}