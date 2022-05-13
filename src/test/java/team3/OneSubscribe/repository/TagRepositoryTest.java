package team3.OneSubscribe.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team3.OneSubscribe.domain.DiseaseName;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.service.MemberService;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

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
    @Autowired
    TagRepository tagRepository;


    @Test
    @Rollback(false)
    void findAll() {
        Tag tag = new Tag();

    }

    @Test
    @Rollback(false)
    public void findListByWriting(){
        Tag tag = new Tag();
        Writing writing = new Writing();
        writing.setTitle("a");
        writingRepository.save(writing);

        tag.setDiseaseName(DiseaseName.diarrhea);
        tag.setWriting(writing);
        tagRepository.save(tag);

        Tag tag1 = new Tag();
        Writing writing1 = new Writing();
        writing1.setTitle("b");
        writingRepository.save(writing1);

        tag1.setDiseaseName(DiseaseName.nutritionalDisease);
        tag1.setWriting(writing1);
        tagRepository.save(tag1);

        List<Tag> tags = tagRepository.findListByWriting(writing1);
        System.out.println(tags.get(0).getDiseaseName());
    }
}