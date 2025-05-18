package es.princip.ringus.infra.storage.api;

import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.infra.storage.application.StoragePortfolioService;
import es.princip.ringus.infra.storage.dto.PortfolioUploadRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController implements PortfolioControllerDocs{

    private final StoragePortfolioService storagePortfolioService;

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<Void>> uploadPortfolio(
            @ModelAttribute PortfolioUploadRequest request,
            HttpSession session) {

        Long memberId = (Long) session.getAttribute("memberId");
        if(memberId == null){
            throw new CustomRuntimeException(MemberErrorCode.SESSION_EXPIRED);
        }
        String filePath = storagePortfolioService.uploadMentorPortfolio(request, memberId);

        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, filePath));
    }
}
