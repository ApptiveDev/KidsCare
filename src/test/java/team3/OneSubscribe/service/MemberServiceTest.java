package team3.OneSubscribe.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.repository.MemberRepository;
import team3.OneSubscribe.repository.MemberRepositoryImp;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepositoryImp memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void 회원가입() throws Exception{
        //Given
        Member member1 = new Member();
        member1.setNickName("choi");
        member1.setLoginId("chl");

        Member member2 = new Member();
        member2.setNickName("shin");
        member2.setLoginId("tls");

        Member member3 = new Member();
        member3.setNickName("hwang");
        member3.setLoginId("ghkd");

        //When
        Long savedId1 = memberService.save(member1);
        Long savedId2 = memberService.save(member2);
        Long savedId3 = memberService.save(member3);

        //Then
        assertEquals(member1.getId(), savedId1);
        assertEquals(member2.getId(), savedId2);
        assertEquals(member3.getId(), savedId3);

        assertEquals(member1.getId(), memberRepository.findByLoginId("chl").getId());
        assertEquals(member2.getId(), memberRepository.findByLoginId("tls").getId());
        assertEquals(null, memberRepository.findByLoginId("ahffk"));

    }
}