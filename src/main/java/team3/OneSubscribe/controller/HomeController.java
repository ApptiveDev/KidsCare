package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team3.OneSubscribe.domain.*;
import team3.OneSubscribe.repository.MemberRepository;
import team3.OneSubscribe.repository.TagRepository;
import team3.OneSubscribe.repository.WritingRepository;
import team3.OneSubscribe.service.MemberService;
import team3.OneSubscribe.service.WritingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final WritingRepository writingRepository;

    private final WritingService writingService;

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final TagRepository tagRepository;

    @GetMapping("/")
    @PostMapping("/") //redirection을 위한것
    public String index(Model model, HttpServletRequest request) {

        HttpSession sess = request.getSession(false);//로그인 하면 회원가입, 로그인 버튼이 사라지도록.

        if (sess != null && sess.getAttribute("member") != null) { //로그인 했을 때
//            System.out.println("로그인했다!");
            model.addAttribute("isLogined", true);
            System.out.println("테스트 : "+ ((Member) sess.getAttribute("member")));
            model.addAttribute("nickName", ((Member) sess.getAttribute("member")).getName());
            System.out.println("nickName : " + ((Member) sess.getAttribute("member")).getNickName());
        }

        // 금주의 베스트 글
        List<Writing> bestWritings = writingService.sequenceByAnswerNumberForAWeek();
        List<Writing> best3Writings = new LinkedList<>();
        if(bestWritings.size() == 0){
            model.addAttribute("writings", null);
        }
        else {
            if(bestWritings.size() < 3){
                for(int i = 0; i < bestWritings.size(); i++)
                    best3Writings.add(bestWritings.get(i));
            }
            else{
                for (int i = 0; i < 3; i++) {
                    best3Writings.add(bestWritings.get(i));
                }
            }
            model.addAttribute("bestWritings", best3Writings);
        }

        // 전체 글 목록 // 일단은 5개만 주기 // 최신순으로 던져줌
        List<Writing> writings = writingRepository.findAll();
        List<Writing> recentWritings = new LinkedList<>();
        if(writings.size() == 0){
            model.addAttribute("writings", null);
        }
        else {
            if(writings.size() < 6){
                for (int i = writings.size(); i > 0; i--) {
                    recentWritings.add(writings.get(i - 1));
                }
            }
            else{
                for (int i = 0; i < 6; i++) {
                    recentWritings.add(writings.get(writings.size() - 1 - i));
                }
            }

            model.addAttribute("writings", recentWritings);
        }

        // 베스트 전문가
        // 사람 다 찾고, totalLikeNumber 비교, 위에 3명 보이게 하기 // 일단 0도 넣기
        List<Member> bestExpert = memberRepository.findBestExpert();
        List<Member> bestInExpert = memberRepository.findBestInexpert();
        model.addAttribute("bestExperts", bestExpert);
        model.addAttribute("bestInexperts", bestInExpert);
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
