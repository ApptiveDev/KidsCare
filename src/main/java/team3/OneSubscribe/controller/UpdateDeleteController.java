package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team3.OneSubscribe.DTO.WritingDTO;
import team3.OneSubscribe.domain.*;
import team3.OneSubscribe.repository.AnswerRepository;
import team3.OneSubscribe.repository.MemberRepository;
import team3.OneSubscribe.repository.TagRepository;
import team3.OneSubscribe.repository.WritingRepository;
import team3.OneSubscribe.service.AnswerService;
import team3.OneSubscribe.service.MemberService;
import team3.OneSubscribe.service.WritingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/contents")
public class UpdateDeleteController {

    private final WritingRepository writingRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final TagRepository tagRepository;
    private final AnswerService answerService;
    private final AnswerRepository answerRepository;

    private final WritingService writingService;

    @PostMapping("/{writingId}/update")
    public String updateWriting(@PathVariable("writingId") Long writingId, WritingDTO form, HttpServletRequest request){
        //writingService.updateWriting(writingId, form.getTitle(), form.getContext());

        Writing writing = writingRepository.findOneById(writingId);

        // 1. 제목, 내용, 시간 저장
        writing.setTitle(form.getTitle());
        writing.setContext(form.getContext());
        writing.setUpdateDate(LocalDateTime.now());
        writingRepository.save(writing);

        // 기존tag 삭제
        tagRepository.deleteMany(writing);

        // 2. tag 저장
        if (Objects.equals(request.getParameter("복통"), "1")) {
            Tag tag1 = new Tag();
            tag1.setDiseaseName(DiseaseName.abdominalPain);
            tag1.setWriting(writing);
            tagRepository.save(tag1);
        }
        if (Objects.equals(request.getParameter("설사"), "1")) {
            Tag tag2 = new Tag();
            tag2.setDiseaseName(DiseaseName.diarrhea);
            tag2.setWriting(writing);
            tagRepository.save(tag2);
        }
        if (Objects.equals(request.getParameter("영양질환"), "1")) {
            Tag tag3 = new Tag();
            tag3.setDiseaseName(DiseaseName.nutritionalDisease);
            tag3.setWriting(writing);
            tagRepository.save(tag3);
        }
        if (Objects.equals(request.getParameter("비염"), "1")) {
            Tag tag4 = new Tag();
            tag4.setDiseaseName(DiseaseName.rhinitis);
            tag4.setWriting(writing);
            tagRepository.save(tag4);
        }
        if (Objects.equals(request.getParameter("폐렴"), "1")) {
            Tag tag5 = new Tag();
            tag5.setDiseaseName(DiseaseName.pneumonia);
            tag5.setWriting(writing);
            tagRepository.save(tag5);
        }
        if (Objects.equals(request.getParameter("천식"), "1")) {
            Tag tag6 = new Tag();
            tag6.setDiseaseName(DiseaseName.asthma);
            tag6.setWriting(writing);
            tagRepository.save(tag6);
        }
        if (Objects.equals(request.getParameter("알레르기"), "1")) {
            Tag tag7 = new Tag();
            tag7.setDiseaseName(DiseaseName.allergic);
            tag7.setWriting(writing);
            tagRepository.save(tag7);
        }
        if (Objects.equals(request.getParameter("아토피"), "1")) {
            Tag tag8 = new Tag();
            tag8.setDiseaseName(DiseaseName.atopy);
            tag8.setWriting(writing);
            tagRepository.save(tag8);
        }
        if (Objects.equals(request.getParameter("두통"), "1")) {
            Tag tag9 = new Tag();
            tag9.setDiseaseName(DiseaseName.cephalagia);
            tag9.setWriting(writing);
            tagRepository.save(tag9);
        }
        if (Objects.equals(request.getParameter("중이염"), "1")) {
            Tag tag10 = new Tag();
            tag10.setDiseaseName(DiseaseName.otitisMedia);
            tag10.setWriting(writing);
            tagRepository.save(tag10);
        }
        if (Objects.equals(request.getParameter("축농증"), "1")) {
            Tag tag11 = new Tag();
            tag11.setDiseaseName(DiseaseName.empyema);
            tag11.setWriting(writing);
            tagRepository.save(tag11);
        }
        return "redirect:/contents/{writingId}";
    }

    @GetMapping("/writing/{writingId}/delete")
    public String deleteWriting(@PathVariable("writingId") Long writingId){
        // tag 삭제(해당 writing의 태그) // DB 구축 방법으로 인해 2가지 삭제하는거임
        Writing writing = writingRepository.findOneById(writingId);
        tagRepository.deleteMany(writing);

        // writing 삭제
        writingRepository.deleteOne(writingId);

        return "redirect:/contents";
    }



    @GetMapping("/{writingId}/{answerId}/delete")
    public String deleteAnswer(@PathVariable("writingId") Long writingId, @PathVariable("answerId") Long answerId){
        // 답변을 작성한 회원인지 먼저 확인
        answerRepository.deleteOne(answerId);
        return "redirect:/contents/{writingId}";
    }


}
