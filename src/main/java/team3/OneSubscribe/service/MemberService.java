package team3.OneSubscribe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional // 변경
    public Long join(Member member){
        //validateDuplicateMember(member); //중복 회원 검사
        memberRepository.save(member);
        return member.getId();
    }

    // 사실상 loginId를 중복해서 만드는것을 허용안하므로, 중복이 처음부터 발생하지 않음.
    // 이거 필요 없음.
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByLoginId(member.getLoginId());
        // Member가 null을 못 받고 있음
        if(findMember == null){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 한명 조회
     */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
