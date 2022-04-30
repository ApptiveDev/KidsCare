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
        Member member = new Member();
        member.setNickName("kim");

        //When
        Long saveId = memberService.save(member);

        //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }
}