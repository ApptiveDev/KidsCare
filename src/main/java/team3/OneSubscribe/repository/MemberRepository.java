package team3.OneSubscribe.repository;

import team3.OneSubscribe.domain.Member;

import java.util.List;

public interface MemberRepository {


    //DB에 멤버저장. 고유 id반환.
    public Long save(Member member);

    public Member findOneById(Long id);

    public List<Member> findAll();

    //loginId 중복검사를 위함.
    //이미 id가 존재하면 해당 Member반환. 없다면 null반환.
    public Member findByLoginId(String loginId);

    public Member findByNickName(String nickName);


    //phoneNumber가 일치하는 member가 있으면 Member반환.
    //일치하는 멤버 없으면 null반환.
    public Member findByPhoneNumber(String phoneNumber);

    public List<Member> findBestExpert();

    public List<Member> findBestInexpert();




}
