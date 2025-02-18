package es.princip.ringus.presentation.member;

import es.princip.ringus.application.member.service.MemberService;
import es.princip.ringus.domain.exception.MemberErrorCode;
import es.princip.ringus.global.exception.CustomRuntimeException;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.presentation.member.dto.MemberResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponseWrapper<MemberResponse>> getMember(HttpSession session){

        Long memberId = (Long)session.getAttribute("memberId");
        if(memberId == null){
            throw new CustomRuntimeException(MemberErrorCode.SESSION_EXPIRED);
        }

        MemberResponse response = memberService.getMemberById(memberId);

        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }
}