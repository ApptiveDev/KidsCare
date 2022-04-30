package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team3.OneSubscribe.service.MemberService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login_member")
    public String loginMember(@RequestParam String loginId, @RequestParam String pw, HttpServletRequest request, Model model) {
        model.addAttribute("login", "fail");
        if (memberService.login(loginId, pw)) {
            request.getSession();//세션이 없다면 세션생성.
            model.addAttribute("login", "success");
        }
        return "index";

    }



}
