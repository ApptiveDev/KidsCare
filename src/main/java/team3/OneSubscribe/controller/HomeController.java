package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.domain.WritingContent;
import team3.OneSubscribe.repository.MemberRepository;
import team3.OneSubscribe.repository.WritingRepository;
import team3.OneSubscribe.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @Autowired
    WritingRepository writingRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @GetMapping("/setting")
    public String makeTheId(){
        Member member = new Member();
        member.setNickName("choi");
        member.setLoginId("test");
        member.setLoginPassword("test1");
        member.setNickName("테스트Choi");
        memberService.save(member);
        return "index";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/member/login")
    public String loginGet() {
        return "login";
    }

    @GetMapping("/member/signup")
    public String signup() {
        return "signup";
    }









}
