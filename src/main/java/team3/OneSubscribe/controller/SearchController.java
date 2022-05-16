package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team3.OneSubscribe.domain.DiseaseName;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.repository.MemberRepository;
import team3.OneSubscribe.repository.TagRepository;
import team3.OneSubscribe.repository.WritingRepository;
import team3.OneSubscribe.service.MemberService;
import team3.OneSubscribe.service.SearchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static team3.OneSubscribe.domain.DiseaseName.*;
import static team3.OneSubscribe.domain.DiseaseName.abdominalPain;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    private final WritingRepository writingRepository;

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final TagRepository tagRepository;

    private final SearchService searchService;

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @GetMapping("/search/result")
    public String search(HttpServletRequest request, Model model){

        List<Tag> tags = new LinkedList<Tag>();

        List<DiseaseName> diseaseNames = new LinkedList<DiseaseName>();
        List<String> diseaseNamesKorean = new LinkedList<String>();
        if(Objects.equals(request.getParameter("복통"), "1")){
            diseaseNames.add(abdominalPain);
            diseaseNamesKorean.add("복통");
        }
        if(Objects.equals(request.getParameter("설사"), "1")){
            diseaseNames.add(diarrhea);
            diseaseNamesKorean.add("설사");
        }
        if(Objects.equals(request.getParameter("영양질환"), "1")){
            diseaseNames.add(nutritionalDisease);
            diseaseNamesKorean.add("영양질환");
        }
        if(Objects.equals(request.getParameter("비염"), "1")){
            diseaseNames.add(rhinitis);
            diseaseNamesKorean.add("비염");
        }
        if(Objects.equals(request.getParameter("폐렴"), "1")){
            diseaseNames.add(pneumonia);
            diseaseNamesKorean.add("폐렴");
        }
        if(Objects.equals(request.getParameter("천식"), "1")){
            diseaseNames.add(asthma);
            diseaseNamesKorean.add("천식");
        }
        if(Objects.equals(request.getParameter("알레르기"), "1")){
            diseaseNames.add(allergic);
            diseaseNamesKorean.add("알레르기");
        }
        if(Objects.equals(request.getParameter("아토피"), "1")){
            diseaseNames.add(atopy);
            diseaseNamesKorean.add("아토피");
        }
        if(Objects.equals(request.getParameter("두통"), "1")){
            diseaseNames.add(cephalagia);
            diseaseNamesKorean.add("두통");
        }
        if(Objects.equals(request.getParameter("중이염"), "1")){
            diseaseNames.add(otitisMedia);
            diseaseNamesKorean.add("중이염");
        }
        if(Objects.equals(request.getParameter("축농증"), "1")){
            diseaseNames.add(empyema);
            diseaseNamesKorean.add("축농증");
        }
        model.addAttribute("diseaseNamesKorean", diseaseNamesKorean);
        model.addAttribute("diseaseNames", diseaseNames);

        List<Writing> writings = searchService.searchResults(diseaseNames);
        // 확인용 // 나중에 지워야함
        for(int i = 0; i < writings.size(); i++){
            System.out.println("searchList : " + writings.get(i).getTitle());
        }
        model.addAttribute("searchResults", writings);
        return "search_result";
    }
}
