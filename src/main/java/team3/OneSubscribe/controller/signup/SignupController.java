package team3.OneSubscribe.controller.signup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.service.SignupService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signup")
public class SignupController {

    //자동 autowired
    private final SignupService signupService;

    //회원을 등록해준다. 등록에 성공하면 id를 반환. 실패하면 메시지를 반환.
    //어떤 검증에서 에러가 발생했는지 알기 위해 if문을 나눠서 작성.
    //에러를 보면 어디서 뭔가 잘못되었는지 한눈에 알 수 있어야 한다. errorCode를 같이 출력함.
    @PostMapping("/signupMember")
    @ResponseBody
    public String signupMember(@RequestParam Member member) {
        int errorCode=0;
        if ((errorCode = signupService.idFormCheck(member.getLoginId())) != 0) {
            return "id 형식이 맞지 않습니다. errorCode : " + errorCode;
        }
        if ((errorCode = signupService.pwFormCheck(member.getLoginPassword())) != 0) {
            return "password 형식이 맞지 않습니다. errorCode : " + errorCode;
        }
        if (!signupService.idDuplicateCheck(member.getLoginId())) { //ajax로 중복처리 했더라도 검사하는게 맞을듯.
            return "이미 id가 존재합니다.";
        }
        signupService.saveMember(member); // id, pw 형식, 중복검사를 통과했을 때 저장.
        return member.getLoginId();
    }

    //id 중복검사 api
    //ajax로 프론트에서 요청 후 js로 프론트에서 처리하는게 좋을 듯.
    //에러페이지를 보여줄까도 했지만 ajax가 더 현대적일듯.
    @PostMapping("idDuplicateCheck")
    @ResponseBody
    public boolean idDuplicateCheck(@RequestParam String loginId) {
        return signupService.idDuplicateCheck(loginId);
    }
}
