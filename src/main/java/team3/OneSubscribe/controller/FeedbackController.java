package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
    @PostMapping("/{answerId}/feedback")
    public String Feedback(@PathVariable("answerId") Long answerId, HttpServletRequest request, Model model){
        // 유저랑, 글 작성자가 맞는지 확인 // request에 writingId 넣어줘야 함.
        Member answerHuman = memberRepository.findByNickName(request.getParameter("nickName"));
        HttpSession session = request.getSession();
        Member questioner = memberRepository.findByLoginId(((Member) session.getAttribute("member")).getLoginId());

        // 동일하면(권한이 있으면) 답변의 feedback = true로 해주고, 답변자의 totalLikeNumber+= 1 해주기
//        Answer answer = answerRepository.findOneByNickName(request.getParameter("nickName"));
        Answer answer = answerRepository.findOneById(answerId);
        answer.setFeedback(true);


        return "redirect:/contents/{writingId}"; // 여기서도 feedback 주기
    }
}
