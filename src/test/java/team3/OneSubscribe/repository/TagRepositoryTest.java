package team3.OneSubscribe.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.service.MemberService;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class TagRepositoryTest {

    @Autowired
    MemberRepositoryImp memberRepository;
    @Autowired
    EntityManager em;
    @Autowired WritingRepository writingRepository;
    @Autowired
    MemberService memberService;

    @Test
    @Rollback(false)
    void findAll() {
        Tag tag = new Tag();

    }
}