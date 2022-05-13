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
import team3.OneSubscribe.DTO.WritingDTO;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.DiseaseName;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.repository.MemberRepository;
import team3.OneSubscribe.repository.TagRepository;
import team3.OneSubscribe.repository.WritingRepository;
import team3.OneSubscribe.service.AnswerService;
import team3.OneSubscribe.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/contents")
public class ContentsWritingController {

    private final WritingRepository writingRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final TagRepository tagRepository;
    private final AnswerService answerService;

    @GetMapping("/writing")
    public String writingPage(Model model){
        model.addAttribute("form", new WritingDTO());
        return "writing";
    }

    @PostMapping("/writing/new")
    public String contentsFromWriting(WritingDTO form, HttpServletRequest request){
        HttpSession session = request.getSession();
        Member writer = memberRepository.findByLoginId(((Member)session.getAttribute("member")).getLoginId());

        // 1. writing 저장
        Writing writing = new Writing(form);
        writing.setCreateDate(LocalDateTime.now());

        System.out.println("세션 : " + session.getAttribute("member"));
        //writing.setMember((Member) session.getAttribute("member")); // 여기 고쳐야 함
        writingRepository.save(writing);


        // 2. tag 저장
        if(Objects.equals(request.getParameter("복통"), "1")){
            Tag tag1 = new Tag();
            tag1.setDiseaseName(DiseaseName.abdominalPain);
            tag1.setWriting(writing);
            tagRepository.save(tag1);
        }
        if(Objects.equals(request.getParameter("설사"), "1")){
            Tag tag2 = new Tag();
            tag2.setDiseaseName(DiseaseName.diarrhea);
            tag2.setWriting(writing);
            tagRepository.save(tag2);
        }
        if(Objects.equals(request.getParameter("영양질환"), "1")){
            Tag tag3 = new Tag();
            tag3.setDiseaseName(DiseaseName.nutritionalDisease);
            tag3.setWriting(writing);
            tagRepository.save(tag3);
        }
        if(Objects.equals(request.getParameter("비염"), "1")){
            Tag tag4 = new Tag();
            tag4.setDiseaseName(DiseaseName.rhinitis);
            tag4.setWriting(writing);
            tagRepository.save(tag4);
        }
        if(Objects.equals(request.getParameter("폐렴"), "1")){
            Tag tag5 = new Tag();
            tag5.setDiseaseName(DiseaseName.pneumonia);
            tag5.setWriting(writing);
            tagRepository.save(tag5);
        }
        if(Objects.equals(request.getParameter("천식"), "1")){
            Tag tag6 = new Tag();
            tag6.setDiseaseName(DiseaseName.asthma);
            tag6.setWriting(writing);
            tagRepository.save(tag6);
        }
        if(Objects.equals(request.getParameter("알레르기"), "1")){
            Tag tag7 = new Tag();
            tag7.setDiseaseName(DiseaseName.allergic);
            tag7.setWriting(writing);
            tagRepository.save(tag7);
        }
        if(Objects.equals(request.getParameter("아토피"), "1")){
            Tag tag8 = new Tag();
            tag8.setDiseaseName(DiseaseName.atopy);
            tag8.setWriting(writing);
            tagRepository.save(tag8);
        }
        if(Objects.equals(request.getParameter("두통"), "1")){
            Tag tag9 = new Tag();
            tag9.setDiseaseName(DiseaseName.cephalagia);
            tag9.setWriting(writing);
            tagRepository.save(tag9);
        }
        if(Objects.equals(request.getParameter("중이염"), "1")){
            Tag tag10 = new Tag();
            tag10.setDiseaseName(DiseaseName.otitisMedia);
            tag10.setWriting(writing);
            tagRepository.save(tag10);
        }
        if(Objects.equals(request.getParameter("축농증"), "1")){
            Tag tag11 = new Tag();
            tag11.setDiseaseName(DiseaseName.empyema);
            tag11.setWriting(writing);
            tagRepository.save(tag11);
        }
        return "redirect:/contents";
    }

    @GetMapping("")
    //전체글 조회
    public String contents(Model model){
        //TODO : 전부 가져오면 터진다. 10개 또는 20개씩 끊어서 가져오기
        List<Writing> writings = writingRepository.findAll();
        model.addAttribute("writings", writings);
        return "posts";
    }

    @GetMapping("/{writingId}")
    //글 하나 보여주기
    public String writingContents(@PathVariable("writingId") Long writingId, Model model){
        //글찾기
        Writing writing = (Writing) writingRepository.findOneById(writingId);
        model.addAttribute("title", writing.getTitle());//writing객체 다 넘기지 말고 필요한 애들만 넘기자
        model.addAttribute("context", writing.getContext());

        //댓글찾기
        List<Answer> li = answerService.findAllAnswerByWriting(writing);
        model.addAttribute("li", li);
        return "showWriting";
    }

}
