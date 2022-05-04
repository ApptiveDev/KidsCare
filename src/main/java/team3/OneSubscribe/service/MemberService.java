package team3.OneSubscribe.service;

import com.sun.xml.bind.v2.TODO;
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
     * 전화번호 중복으로 실패하면 -2반환.
     */
    @Transactional
    public Long save(Member member) {
        Member tmp = memberRepository.findByLoginId(member.getLoginId());
        if (tmp != null) {//아이디 중복 검사
            return (long) -1;
        }
        tmp = memberRepository.findByPhoneNumber(member.getPhoneNumber());
        if (tmp != null) { //전화번호 중복 검사
            return (long) -2;
        }
        return memberRepository.save(member);

    }

    /**
     * @param loginId 검사하고자하는 id값 입력.
     * @return true : 이미 존재 , false : 사용가능.
     */
    public Boolean isIdDuplicated(String loginId) {
        if (memberRepository.findByLoginId(loginId) != null) {//중복 회원 검사
            return true;
        }
        return false;
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 한명 조회
     */
    public Member findOneById(Long memberId) {
        return memberRepository.findOneById(memberId);
    }

    /**
     * 성공 : true
     * 실패 : false
     */
    public boolean login(Member m) {
        Member findMem = memberRepository.findByLoginId(m.getLoginId());
        if (findMem == null) {
            return false;
        }
        else if (findMem.getLoginPassword().equals(m.getLoginPassword())) {
            return true;
        }
        return false;
    }

    /**
     * loginId찾기.
     * name, email, phoneNum이 다 일치하는 사용자가 있으면 id반환, 없으면 null반환.
     */
    public String findLoginId(String name, String email, String phoneNum) {
        Member res = memberRepository.findByPhoneNumber(phoneNum);
        if (res.getEMail().equals(email) && res.getName().equals(name)) {
            return res.getLoginId();//일치. loginId알려준다.
        } else {
            return null; //일치하는게 없다.
        }
    }

    /**
     * @param loginId 찾고자하는 아이디
     * @param name 실명 ex) 홍길동
     * @param email 이메일
     * @param phoneNum 전화번호
     * @return 찾았는지 성공여부. 비밀번호는 이메일로 알려줌.
     */
    public boolean findPw(String loginId, String name, String email, String phoneNum) {
        Member res = memberRepository.findByLoginId(loginId);
        if (res.getName().equals(name) && res.getEMail().equals(email) && res.getPhoneNumber().equals(phoneNum)) {
            //TODO(메일발송)
            return true;
        }
        else{
            return false;
        }

    }


}



