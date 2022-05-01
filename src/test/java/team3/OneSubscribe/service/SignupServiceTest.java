//package team3.OneSubscribe.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import team3.OneSubscribe.domain.Member;
//import team3.OneSubscribe.repository.MemberRepository;
//import team3.OneSubscribe.repository.MemberRepositoryImp;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//class SignupServiceTest {
//
//
//    SignupService signupService = new SignupService(new MemberRepositoryImp());
//
//
//    @Test
//    public void idFormCheck() {
//        //글자길이에 따라 실패
//        assertThat(signupService.idFormCheck("abcde")).isEqualTo(1);
//        assertThat(signupService.idFormCheck("a123456789012345")).isEqualTo(1);
//
//        //첫글자 영어 x
//        assertThat(signupService.idFormCheck("0asdfgg")).isEqualTo(2);
//        assertThat(signupService.idFormCheck("_asdfasdg")).isEqualTo(2);
//
//        //영어, 숫자, _ 외에 다른문자
//        assertThat(signupService.idFormCheck("asdfg$g")).isEqualTo(3);
//        assertThat(signupService.idFormCheck("a_sdfas#dg")).isEqualTo(3);
//
//        //_가 있어도 통과
//        assertThat(signupService.idFormCheck("ab_23cdef")).isEqualTo(0);
//    }
//
//    @Test
//    public void pwFormCheck() {
//
//        //글자길이에 따라 실패
//        assertThat(signupService.pwFormCheck("abcde")).isEqualTo(1);
//        assertThat(signupService.pwFormCheck("a123456789012345")).isEqualTo(1);
//
//        //영어, 숫자, ~!@ 외에 다른문자
//        assertThat(signupService.pwFormCheck("asdfg$g")).isEqualTo(3);
//        assertThat(signupService.pwFormCheck("a_sdfas#dg")).isEqualTo(3);
//
//        //~!@가 있어도 통과
//        assertThat(signupService.pwFormCheck("ab~!@23cdef")).isEqualTo(0);
//    }
//
//}