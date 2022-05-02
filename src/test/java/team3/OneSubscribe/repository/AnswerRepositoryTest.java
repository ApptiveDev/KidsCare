package team3.OneSubscribe.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.service.MemberService;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class AnswerRepositoryTest {

    @Autowired
    MemberRepositoryImp memberRepository;
    @Autowired
    EntityManager em;

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    MemberService memberService;

    @Test
    @Rollback(false)
    void findOneById() {
        //Given
        Member member = new Member();
        member.setNickName("kim");
        Answer answer1 = new Answer();
        answer1.setContext("answerContext1");
        answerRepository.save(answer1);

        //When
        Answer savedAnswer = answerRepository.findOneById(answer1.getId());

        //Then
        assertEquals("answerContext1", savedAnswer.getContext());
    }

    @Test
    @Rollback(false)
    void findAll() {
        //Given
        Member member = new Member();
        member.setNickName("kim");
        Answer answer1 = new Answer();
        answer1.setContext("answerContext1");
        answerRepository.save(answer1);
        Answer answer2 = new Answer();
        answer2.setContext("answerContext2");
        answerRepository.save(answer2);

        //When
        List<Answer> savedAnswers = answerRepository.findAll();

        //Then
        assertEquals("answerContext1", savedAnswers.get(0).getContext());
        assertEquals("answerContext2", savedAnswers.get(1).getContext());
    }
}