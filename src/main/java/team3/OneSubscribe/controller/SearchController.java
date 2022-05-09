package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team3.OneSubscribe.repository.MemberRepository;
import team3.OneSubscribe.repository.WritingRepository;
import team3.OneSubscribe.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    @Autowired
    WritingRepository writingRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @GetMapping("/contents/search")
    public String search(){
        return "search";
    }

    @PostMapping("/contents/search")
    public String search(HttpServletRequest request, Model model){
        System.out.println(request.getParameter("복통"));
        System.out.println(request.getParameter("설사"));
        System.out.println(request.getParameter("영양질환"));
        System.out.println(request.getParameter("비염"));
        System.out.println(request.getParameter("폐렴"));
        System.out.println(request.getParameter("천식"));
        System.out.println(request.getParameter("알레르기"));
        System.out.println(request.getParameter("아토피"));
        System.out.println(request.getParameter("두통"));
        System.out.println(request.getParameter("중이염"));
        System.out.println(request.getParameter("축농증"));
        return "search";
    }
}
