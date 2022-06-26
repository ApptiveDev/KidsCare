package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import team3.OneSubscribe.domain.DiseaseName;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.repository.MemberRepository;
import team3.OneSubscribe.repository.TagRepository;
import team3.OneSubscribe.repository.WritingRepository;
import team3.OneSubscribe.service.MemberService;
import team3.OneSubscribe.service.WritingService;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final WritingRepository writingRepository;

    private final WritingService writingService;

    private final MemberRepository memberRepository;

    private final MemberService memberService;

    private final TagRepository tagRepository;

    @GetMapping("/setting")
    public String makeTheId(){
        Member member1 = new Member();
        member1.setNickName("choi");
        member1.setLoginId("test");
        member1.setLoginPassword("test1");
        memberService.save(member1);

        Member member2 = new Member();
        member2.setNickName("shin");
        member2.setLoginId("shin");
        member2.setLoginPassword("test1");
        memberService.save(member2);

        Member member3 = new Member();
        member3.setNickName("hwang");
        member3.setLoginId("hwang");
        member3.setLoginPassword("test1");
        member3.setTotalLikeNumber(10L);
        memberService.save(member3);

        Member member4 = new Member();
        member4.setNickName("ahn");
        member4.setLoginId("ahn");
        member4.setLoginPassword("test1");
        member4.setTotalLikeNumber(1L);
        memberService.save(member4);

        Member member5 = new Member();
        member5.setNickName("lee");
        member5.setLoginId("lee");
        member5.setLoginPassword("test1");
        memberService.save(member5);

        Member member6 = new Member();
        member6.setNickName("kim");
        member6.setLoginId("kim");
        member6.setLoginPassword("test1");
        member6.setTotalLikeNumber(8L);
        memberService.save(member6);
        return "redirect:/";
    }
    @GetMapping("/setting1")
    public String settingForTesting(){
        Writing writing1 = new Writing();
        writing1.setTitle("복통이 있어요");
        writing1.setContext("아이에게 복통이 있어요");
        writing1.setMember(memberRepository.findByLoginId("test"));
        writing1.setCreateDate(LocalDateTime.now());
        writingRepository.save(writing1);



        Tag tag1 = new Tag();
        tag1.setDiseaseName(DiseaseName.abdominalPain);
        tag1.setWriting(writing1);
        tagRepository.save(tag1);

        Writing writing2 = new Writing();
        writing2.setTitle("설사 증상이 있어요");
        writing2.setContext("아이에게 설사 증상이 있어요");
        writing2.setMember(memberRepository.findByLoginId("shin"));
        Long w2 = writingRepository.save(writing2);
        writingRepository.findOneById(w2).setCreateDate(LocalDateTime.now().minusDays(1));

        Tag tag2 = new Tag();
        tag2.setDiseaseName(DiseaseName.diarrhea);
        tag2.setWriting(writing2);
        tagRepository.save(tag2);

        Writing writing3 = new Writing();
        writing3.setTitle("어떤 영양 성분이 부족할까요?");
        writing3.setContext("아이에게 어떤 영양 성분이 부족할까요?");
        writing3.setMember(memberRepository.findByLoginId("hwang"));
        Long w3 = writingRepository.save(writing3);
        writingRepository.findOneById(w3).setCreateDate(LocalDateTime.now().minusDays(2));


        Tag tag3 = new Tag();
        tag3.setDiseaseName(DiseaseName.nutritionalDisease);
        tag3.setWriting(writing3);
        tagRepository.save(tag3);

        Writing writing4 = new Writing();
        writing4.setTitle("복통과 설사를 증상이 있어요");
        writing4.setContext("아이에게 복통과 설사를 증상이 있어요");
        writing4.setMember(memberRepository.findByLoginId("ahn"));
        Long w4 = writingRepository.save(writing4);
        writingRepository.findOneById(w4).setCreateDate(LocalDateTime.now().minusDays(3));


        Tag tag4 = new Tag();
        tag4.setDiseaseName(DiseaseName.abdominalPain);
        tag4.setWriting(writing4);
        tagRepository.save(tag4);

        Tag tag4p = new Tag();
        tag4p.setDiseaseName(DiseaseName.diarrhea);
        tag4p.setWriting(writing4);
        tagRepository.save(tag4p);

        Writing writing5 = new Writing();
        writing5.setTitle("복통, 설사, 영양질환에 관하여");
        writing5.setContext("복통, 설사, 영양질환에 대한 글 내용");
        writing5.setMember(memberRepository.findByLoginId("lee"));
        Long w5 = writingRepository.save(writing5);
        writingRepository.findOneById(w5).setCreateDate(LocalDateTime.now().minusDays(4));

        Tag tag5 = new Tag();
        tag5.setDiseaseName(DiseaseName.abdominalPain);
        tag5.setWriting(writing5);
        tagRepository.save(tag5);

        Tag tag5p = new Tag();
        tag5p.setDiseaseName(DiseaseName.diarrhea);
        tag5p.setWriting(writing5);
        tagRepository.save(tag5p);

        Tag tag5pp = new Tag();
        tag5pp.setDiseaseName(DiseaseName.nutritionalDisease);
        tag5pp.setWriting(writing5);
        tagRepository.save(tag5pp);

        Writing writing6 = new Writing();
        writing6.setTitle("비염이 있어요");
        writing6.setContext("아이의 코가 자주 막혀요");
        writing6.setMember(memberRepository.findByLoginId("kim"));
        Long w6 = writingRepository.save(writing6);
        writingRepository.findOneById(w6).setCreateDate(LocalDateTime.now().minusDays(5));

        Tag tag6 = new Tag();
        tag6.setDiseaseName(DiseaseName.rhinitis);
        tag6.setWriting(writing6);
        tagRepository.save(tag6);

        return "redirect:/";
    }
}
