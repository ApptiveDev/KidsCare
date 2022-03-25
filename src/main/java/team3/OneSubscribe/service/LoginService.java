package team3.OneSubscribe.service;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team3.OneSubscribe.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class LoginService {

    //autowired
    private final MemberRepository memberRepository;

    //로그인
    public boolean login(String loginId, String pw) {
        return 0 == memberRepository.loginCheck(loginId, pw);
    }

    //id찾기. name, email, phoneNum이 다 일치하는 사용자가 있으면 id반환, 없으면 null반환..
    public String findLoginId(String name, String email, String phoneNum) {
        return memberRepository.matchNamenEmailNPhoneNum(name, email, phoneNum);

    }


    //비번찾기
//    public boolean findPw(String loginId, String name, String email, String phoneNum) {
//
//    }







}
