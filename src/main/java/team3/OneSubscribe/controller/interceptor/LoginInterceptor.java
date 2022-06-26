package team3.OneSubscribe.controller.interceptor;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import team3.OneSubscribe.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Component
@NoArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    //인터셉터가 동작할 url을 넣어주는 리스트
    public List<String> loginEssential = Arrays.asList("/contents/writing/**");
    public List<String> loginInessential = Arrays.asList();//비어있다.

    /**
     * @return true : 컨트롤러에 접근 승인, false : 컨트롤러에 접근 거부
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession sess = request.getSession(false);


        boolean flag;
        if (sess != null) {
            Member m = (Member) sess.getAttribute("member");//세션 없을 때 세션 생성 x

//        HttpSession m =  request.getSession(false);//세션의 유무만을 확인하니까 통과해버린다.
//        Enumeration<String> em = m.getAttributeNames();
//        System.out.println("모든 sesseion attri key");
//        while (em.hasMoreElements()) {
//            System.out.println(em.nextElement());
//        }
            if (m != null) {
                flag=true;//접근허용

            }
            else{
                flag= false;
            }
        } else {

            flag= false;
        }
        if (!flag) {
            String destUri = request.getRequestURI();
            String destQuery = request.getQueryString();
            String dest = (destQuery == null) ? destUri : destUri + "?" + destQuery;
//            request.getSession().setAttribute("dest", dest); //굳이 세션에 등록할 필요가?
            response.sendRedirect("/member/login");
            return flag;
        }
        else{
            return flag;
        }
    }
}

