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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    TagRepository tagRepository;

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @GetMapping("/search/result")
    public String search(HttpServletRequest request, Model model){
//        HttpSession session;
//        session = request.getSession();
        // 위에꺼 필요없을거 같은데, 그냥 바로 request에 접근하는데?

        ArrayList<Tag> tags = new ArrayList<Tag>();
        if(Objects.equals(request.getParameter("복통"), "1")){
            Tag tag1 = new Tag();
            tag1.setDiseaseName(DiseaseName.abdominalPain);
            tags.add(tag1);
        }
        if(Objects.equals(request.getParameter("설사"), "1")){
            Tag tag2 = new Tag();
            tag2.setDiseaseName(DiseaseName.diarrhea);
            tags.add(tag2);
        }
        if(Objects.equals(request.getParameter("영양질환"), "1")){
            Tag tag3 = new Tag();
            tag3.setDiseaseName(DiseaseName.nutritionalDisease);
            tags.add(tag3);
        }
        if(Objects.equals(request.getParameter("비염"), "1")){
            Tag tag4 = new Tag();
            tag4.setDiseaseName(DiseaseName.rhinitis);
            tags.add(tag4);
        }
        if(Objects.equals(request.getParameter("폐렴"), "1")){
            Tag tag5 = new Tag();
            tag5.setDiseaseName(DiseaseName.pneumonia);
            tags.add(tag5);
        }
        if(Objects.equals(request.getParameter("천식"), "1")){
            Tag tag6 = new Tag();
            tag6.setDiseaseName(DiseaseName.asthma);
            tags.add(tag6);
        }
        if(Objects.equals(request.getParameter("알레르기"), "1")){
            Tag tag7 = new Tag();
            tag7.setDiseaseName(DiseaseName.allergic);
            tags.add(tag7);
        }
        if(Objects.equals(request.getParameter("아토피"), "1")){
            Tag tag8 = new Tag();
            tag8.setDiseaseName(DiseaseName.atopy);
            tags.add(tag8);
        }
        if(Objects.equals(request.getParameter("두통"), "1")){
            Tag tag9 = new Tag();
            tag9.setDiseaseName(DiseaseName.cephalagia);
            tags.add(tag9);
        }
        if(Objects.equals(request.getParameter("중이염"), "1")){
            Tag tag10 = new Tag();
            tag10.setDiseaseName(DiseaseName.otitisMedia);
            tags.add(tag10);
        }
        if(Objects.equals(request.getParameter("축농증"), "1")){
            Tag tag11 = new Tag();
            tag11.setDiseaseName(DiseaseName.empyema);
            tags.add(tag11);
        }
        //
        // tags를 이용해서 search 하기

        List<Writing> searchResults = writingRepository.findAll(); // 이거말고 알고리즘 짜서 List만들어야 함.
        model.addAttribute("searchResults", searchResults);
        return "search_result";
    }
}
