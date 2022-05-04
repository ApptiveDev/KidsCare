package team3.OneSubscribe.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.service.MemberService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {


    @Autowired
    MemberService memberService;
    @Autowired
    MockMvc mvc;

    @Test
    public void 회원가입성공() throws Exception {
        //givne
        final String loginId = "testId";
        final String loginPw = "testPassword";
        Member m = new Member();
        m.setLoginId(loginId);
        m.setLoginPassword(loginPw);
        memberService.save(m);

        //when
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("loginId", loginId);
        map.add("loginPassword", loginPw);
        //then
        mvc.perform(post("/member/login").params(map))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));


    }

    @Test
    public void 회원가입실패() throws Exception {
        //givne
        final String loginId = "testId";
        final String loginPw = "testPassword";

        //when
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("loginId", loginId);
        map.add("loginPassword", loginPw);
        //then
        mvc.perform(post("/member/login").params(map))
                .andExpect(status().isOk())
                .andExpect(view().name("loginFail"));
    }

    //TODO 로그아웃 테스트는 짜기 힘든듯.
//    @Test
//    public void 로그아웃() throws Exception {
//        //givne
//        final String loginId = "testId";
//        final String loginPw = "testPassword";
//        Member m = new Member();
//        m.setLoginId(loginId);
//        m.setLoginPassword(loginPw);
//        memberService.save(m);
//
//
//        //when
//
//        MultiValueMap map = new LinkedMultiValueMap();
//        map.add("loginId", loginId);
//        map.add("loginPassword", loginPw);
//        mvc.perform(post("/member/login").params(map))
//                .andExpect(view().name("index"));//로그인 성공
//
//        //then
//        mvc.perform(get("/member/logout"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"))
//                .andExpect(model().attribute("logout", "success"));
//    }

}