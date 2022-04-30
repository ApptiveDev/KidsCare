package team3.OneSubscribe.controller.login;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team3.OneSubscribe.service.LoginService;
import team3.OneSubscribe.service.MailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("login")
public class LoginController {


    //자동 autowierd
    private final MailService mailService;
    private final LoginService loginService;

//    @GetMapping("mailtest")
//    public String sendMail() {
//        return "email";
//
//    }

    @PostMapping("/login_member")
    public String loginMember(@RequestParam String loginId, @RequestParam String pw, HttpServletRequest request, Model model) {
        model.addAttribute("login", "fail");
//        if (loginService.login(loginId, pw)) {
//            request.getSession();//세션이 없다면 세션생성.
//            model.addAttribute("login", "success");
//        }
        return "index";

    }

    @PostMapping("/logout")
    public String logoutMember(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false); //세션이 없어도 생성안함.
        model.addAttribute("logout", "fail");
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
}
