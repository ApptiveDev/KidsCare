package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.repository.AnswerRepository;
import team3.OneSubscribe.repository.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/contents")
public class FeedbackController {

    private final AnswerRepository answerRepository;

    private final MemberRepository memberRepository;

    @Transactional
    @GetMapping("/{answerId}/feedback")
    public String Feedback(@PathVariable("answerId") Long answerId, HttpServletRequest request, Model model){

        // redirection을 위한 경로 확인
        Long writingId = answerRepository.findOneById(answerId).getWriting().getId();

        // 유저랑, 글 작성자가 맞는지 확인 // request에 writingId 넣어줘야 함.
        Member questioner = memberRepository.findOneById(answerRepository.findOneById(answerId)
                .getWriting()
                .getMember()
                .getId()); // 답변으로부터 글 작성자 알기 // 답변 작성자 아님
        HttpSession session = request.getSession();
        Member user = memberRepository.findByLoginId(((Member) session.getAttribute("member")).getLoginId()); // 사용자
        if(questioner == user){
            model.addAttribute("operation", true);
            Answer answer = answerRepository.findOneById(answerId);
            if(answer.getFeedback() == 0) {
                answer.setFeedback(1);
                Member answerHuman = memberRepository.findByNickName(answerRepository.findOneById(answerId).getNickName());
                Long newTotalLikeNumber = answerHuman.getTotalLikeNumber() + 1L;
                answerHuman.setTotalLikeNumber(newTotalLikeNumber);
            }
            String s = "redirect:/contents/" + writingId;
            return s;
        }
        else{
            model.addAttribute("operation", false);
            String s = "redirect:/contents/"+writingId;
            return s;
        }
    }
}
