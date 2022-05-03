package team3.OneSubscribe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    /**
     * 회원가입
     * 아이디 중복으로 실패하면 -1반환.
     */
    @Transactional // 변경
    public Long save(Member member) {
        if (memberRepository.findByLoginId(member.getLoginId()) != null) {//중복 회원 검사
            return Long.valueOf(-1);
        }
        memberRepository.save(member);
        return member.getId();
    }

//    private void validateDuplicateMember(Member member) {
//        List<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
//        if(!findMembers.isEmpty()){
//            throw new IllegalStateException("이미 존재하는 아이디입니다.");
//        }
//    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 한명 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOneById(memberId);
    }

    /**
     * 성공 : true
     * 실패 : false
     */
    public boolean login(Member m) {
        Member findMem = memberRepository.findByLoginId(m.getLoginId());
        if (findMem.getLoginPassword().equals(m.getLoginPassword())) {
            return true;
        }
        return false;
    }

    /**
     * id찾기.
     * name, email, phoneNum이 다 일치하는 사용자가 있으면 id반환, 없으면 null반환..
     */
//
    public String findLoginId(String name, String email, String phoneNum) {
        List<Member> res = memberRepository.findByPhoneNumber(phoneNum).stream().filter(m -> m.getEMail().equals(email))
                .filter(m -> m.getName().equals(name)).collect(Collectors.toList());
        if (res.size() == 1) {
            return res.get(0).getLoginId();
        } else if (res.size() > 1) {
            System.out.println("findLoginId()에서 2개의 아이디 탐색!");
        }
        return null; //일치하는게 없다.
    }

    //비번찾기
//    public boolean findPw(String loginId, String name, String email, String phoneNum) {
//
//    }
}


