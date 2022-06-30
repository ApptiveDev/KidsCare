package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team3.OneSubscribe.DTO.Pagination;
import team3.OneSubscribe.domain.DiseaseName;
import team3.OneSubscribe.domain.Member;
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
    public String search(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") int page){

        HttpSession session;
        session = request.getSession();//세션이 없다면 세션생성.
        if(session.getAttribute("member") != null) {
            Member m = (Member) session.getAttribute("member");
            model.addAttribute("name", m.getName());
        }else{
            Member m = (Member) session.getAttribute("member");
            model.addAttribute("name", "'로그인 안됨'");
        }
        if(request.getParameter("복통") != null) {
            session.setAttribute("복통", request.getParameter("복통"));
            session.setAttribute("설사", request.getParameter("설사"));
            session.setAttribute("영양질환", request.getParameter("영양질환"));
            session.setAttribute("비염", request.getParameter("비염"));
            session.setAttribute("폐렴", request.getParameter("폐렴"));
            session.setAttribute("천식", request.getParameter("천식"));
            session.setAttribute("알레르기", request.getParameter("알레르기"));
            session.setAttribute("아토피", request.getParameter("아토피"));
            session.setAttribute("두통", request.getParameter("두통"));
            session.setAttribute("중이염", request.getParameter("중이염"));
            session.setAttribute("축농증", request.getParameter("축농증"));
        }

        List<Tag> tags = new LinkedList<Tag>();

        List<DiseaseName> diseaseNames = new LinkedList<DiseaseName>();
        List<String> diseaseNamesKorean = new LinkedList<String>();
        if(session.getAttribute("복통").equals("1")){
            diseaseNames.add(abdominalPain);
            diseaseNamesKorean.add("복통");
        }
        if(session.getAttribute("설사").equals("1")){
            diseaseNames.add(diarrhea);
            diseaseNamesKorean.add("설사");
        }
        if(session.getAttribute("영양질환").equals("1")){
            diseaseNames.add(nutritionalDisease);
            diseaseNamesKorean.add("영양질환");
        }
        if(session.getAttribute("비염").equals("1")){
            diseaseNames.add(rhinitis);
            diseaseNamesKorean.add("비염");
        }
        if(session.getAttribute("폐렴").equals("1")){
            diseaseNames.add(pneumonia);
            diseaseNamesKorean.add("폐렴");
        }
        if(session.getAttribute("천식").equals("1")){
            diseaseNames.add(asthma);
            diseaseNamesKorean.add("천식");
        }
        if(session.getAttribute("알레르기").equals("1")){
            diseaseNames.add(allergic);
            diseaseNamesKorean.add("알레르기");
        }
        if(session.getAttribute("아토피").equals("1")){
            diseaseNames.add(atopy);
            diseaseNamesKorean.add("아토피");
        }
        if(session.getAttribute("두통").equals("1")){
            diseaseNames.add(cephalagia);
            diseaseNamesKorean.add("두통");
        }
        if(session.getAttribute("중이염").equals("1")){
            diseaseNames.add(otitisMedia);
            diseaseNamesKorean.add("중이염");
        }
        if(session.getAttribute("축농증").equals("1")){
            diseaseNames.add(empyema);
            diseaseNamesKorean.add("축농증");
        }
        model.addAttribute("diseaseNamesKorean", diseaseNamesKorean);
        model.addAttribute("diseaseNames", diseaseNames);

        List<Writing> writings = searchService.searchResults(diseaseNames);

        // 총 게시물 수
        int totalListCnt = writings.size();

        // 생성인자로
        Pagination pagination = new Pagination(totalListCnt, page);

        // DB select start index
        int startIndex = pagination.getStartIndex();

        // 페이지 당 보여지는 게시글의 최대 개수
        int pageSize = pagination.getPageSize();

        int finalIndex;
        if(startIndex + pageSize > writings.size()){
            finalIndex = writings.size();
        }else{
            finalIndex = startIndex+pageSize;
        }
        List<Writing> searchList = writings.subList(startIndex, finalIndex);

        model.addAttribute("searchResults", searchList);
        model.addAttribute("pagination", pagination);
        model.addAttribute("searchCounting", writings.size());
        return "search_result";
    }
}
