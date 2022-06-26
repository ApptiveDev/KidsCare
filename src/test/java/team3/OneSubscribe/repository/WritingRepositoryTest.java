package team3.OneSubscribe.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.service.MemberService;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class WritingRepositoryTest {

    @Autowired
    MemberRepositoryImp memberRepository;
    @Autowired EntityManager em;
    @Autowired WritingRepository writingRepository;
    @Autowired
    MemberService memberService;


    @Test
    @Rollback(false)
    void findAll() {
        //Given
        Member member = new Member();
        member.setNickName("kim");
        Long saveId = memberService.save(member);
        Writing writing1 = new Writing();
        writing1.setTitle("titleTest1");
        writing1.setContext("contextTest1");

        Writing writing2 = new Writing();
        writing2.setTitle("titleTest2");
        writing2.setContext("contextTest2");

        //When
        writingRepository.save(writing1);
        writingRepository.save(writing2);
        List<Writing> savedWritings = writingRepository.findAll();

        //Then
        assertEquals("titleTest1", savedWritings.get(0).getTitle());
        assertEquals("titleTest2", savedWritings.get(1).getTitle());

        assertEquals("contextTest1", savedWritings.get(0).getContext());
        assertEquals("contextTest2", savedWritings.get(1).getContext());
    }
}