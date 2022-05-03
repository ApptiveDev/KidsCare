package team3.OneSubscribe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    /**
     * 회원가입
     * 아이디 중복으로 실패하면 -1반환.
     */
    @Transactional // 변경
    public Long save(Member member) {
        memberRepository.save(member);
        return member.getId();
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

class FormCheck{

    private static final int minLEN = 6;
    private static final int maxLEN = 15;
    //id형식검사. 통과하면 0. 통과못하면 상황에 따른 int반환.
    public static int idFormCheck(String loginId) {
        if (loginId.length() < minLEN || loginId.length() > maxLEN) {
            return 1;
        }
        if (!isEngish(loginId.charAt(0))) {//첫글자가 영어가 아니라면 탈락.
            return 2;
        }

        for (int i = 0; i < loginId.length(); ++i) {
            char ch = loginId.charAt(i);
            if (!(isNumber(ch) || isEngish(ch) || ch=='_')) { //영어도 숫자도 '_'도 아니면 false
                return 3;
            }
        }
        return 0;//다 통과하면 true


    }

    //pw형식검사
    //통과하면 0 아니면 다른 숫자.
    public static int pwFormCheck(String loginPw) {

        if (loginPw.length() < minLEN || loginPw.length() > maxLEN) {
            return 1;
        }

        for (int i = 0; i < loginPw.length(); ++i) {
            char ch = loginPw.charAt(i);
            if (!(isNumber(ch) || isEngish(ch) || ch=='~' || ch=='!' || ch=='@')) { //영어도 숫자도, ~!@도 아니면 false
                return 3;//idFormCheck와 맞추기 위해 3을 반환.
            }
        }

        return 0;
    }

    public static boolean isEngish(char ch) {
        return ('A' <= ch && ch <= 'Z') || ('a' <= ch && ch <= 'z');
    }

    public static boolean isNumber(char ch) {
        return '0' <= ch && ch <= '9';
    }

}

