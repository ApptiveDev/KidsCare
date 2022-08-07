package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team3.OneSubscribe.DTO.SignupDto;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.repository.MemberRepository;
import team3.OneSubscribe.service.FormCheck;
import team3.OneSubscribe.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/login")
    public String login(@ModelAttribute("m") Member m, HttpServletRequest request, Model model) {
        HttpSession session;
        if (memberService.login(m)) {
            session = request.getSession();//세션이 없다면 세션생성.
            m = memberRepository.findByLoginId(m.getLoginId());
            session.setAttribute("member", m);
            return "redirect:/";//로그인 성공
        }
        return "loginFail";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false); //세션이 없어도 생성안함.
        if (session != null) {
            session.invalidate();
            model.addAttribute("logout", "success");//덮어쓰기
        }
        return "redirect:/";
    }

    @PostMapping("/findId")
    public String findId(String name, String email, String phoneNum, Model model) {
        return "find_login_id";
    }


    //회원을 등록해준다. 등록에 성공하면 id를 반환. 실패하면 메시지를 반환.
    //어떤 검증에서 에러가 발생했는지 알기 위해 if문을 나눠서 작성.
    //에러를 보면 어디서 뭔가 잘못되었는지 한눈에 알 수 있어야 한다. errorCode를 같이 출력함.
    @PostMapping("/signup")
    public String signupMember(@ModelAttribute SignupDto signupDto) {

        Member member = new Member(signupDto);
        int errorCode = 0;
        if ((errorCode = FormCheck.idFormCheck(signupDto.getLoginId())) != 0) {
            return "IdPwFormError";
        }
        if ((errorCode = FormCheck.pwFormCheck(signupDto.getLoginPassword())) != 0) {
            return "IdPwFormError";
        }
        if (memberService.isIdDuplicated(signupDto.getLoginId())) { //ajax로 중복처리 했더라도 검사하는게 맞을듯.
            return "duplicatedID";
        }
        member.setNickName(member.getLoginId()); // 닉네임이 이라는 것을 따로 만들어놨는데, 회원가입에서 안 받음 // 그래서 logid를 nickname으로 대체하고자 함
        memberService.save(member); // id, pw 형식, 중복검사를 통과했을 때 저장.
        return "redirect:/";
    }

    //id 중복검사 api
    //ajax로 프론트에서 요청 후 js로 프론트에서 처리하는게 좋을 듯.
    //에러페이지를 보여줄까도 했지만 ajax가 더 현대적일듯.
    @ResponseBody
    @PostMapping("/isIdDuplicated")
    public boolean isIdDuplicated(@RequestBody String loginId) {

        return memberService.isIdDuplicated(loginId);
    }


}
