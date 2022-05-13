package team3.OneSubscribe.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team3.OneSubscribe.controller.interceptor.LoginInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginIntercepter = new LoginInterceptor();
        registry.addInterceptor(loginIntercepter) //객체등록
                .addPathPatterns(loginIntercepter.loginEssential) //검사할 url등록
                .excludePathPatterns(loginIntercepter.loginInessential); //제외도 할 수 있다. 제외하기보단 등록 안하면 되지.
    }
}
