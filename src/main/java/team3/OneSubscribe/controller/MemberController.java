package team3.OneSubscribe.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.service.FormCheck;
import team3.OneSubscribe.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/login")
    public String login(@ModelAttribute("m") Member m, HttpServletRequest request, Model model) {
        model.addAttribute("login", "fail");
        HttpSession session;
        if (memberService.login(m)) {
            session = request.getSession();//세션이 없다면 세션생성.
            session.setAttribute("member", m);
            model.addAttribute("isLogined", "true");
            return "index";//로그인 성공
        }
        return "loginFail";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false); //세션이 없어도 생성안함.
//        model.addAttribute("logout", "fail");
        if (session != null) {
            session.invalidate();
            model.addAttribute("logout", "success");//덮어쓰기
        }
        return "index";
    }

    @PostMapping("/findId")
    public String findId(String name, String email, String phoneNum, Model model) {
//        String resId = loginService.findLoginId(name, email, phoneNum);
//        if (resId == null) {
//            model.addAttribute("findId", "해당하는 아이디를 찾지 못했습니다. 다시 입력해주세요");
//            //찾지 못했다. 다시 입력하도록 보낸다.
//            return "find_login_id";
//
//        } else {//아이디를 찾았다.
//            //이메일 보내기.
//            mailService.sendMail(email, "[KidsCare]아이디 찾기", "아이디는 : " +resId + "입니다."); //메일을 보내는데 시간이 좀 걸려서 비동기로 하면 좋을 것 같다.
//            //메일을 확인하라는 뷰 띄우기.
//            model.addAttribute("findId", email);
//            return "plz_check_email";
//        }
//
//    }
        return "find_login_id";
    }



    //회원을 등록해준다. 등록에 성공하면 id를 반환. 실패하면 메시지를 반환.
    //어떤 검증에서 에러가 발생했는지 알기 위해 if문을 나눠서 작성.
    //에러를 보면 어디서 뭔가 잘못되었는지 한눈에 알 수 있어야 한다. errorCode를 같이 출력함.
    @PostMapping("/signup")
    public String signupMember(@RequestParam Member member) {

        int errorCode = 0;
        if ((errorCode = FormCheck.idFormCheck(member.getLoginId())) != 0) {
            return "id 형식이 맞지 않습니다. errorCode : " + errorCode;
        }
        if ((errorCode = FormCheck.pwFormCheck(member.getLoginPassword())) != 0) {
            return "password 형식이 맞지 않습니다. errorCode : " + errorCode;
        }
        if (memberService.isIdDuplicated(member.getLoginId())) { //ajax로 중복처리 했더라도 검사하는게 맞을듯.
            return "이미 id가 존재합니다.";
        }
        memberService.save(member); // id, pw 형식, 중복검사를 통과했을 때 저장.
        return "index";
    }

    //id 중복검사 api
    //ajax로 프론트에서 요청 후 js로 프론트에서 처리하는게 좋을 듯.
    //에러페이지를 보여줄까도 했지만 ajax가 더 현대적일듯.
    @ResponseBody
    @PostMapping("/isIdDuplicated")
    public boolean isIdDuplicated(@RequestBody String loginId) {

        System.out.println("isIdDuplicated");
        System.out.println("loginId : "+loginId);
        return memberService.isIdDuplicated(loginId);
    }
}
