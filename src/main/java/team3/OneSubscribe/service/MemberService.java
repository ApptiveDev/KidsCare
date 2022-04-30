package team3.OneSubscribe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.error.IdFormException;
import team3.OneSubscribe.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {



    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private FormCheck formCheck;

    /**
     * 회원가입
     */
    @Transactional // 변경
    public Long save(Member member) {
        if (validateDuplicateMember(member)) {

        }

        memberRepository.save(member);
        return member.getId();
    }
    /**
     * 중복검사
     * true : 중복안됨.
     * throw IdFormException : 중복됨.
     */
    private boolean validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByLoginId(member.getLoginId());
        // Member가 null을 못 받고 있음
        if (findMember == null) {
            throw new IdFormException();
        }
        return true;
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
    public Member findOne(Member m) {
        return memberRepository.findOne(m.getId());
    }

    /**
     * 로그인
     */
    public boolean login(Member m) {


        Member res = memberRepository.findByLoginId(m.getLoginId());
        if (res.getLoginPassword().equals(m.getLoginPassword())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * id찾기. name, email, phoneNum이 다 일치하는 사용자가 있으면 id반환, 없으면 null반환
     * name이 만들어 지고 나면 다시 만들기
     * 찾을 때는 Member를 매개변수로 받기는 그럴 듯. 넣기는 그렇지..
     */
    public String findLoginId(String name, String email, String phoneNum) {
        List<Member> li = memberRepository.findByPhoneNumber(phoneNum);
//        li.stream().filter(m -> m.getEMail().equals(email));
        return null;
    }


    //비번찾기
    //    public boolean findPw(String loginId, String name, String email, String phoneNum) {
    //}

}

class FormCheck{

    private final static int minLEN = 6;
    private final static int maxLEN = 15;

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

    public static boolean  isEngish(char ch) {
        return ('A' <= ch && ch <= 'Z') || ('a' <= ch && ch <= 'z');
    }

    public static boolean isNumber(char ch) {
        return '0' <= ch && ch <= '9';
    }
}



