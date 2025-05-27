package es.princip.ringus.presentation.member;

import es.princip.ringus.application.member.service.MemberService;
import es.princip.ringus.global.annotation.SessionCheck;
import es.princip.ringus.global.annotation.SessionMemberId;
import es.princip.ringus.global.util.ApiResponseWrapper;
import es.princip.ringus.global.util.CookieUtil;
import es.princip.ringus.global.util.PasswordVaildator;
import es.princip.ringus.presentation.member.dto.MemberResponse;
import es.princip.ringus.presentation.member.dto.PasswordUpdateRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @SessionCheck
    @GetMapping("/me")
    public ResponseEntity<ApiResponseWrapper<MemberResponse>> getMember(@SessionMemberId Long memberId){

        MemberResponse response = memberService.getMember(memberId);

        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공", response));
    }

    @SessionCheck
    @GetMapping("/check-session")
    public ResponseEntity<ApiResponseWrapper<Void>> checkSession(){

        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "성공"));
    }

    @SessionCheck
    @GetMapping("/check-nickname")
    public ResponseEntity<ApiResponseWrapper<Boolean>> isUniqueNickname(@RequestParam String nickname){
        boolean response = memberService.isUniqueNickname(nickname);
        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, response));
    }

    @PatchMapping("/password")
    public ResponseEntity<ApiResponseWrapper<Void>> updatePassword(
        @RequestBody PasswordUpdateRequest request,
        HttpSession session,
        HttpServletResponse httpResponse
    ){
        PasswordVaildator.validate(request.newPassword());

        memberService.updatePassword(request, session);

        CookieUtil.deleteCookie(httpResponse, "JSESSIONID");

        session.invalidate();

        return ResponseEntity.ok(ApiResponseWrapper.success(HttpStatus.OK, "비밀번호가 변경되었습니다."));
    }

}