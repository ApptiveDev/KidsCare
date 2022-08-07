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


import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private final WritingRepository writingRepository;

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final TagRepository tagRepository;

    @GetMapping("/setting")
    public String makeTheId(){
        Member member1 = new Member();
        member1.setNickName("최연재");
        member1.setLoginId("choi");
        member1.setLoginPassword("test1");
        member1.setTotalLikeNumber(10L);
        memberService.save(member1);

        Member member2 = new Member();
        member2.setNickName("shin");
        member2.setLoginId("shin");
        member2.setLoginPassword("test1");
        memberService.save(member2);

        Member member3 = new Member();
        member3.setNickName("황정호");
        member3.setLoginId("hwang");
        member3.setLoginPassword("test1");
        member3.setTotalLikeNumber(20L);
        memberService.save(member3);

        Member member4 = new Member();
        member4.setNickName("ahn");
        member4.setLoginId("ahn");
        member4.setLoginPassword("test1");
        memberService.save(member4);

        Member member5 = new Member();
        member5.setNickName("이민재");
        member5.setLoginId("lee");
        member5.setLoginPassword("test1");
        member5.setTotalLikeNumber(1L);
        memberService.save(member5);

        Member member6 = new Member();
        member6.setNickName("kim");
        member6.setLoginId("kim");
        member6.setLoginPassword("test1");
        memberService.save(member6);
        return "redirect:/";
    }
    @GetMapping("/setting1")
    public String settingForTesting(){
        Writing writing1 = new Writing();
        writing1.setTitle("아이가 자주 배를 아파해요");
        writing1.setContext("특별히 무엇을 잘 못 먹었거나 그런거는 아닌데, 왜 이러는 걸까요?");
        writing1.setMember(memberRepository.findByLoginId("choi"));
        Long w1 = writingRepository.save(writing1);
        writingRepository.findOneById(w1).setCreateDate(LocalDateTime.now().minusDays(6));

        Tag tag1 = new Tag();
        tag1.setDiseaseName(DiseaseName.abdominalPain);
        tag1.setWriting(writing1);
        tagRepository.save(tag1);

        Writing writing2 = new Writing();
        writing2.setTitle("마른 기침");
        writing2.setContext("아이가 마른 기침을 계속합니다.");
        writing2.setMember(memberRepository.findByLoginId("shin"));
        Long w2 = writingRepository.save(writing2);
        writingRepository.findOneById(w2).setCreateDate(LocalDateTime.now().minusDays(5));

        Tag tag2 = new Tag();
        tag2.setDiseaseName(DiseaseName.asthma);
        tag2.setWriting(writing2);
        tagRepository.save(tag2);

        Writing writing3 = new Writing();
        writing3.setTitle("아이의 손에 습진이 계속 나네요.");
        writing3.setContext("연고를 안 바르면 다시 심해지는데, 약을 계속 발라도 문제가 없는걸까요?");
        writing3.setMember(memberRepository.findByLoginId("hwang"));
        Long w3 = writingRepository.save(writing3);
        writingRepository.findOneById(w3).setCreateDate(LocalDateTime.now().minusDays(4));

        Tag tag3 = new Tag();
        tag3.setDiseaseName(DiseaseName.allergic);
        tag3.setWriting(writing3);
        tagRepository.save(tag3);

        Writing writing4 = new Writing();
        writing4.setTitle("지속적인 설사, 구토");
        writing4.setContext("늦은 밤이라 약국이 닫았는데 임시방편으로 어떤 조취를 취해야 할까요?");
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
        writing5.setTitle("알레르기 확인법");
        writing5.setContext("우리아이에게 어떤 음식에 알레르기가 있는지 어떻게 확인할까요?");
        writing5.setMember(memberRepository.findByLoginId("lee"));
        Long w5 = writingRepository.save(writing5);
        writingRepository.findOneById(w5).setCreateDate(LocalDateTime.now().minusDays(2));

        Tag tag5 = new Tag();
        tag5.setDiseaseName(DiseaseName.allergic);
        tag5.setWriting(writing5);
        tagRepository.save(tag5);

        Tag tag5p = new Tag();
        tag5p.setDiseaseName(DiseaseName.atopy);
        tag5p.setWriting(writing5);
        tagRepository.save(tag5p);

        Tag tag5pp = new Tag();
        tag5pp.setDiseaseName(DiseaseName.nutritionalDisease);
        tag5pp.setWriting(writing5);
        tagRepository.save(tag5pp);

        Writing writing6 = new Writing();
        writing6.setTitle("비행기에서 자주 울던 아기");
        writing6.setContext("아이가 어릴 때 비행기에서 자주 울었었는데, 물놀이를 해도 될까요?");
        writing6.setMember(memberRepository.findByLoginId("kim"));
        Long w6 = writingRepository.save(writing6);
        writingRepository.findOneById(w6).setCreateDate(LocalDateTime.now().minusDays(1));

        Tag tag6 = new Tag();
        tag6.setDiseaseName(DiseaseName.otitisMedia);
        tag6.setWriting(writing6);
        tagRepository.save(tag6);

        return "redirect:/";
    }
}
