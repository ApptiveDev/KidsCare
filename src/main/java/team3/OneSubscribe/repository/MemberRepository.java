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
    public List<Member> findByLoginId(String loginId);


    //id, pw를 받아서 일치하면 0반환. 틀리면 -1
    //혹시 특정 에러가 발생했을 때 다른 수를 반환할 수 있게 int로 선언함.
    // //public int loginCheck(String loginId, String loginPw);
    // // 이거는 service 기능 같은데??
    // // 바로 loginCheck로 하는게 아닌, 기본기능으로 하기??



}
